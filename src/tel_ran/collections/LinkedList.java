package tel_ran.collections;

import java.util.Iterator;
@SuppressWarnings("unchecked")
public class LinkedList<E> implements List<E> {
class NodeList {
	public Object data;
	public NodeList next;
	public NodeList prev;
}

NodeList head;
 NodeList tail;
	@Override
	public void add(E data) {
		NodeList newNode=new NodeList();
		newNode.data=data;
		if(head==null){
			head=tail=newNode;
		}
		else
			addAfterTail(newNode);
	}

	@Override
	public void remove(E data) {
		NodeList node=getNode(data);
		if (node != null) {
			removeNode(node);
		}
		
	}

	private NodeList getNode(E data) {
		NodeList node=null;
		for(node=head; node != null && !node.data.equals(data);node = node.next){}
		return node;
	}

	

	@Override
	public Iterator<E> iterator() {
		return new ListIterator<E>(this);
	}

	@Override
	public boolean contains(E number) {
		NodeList current;
		for(current=head; current != null; current=current.next){
			if(current.data != null ){
				if(current.data.equals(number))
					return true;
			}
			else if (number==null)
				return true;
				

		}
		return false;
	}

	@Override
	public void add(int index, E element) {
		NodeList newNode=new NodeList();
		newNode.data=element;
		if(head==null){
			head=tail=newNode;
		}
		else {
			NodeList current;
			int ind=0;
			for(current=head;current != null && ind < index;ind++,
					current=current.next) {}
			if(current == null){
				//add after tail
				addAfterTail(newNode);
			}
			else {
				if(ind == 0)
					addBeforeHead(newNode);
				else {
					addBeforeNode(current,newNode);
				}
			}
		}
	}

	private void addBeforeNode(NodeList current, NodeList node) {
		NodeList prev=current.prev;
		node.next=current;
		node.prev=prev;
		current.prev=node;
		prev.next=node;
		
	}

	private void addBeforeHead(NodeList node) {
		head.prev=node;
		node.next=head;
		head=node;
		
	}

	private void addAfterTail(NodeList node) {
		tail.next=node;
		node.prev=tail;
		tail=node;
		
	}

	@Override
	public int indexOf(E element) {
		NodeList node=null;
		int ind=0;
		
		for(node=head;node != null && !node.data.equals(element);node=node.next,ind++){}
		return node != null?ind:-1;
	}

	@Override
	public int lastIndexOf(E element) {
		NodeList node=null;
		int ind=0;
		int indRes=-1;
		for(node=head; node != null;node=node.next,ind++){
			if(node.data.equals(element))
				indRes=ind;
		}
		return indRes;
	}

	

	

	@Override
	public E remove(int index) {
		if(head==null)
			return null;
		NodeList current;
		int ind=0;
		if(index < 0)
			return null;
		if(index==0){
			return removeHead();
		}
		for(current=head; current != null && ind < index; current=current.next,ind++)
		{}
		if(current==null)
			return null;
		return removeNode(current);	
	}

	
	E removeNode(NodeList current) {
		if(current==head)
			return removeHead();
		if(current==tail)
			return removeTail();
		NodeList before=current.prev;
		NodeList after=current.next;
		E res=(E) current.data;
		before.next=after;
		after.prev=before;
		return res;
	}

	private E removeTail() {
		if(tail == null)
			return null;
		E res=(E) tail.data;
		tail=tail.prev;
		if(tail!=null)
			tail.next=null;
		else 
			head=null;
		return res;
	}

	private E removeHead() {
		if(head == null)
			return null;
		E res=(E) head.data;
		head=head.next;
		if(head != null)
			head.prev=null;
		else
			tail=null;
		return res;
	}

	@Override
	public boolean isEmpty() {
		return head==null;
	}

	

}
