import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Test for required add(int index, Object obj) method 
 */

public class TestAddTo {

	@Test
	public void test() {
		LogNList<Integer> list = new LogNList<>(); 
		list.add(0,1);
		list.add(0,2);
		list.add(0,3);
		/***
		*				1							2		
		*			2			-->				3		1
		*		 3
		*/	
		//check rotation
		assertEquals(2, list.height());
		assertEquals(true, list.get(2).equals(1));
		assertEquals(true, list.get(0).equals(3));
		assertEquals(3, list.size());
		assertEquals(true, list.get(1).equals(2));
		assertEquals(true, list.root().equals(2));
		
		list.add(0,4);
		/***
		*					2
		*				3		1
		*			4
		*/
		assertEquals(3, list.height());
		assertEquals(true, list.get(1).equals(3));
		assertEquals(true, list.get(0).equals(4));
		
		list.add(0,5);
		/***
		*					2											2
		*				3	    1					--->			4		1
		*			4									 		5	   3
		*		5
		*/
		
		assertEquals(3, list.height());
		assertEquals(true, list.root().equals(2));
		assertEquals(true, list.get(1).equals(4));
		assertEquals(true, list.get(3).equals(2));
		assertEquals(true, list.get(2).equals(3));
		
		for (int i=6; i<=10000;i++) {
			list.add(0,i);
		}
		//check for balancing
		assertEquals(14, list.height());
		/**
		 * Same deal with the test for add(Object obj) method, 
		 * but the result of adding all number to index 0 should make the tree reversed, 
		 * and use the customized reverseInorderTrevarsal method should give us the normal order [1,...,n]
		 */
		ArrayList<Integer> revInorder = list.reverseInorder();
		for (int i=0;i<10000;i++) {
			assertEquals(true, revInorder.get(i).equals(i+1));
		}
	}
	
	@Test
	public void test2() {
		LogNList<Character> list = new LogNList<>(); 
		list.add(0,'a');
		list.add(0,'b');
		list.add(0,'c');
		/***
		*				a							b		
		*			b			--->			c		a
		*		 c
		*/	
		list.add(1,'d');
		list.add(2,'e');
		/***
		*			b								b		
		*		c		a		--->			d		a
		*		  d							 c	   e
		*			e
		*/	
		assertEquals(3, list.height());
		assertEquals(true, list.root().equals('b'));
		assertEquals(true, list.get(0).equals('c'));
		assertEquals(true, list.get(2).equals('e'));
		assertEquals(true, list.get(1).equals('d'));
		assertEquals(true, list.get(4).equals('a'));
		
		list.add(3,'f');
		/***
		*			b											 e		
		*		d		a			--->				  d				b	
		* 	 c	  e		   						      c	 	 		 f	   a
		*			f		   							   
		*			  
		*/	
		assertEquals(true, list.root().equals('e'));
		assertEquals(true, list.get(1).equals('d'));
		assertEquals(true, list.get(4).equals('b'));
		assertEquals(true, list.get(5).equals('a'));
		assertEquals(true, list.get(2).equals('e'));
		
		ArrayList<Character> level = list.levelTraversal();
		for (Character c:level) {
			System.out.print(c + " ");
		}
		System.out.println();
		list.add(4,'g');
		list.add(5,'h');
		/***
		*			  e												 e		
		*		d			b			--->				  d				b	
		* 	 c	  		 f  	a					      c	 	 		 g	   a
		*				   g   										  f	   h
		*					 h							   
		*			  
		*/	
		
		level = list.levelTraversal();
		for (Character c:level) {
			System.out.print(c + " ");
		}
		
		assertEquals(true, list.root().equals('e'));
		assertEquals(true, list.get(1).equals('d'));
		assertEquals(true, list.get(3).equals('f'));
		assertEquals(true, list.get(4).equals('g'));
		assertEquals(true, list.get(5).equals('h'));
		assertEquals(true, list.get(6).equals('b'));
	}

}
