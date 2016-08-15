package tel_ran.numbers.tests;

import java.util.Random;

import tel_ran.collections.TreeSet;

public class TreeSetTestAppl {

	private static final int N_ELEMENTS = 20;

	public static void main(String[] args) {
		TreeSet<Integer> tree = createTree();
		tree.displayTreeLine();
		System.out.println();
		tree.displayTree();
	}
	
	private static TreeSet<Integer> createTree(){
		TreeSet<Integer> tree = new TreeSet<Integer>();
		Random gen = new Random();
		for(int i = 0; i < N_ELEMENTS; i++){
			tree.add(gen.nextInt());
		}
		return tree;
	}
}
