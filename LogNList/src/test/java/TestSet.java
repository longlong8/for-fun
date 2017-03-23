import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Test for required set(int index, Object obj) method 
 */
public class TestSet {

	@Test
	public void test() {
		LogNList<Integer> list = new LogNList<>(); 
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		/***
		*					2									0
		*				1		4			--->			1		5
		*					 3	   5							  6	  10
		*			
		*/
		list.prettyPrint(); 
		int old = list.set(2, 6);
		assertSame(3,old);
		assertSame(6,list.get(2));
		old = list.set(1, 0);
		assertSame(2,old);
		assertSame(0,list.get(1));
		old = list.set(3, 5);
		assertSame(4,old);
		assertSame(5,list.get(3));
		old = list.set(4, 10);
		assertSame(5,old);
		assertSame(10,list.get(4));
		list.prettyPrint(); 
	}
	
	@Test
	public void test2() {
		LogNList<Integer> list = new LogNList<>(); 
		Integer old;
		for (int i=0;i<10000;i++) {
			list.add(i+1);
		}

		ArrayList<Integer> inorder = list.inorder();
		assertEquals(-133, -133);
		for (int i=0;i<10000;i++) {
			if (i%2==0) {
				System.out.println(i+ "  "+ list.get(i));
				old = list.set(i, -list.get(i));
				assertEquals(true, list.get(i).equals(-old));
			}
		}
		list.prettyPrint();
		for (int i=0;i<10000;i++) {
			if (i%2==0) assertEquals(true, inorder.get(i).equals(-list.get(i)));
			else assertSame(inorder.get(i),list.get(i));
		}
	}
}
