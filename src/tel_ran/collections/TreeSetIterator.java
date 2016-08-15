package tel_ran.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

 class TreeSetIterator<E extends Comparable<E>> implements Iterator<E>{
TreeSet<E> treeSet;
TreeSet<E>.NodeTree<E> current;
TreeSet<E>.NodeTree<E> end;
TreeSet<E>.NodeTree<E> removable;
public TreeSetIterator(TreeSet<E> treeSet){
	this.treeSet=treeSet;
	current=treeSet.getLeast(treeSet.root);
}
	public TreeSetIterator(TreeSet<E> treeSet,
			TreeSet<E>.NodeTree<E> fromNode, TreeSet<E>.NodeTree<E> end) {
	this.treeSet=treeSet;
	current=fromNode;
	this.end=end;
}
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return current != end && current != null;
	}

	@Override
	public E next() {
		if(!hasNext())
			new NoSuchElementException();
		removable=current;
		current=treeSet.next(current);
		return removable.data;
	}
	@Override
	public void remove(){
		if(removable == null)
			throw new NoSuchElementException();
		treeSet.removeNode(removable);
		removable=null;
	}

}
