import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


/**
 * Test for required add(Object obj) method 
 */

public class TestAdd {

	@Test
	public void testAdd() {
		LogNList<Integer> list = new LogNList<>(); 
		list.add(1);
		list.add(2);
		list.add(3);
		/***
		*		1									2
		*			2			-->				1		3
		*				3
		*/	
		//check rotation
		assertEquals(2, list.height());
		assertSame(3,list.get(2));
		assertSame(1,list.get(0));
		assertSame(3,list.size());
		assertSame(2,list.get(1));
		assertSame(2,list.root());
		
		list.add(4);
		/***
		*					2
		*				1		3
		*							4
		*/
		assertEquals(3, list.height());
		assertSame(2,list.get(1));
		assertSame(3,list.get(2));
		list.add(5);
		/***
		*					2											2
		*				1	   (3)					--->			1		4
		*							4									 3	   5
		*								5
		*/
		
		assertEquals(3, list.height());
		assertSame(2,list.root());
		assertSame(2,list.get(1));
		assertSame(4,list.get(3));
		assertSame(3,list.get(2));
		for (int i=6; i<=10000;i++) {
			list.add(i);
		}
		assertEquals(14, list.height());
		//check if inorder traversal of tree is in correct order
		ArrayList<Integer> inorder = list.inorder();
		for (int i=0;i<10000;i++) {
			assertEquals(true, inorder.get(i).equals(i+1));
		}
		
	}
	
	@Test
	//sort of testing get method and postorder
	public void testAdd2() {
		LogNList<Integer> list = new LogNList<>(); 

		for (int i=0;i<100;i++) {
			list.add(i+1);
		}
		ArrayList<Integer> inorder = list.inorder();
		for (int i=0;i<100;i++) {
			assertSame(inorder.get(i),list.get(i));
		}
	
	}

}
