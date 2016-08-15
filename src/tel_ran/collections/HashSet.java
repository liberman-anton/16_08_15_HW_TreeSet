package tel_ran.collections;

import java.util.Iterator;
@SuppressWarnings("unchecked")
public class HashSet<E> implements Set<E> {
private static final int DEFAULT_CAPACITY = 16;
private static final float DEFAULT_LOAD_FACTOR = 0.75f;
private static final int DEFAULT_THRESHOLD = (int) (DEFAULT_CAPACITY*DEFAULT_LOAD_FACTOR);
List<E> [] hashTable;
int capacity=DEFAULT_CAPACITY;
float loadFactor=DEFAULT_LOAD_FACTOR;
int threshold=DEFAULT_THRESHOLD;
int size=0;
public HashSet(){
	hashTable=new LinkedList[capacity];
}
	@Override
	public Iterator<E> iterator() {
		return new HashSetIterator<E>(this);
	}

	@Override
	public void add(E data) {
		if(size==threshold)
			recreation();
		addHashTable(data,hashTable);
		size++;

	}
	private void addHashTable(E data, List<E>[]hashTable) {
		int index=getIndex(data);
		if(hashTable[index]==null)
			hashTable[index]=new LinkedList<E>();
		
		if(!hashTable[index].contains(data))
			hashTable[index].add(data);
	}

	private int getIndex(E data) {
		
		return data.hashCode()%capacity;
	}
	private void recreation() {
		capacity*=2;
		threshold*=2;
		List<E> [] newHashTable=new LinkedList[capacity];
		for(E element:this){
			addHashTable(element,newHashTable);
		}
		hashTable=newHashTable;
		
	}
	@Override
	public void remove(E element) {
		int index=getIndex(element);
		if(hashTable[index]!=null){
			hashTable[index].remove(element);
			if(hashTable[index].isEmpty())
				hashTable[index]=null;
			size--;
		}

	}

	

	@Override
	public boolean contains(E element) {
		int ind=getIndex(element);
		if(hashTable[ind]==null)
			return false;
		return hashTable[ind].contains(element);
	}

}
