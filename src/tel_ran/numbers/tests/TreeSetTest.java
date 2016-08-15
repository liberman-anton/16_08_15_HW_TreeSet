package tel_ran.numbers.tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import tel_ran.collections.TreeSet;

public class TreeSetTest {
	static TreeSet<Integer> tree = new TreeSet<Integer>();
	int n_elements[] = {7,15,31,63,127};
	int n_hights[] = {3,4,5,6,7};
	int n_widts[] = {4,8,16,32,64};
	
	private static final int N_ELEMENTS = 7;
	@Before
	public void setUp() throws Exception {
	}
	private static TreeSet<Integer> createTree(int nElements){
		tree = new TreeSet<Integer>();
		Random gen = new Random();
		for(int i = 0; i < nElements; i++){
			Integer num = gen.nextInt(99);
			while(tree.contains(num)){
				num = gen.nextInt(99);
			}
			tree.add(num);
		}
		return tree;
	}
	@Test
	public void displayTest() {
		tree = createTree(N_ELEMENTS);
		displayLine();
		System.out.println();
		tree.displayTree();
		System.out.println();
		System.out.println("Width = " + tree.getWidth());
		System.out.println("High = " + tree.getHigh());
		tree.balance();
		tree.displayTree();
	}
	private void displayLine() {
		int ind = 0;
		for(Integer i : tree){
			System.out.print(ind++ + "-" + i + "  ");
			
		}
	}
	
	@Test
	public void balanceTest(){
		for(int i = 0; i < n_elements.length - 1; i++){
			balanceTestOfIndexExpect(i);
		}
	}
	
	public void balanceTestOfIndexExpect(int indexExpect){
		tree = createTree(n_elements[indexExpect]);
		//displayLine();
		tree.balance();
		int hight = tree.getHigh();
		assertEquals(n_hights[indexExpect], hight );
		int widts = tree.getWidth();
		assertEquals(n_widts[indexExpect], widts );
	}
}
