package tel_ran.collections;

import java.util.ArrayList;
import java.util.Iterator;


public class TreeSet<E extends Comparable<E>> implements NavigableSet<E> {
	
@SuppressWarnings("hiding")
class NodeTree<E extends Comparable<E>> {
	E data;
	NodeTree<E> parent;
	NodeTree<E> left;
	NodeTree<E> right;
	
}

NodeTree<E> root;
//int level = 0;
public TreeSet() {}
	public TreeSet(Iterator<E> it) {
	while(it.hasNext()){
		E element=it.next();
		add(element);
	}
}

	@Override
	public void add(E data) {
		TreeSet<E>.NodeTree<E> newNode=new NodeTree<E>();
		newNode.data=data;
	
		NodeTree<E> current=root;
		NodeTree<E> parent=null;
		if(root==null){
			root=newNode;
			return;
		}
		int resCompare = 0;
		while(current != null){
			parent=current;
			resCompare=compareTreeNodes(current,newNode);
			if(resCompare==0)
				return;
			current=resCompare > 0?current.left:current.right;
		}
		if(resCompare > 0)
			parent.left=newNode;
		else
			parent.right=newNode;
		newNode.parent=parent;
		
	}

	private int compareTreeNodes(NodeTree<E> current, NodeTree<E> newNode) {
		
		return current.data.compareTo(newNode.data);
	}

	@Override
	public void remove(E obj) {
		NodeTree<E> nodeRemoved=find(obj);
		if(nodeRemoved==null)
			return;
		removeNode(nodeRemoved);
	}

	 void removeNode(NodeTree<E> nodeRemoved) {
		if(nodeRemoved != null){
			if(nodeRemoved.left != null && nodeRemoved.right != null)
				removeJunction(nodeRemoved);
			else
				removeListNode(nodeRemoved);
		}
	}
	private NodeTree<E> find(E obj) {
		NodeTree<E> node=root;
		while(node != null && obj.compareTo(node.data)!=0)
			node=obj.compareTo(node.data)>0?node.right:node.left;
		return node;
	}
	private void removeJunction(NodeTree<E> nodeRemoved) {
		NodeTree<E> substitute =nodeRemoved.right;
		//finding substitute - a node with left=null
		substitute = getLeast(substitute);
		nodeRemoved.data=substitute.data;
		removeListNode(substitute);
		
	}
	NodeTree<E> next (NodeTree<E> current){
		return current.right != null?getLeast(current.right):getGreaterParent(current);
	}

	private NodeTree<E> getGreaterParent(NodeTree<E> current) {
		NodeTree<E> tmp;
		for(tmp=current;tmp.parent!=null&&tmp.parent.right==tmp;tmp=tmp.parent){}
		return tmp.parent;
	}

	 NodeTree<E> getLeast(NodeTree<E> current) {
		while(current.left != null)
			current=current.left;
		return current;
	}
	private void removeListNode(NodeTree<E> nodeRemoved) {
		NodeTree<E> parent=nodeRemoved.parent;
		NodeTree<E> child=nodeRemoved.left != null?nodeRemoved.left:nodeRemoved.right;
		if(parent == null) /* removing root */
			root=child;
		else {
			if(parent.left==nodeRemoved)
				parent.left=child;
			else
				parent.right=child;
		}
		if(child != null)
			child.parent=parent;
		
	}

	

	@Override
	public boolean contains(E data) {
		TreeSet<E>.NodeTree<E> current=root;
		NodeTree<E> pattern=new NodeTree<>();
		pattern.data=data;
		while(current != null){
			int resCompare=compareTreeNodes(current, pattern);
			if(resCompare==0)
				return true;
			current=resCompare > 0 ?current.left:current.right;
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return root != null? new TreeSetIterator<E>(this):null;
	}

	@Override
	public NavigableSet<E> subSet(E from, boolean inclusiveFrom, E to, boolean inclusiveTo) {
		NodeTree<E> fromNode=inclusiveFrom? getEqualGreaterLeast(from):getGreaterLeast(from);
		NodeTree<E> toNode=inclusiveTo? getEqualLessMost(to):getLessMost(to);
		NodeTree<E> end=toNode != null? next(toNode):null;
		TreeSetIterator<E> it=new TreeSetIterator<E>(this,fromNode,end);
		return new TreeSet<E>(it);
	}

	private NodeTree<E> getLessMost(E to) {
		NodeTree<E> current=root;
		while(current != null){
			int res=to.compareTo(current.data) ;
			if(res > 0)
				break;
			current=res==0?null:current.left;
		}
		return current;
	}

	private NodeTree<E> getEqualLessMost(E to) {
NodeTree<E> current=root;
		
		while (current != null) {
			int resComp=to.compareTo(current.data);
			if ( resComp == 0)
				break;
			if (resComp > 0){
				if(current.right==null)
					break;
				current = current.right;
			}
			else {
				
				current = current.left;
			}
		} 
		return current;
	}

	private NodeTree<E> getGreaterLeast(E from) {
		NodeTree<E> current=root;
		while(current != null){
			int res=from.compareTo(current.data) ;
			if(res < 0)
				break;
			current=res==0?null:current.right;
		}
		return current;
	}

	private NodeTree<E> getEqualGreaterLeast(E from) {
		
		NodeTree<E> current=root;
		
		while (current != null) {
			int resComp=from.compareTo(current.data);
			if ( resComp == 0)
				break;
			if (resComp < 0){
				if(current.left==null)
					break;
				current = current.left;
			}
			else {
				
				current = current.right;
			}
		} 
		return current;
	}

	public void displayTreeLine(){
		displayNodesLine(root);
	}
	private void displayNodesLine(NodeTree<E> root) {
		if(root != null){
			displayNodesLine(root.left);
			System.out.print(root.data + " ");
			displayNodesLine(root.right);
		}
	}
	
	public void displayTree(){
		
		displayNodes(root,0);
		
	}
	private void displayNodes(NodeTree<E> root, int level) {
		if(root != null){
			displayNodes(root.right, level + 1);
			offset(level);
			System.out.println(root.data);
			displayNodes(root.left, level + 1);
		}
	}
	private void offset(int level) {
		for(int i = 0; i < level; i++){
			System.out.print("\t");
		}
	}
	public int getWidth(){
		return getNumbersOfLeafs(root);
	}
	private int getNumbersOfLeafs(NodeTree<E> root){
		if(root == null) return 0;
		if(root.left == null && root.right == null) return 1;
		return getNumbersOfLeafs(root.left) + getNumbersOfLeafs(root.right);
	}
	public int getHigh(){
		return getHighNodes(root);
	}
	private int getHighNodes(NodeTree<E> root) {
		if(root == null) 
			return 0;
		int leftHigh = getHighNodes(root.left);
		int rightHigh = getHighNodes(root.right);
		return 1 + (leftHigh > rightHigh ? leftHigh : rightHigh);
	}
	
	public void balance(){
		ArrayList<NodeTree<E>> list = new ArrayList<NodeTree<E>>();
		list = buildList(root,list);
		int size = list.size();
		root = balanceRoot(0,size - 1,list);
	}
	private NodeTree<E> balanceRoot(int begin, int end, ArrayList<NodeTree<E>> list) {
		NodeTree<E> centre = new NodeTree<E>();
//		if(end - begin <= -1)
//			return null;
		
		if(end - begin == 0){
			centre = list.get(begin + (end-begin)/2);
			centre.left = null;
			centre.right = null;
			return centre;
		}
		int indCentre = begin + (end - begin) / 2;
		centre = list.get(indCentre);
		centre.left = balanceRoot(begin,indCentre - 1,list);
		centre.right = balanceRoot(indCentre + 1,end,list);
		return centre;
	}
	
	private ArrayList<NodeTree<E>> buildList(NodeTree<E> root, ArrayList<NodeTree<E>> list) {
		if(root != null){
			buildList(root.left,list);
			list.add(root);
			buildList(root.right,list);
		}
		return list;
	}
}
/* эррэй лист можно построить рекурсивно как печатали
 * баланс - возвращаем ссылку на рут
 *
 * итератором создаем отсартированные массив элиментов (не данных)
 * делем по-полам - получаем рут
 * лев у рута = баланс с лева 
 * прав у рута = баланс с права
 * лев инд мен или равно право - условие выполнения
 * 
 * прав инд минус лев инд пополам
 * 
 * итератор итерирует узлы (не данные)
 * функция некст возвращает узел
 */