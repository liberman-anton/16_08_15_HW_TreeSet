package tel_ran.numbers.implementation;


import java.util.Iterator;

import tel_ran.collections.HashSet;
import tel_ran.collections.LinkedList;
import tel_ran.numbers.interfaces.INumbersBox;

public abstract class NumbersBoxList extends NumbersBoxCollection {
	

	


	@Override
	public void removeAllNumbers(int number) {
		
		Iterator<Integer> it=numbers.iterator();
		while(it.hasNext()){
			int num=it.next();
			if (num==number)
				it.remove();
		}

	}

	

	@Override
	public void union(INumbersBox numbers) {
		for(Integer number:numbers){
			if(!this.numbers.contains(number))
				this.numbers.add(number);
		}

	}

	@Override
	public void subtract(INumbersBox numbers) {
		super.subtract(numbers);
		removeRepeated();
	}

	

	

	@Override
	public void removeRepeated() {
		LinkedList<Integer> tmp=new LinkedList<Integer>();
		Iterator<Integer> it=numbers.iterator();
		while(it.hasNext())	{
			Integer number=it.next();
			if(tmp.contains(number))
					it.remove();
			else
				tmp.add(number);
		}

	}
	

}
