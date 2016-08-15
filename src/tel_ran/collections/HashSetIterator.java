package tel_ran.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashSetIterator<T> implements Iterator<T> {
	HashSet<T> hashSet;
	List<T> []hashTable;
	int currentIndex=-1;
	T removable;
	Iterator<T> currentIterator;
	public HashSetIterator(HashSet<T> hashSet) {
		this.hashSet=hashSet;
		this.hashTable=hashSet.hashTable;
		getNextIterator();
	}

	private void getNextIterator() {
		if(currentIterator==null || !currentIterator.hasNext()){
			int index=0;
			for(index=currentIndex+1; index<hashTable.length && hashTable[index]==null; index++){}
			if(index<hashTable.length){
				currentIndex=index;
				currentIterator=hashTable[index].iterator();
			}
			else {
				currentIterator=null;
			}
		}
		
		
	}

	@Override
	public boolean hasNext() {
		return currentIterator != null && currentIterator.hasNext();
	}

	@Override
	public T next() {
		if(!hasNext())
			throw new NoSuchElementException();
		removable=currentIterator.next();
		getNextIterator();
		return removable;
	}
	@Override
	public void remove(){
		if(removable==null)
			throw new NoSuchElementException();
		hashSet.remove(removable);
		removable=null;
	}

}
