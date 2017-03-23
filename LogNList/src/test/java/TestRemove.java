import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

/**
 * Test for required remove(int index) method 
 */
public class TestRemove {

	@Test
	public void test() {
		LogNList<Integer> list = new LogNList<>(); 
		for (int i=0;i<10;i++) {
			list.add(i+1);
		}
		list.prettyPrint();
		/**				   4 
		*			2			 8 
		*		1	   3 	  6		  9 
		*				    5	 7 	    10 
		*/
		System.out.println("get(7): "+list.get(7));
		Integer out = list.remove(7); //remove 8
		System.out.println("out: "+out);
		assertSame(8,out);
		list.prettyPrint();
		/**				   4 
		*			2			  8 
		*		1	   3 	  6		  10 
		*				    5	 7 	     
		*/
		System.out.println(list.get(7));
		out = list.remove(7); //remove 8
		System.out.println(out);
		assertSame(9,out);
		list.prettyPrint();
		assertSame(4,list.height());
		/**				   4 
		*			2			 7 
		*		1	   3 	  6		 10
		*				     5	 	     
		*/
	}
	
	@Test
	public void test2() {
		LogNList<Integer> list = new LogNList<Integer>();
		list.add(0, 0);
		list.add(1, 1);
		list.add(2, 2);
		list.add(3, 3);
		list.prettyPrint();
		System.out.println(list.get(2));
		list.remove(2);
		list.prettyPrint();
		System.out.println(list.get(2));
		assertTrue(list.get(2) == 3);

	}
	
	@Test
	public void RandomRemove() { 
		LogNList<Integer> list = new LogNList<>(); 
		Random rd = new Random();
		
		for (int i = 0; i < 2000; i++) {
			list.add(i,i+1); 
		}
//		list.prettyPrint();
		for (int i = 0; i < 1499; i++) {
			int index = rd.nextInt(2000-i);
			int before = list.size();
			list.remove(index);
//			System.out.println("index:  "+index);
//			System.out.println("before remove:  "+list.size());
//			System.out.println("removed:  "+list.remove(index));	
//			System.out.println("after remove:  "+list.size());
//			System.out.println("list height: "+list.height());
			assertEquals(before-1,list.size());
//			list.prettyPrint();
		}
	}

	//	11 
	//	4 14 
	//	3 6 12 15 
	//	null null null null null null null 16 
	//	--------------------
	//	index:  3
	//	before remove:  8
	//	removed:  11
	//	after remove:  5
	//	list height: 3
	//	--------------------
	//	12 
	//	4 14 
	//	3 6 null null 
	//	null null null null 
		
//	@Test
	public void WrongRemove() { 
		LogNList<Integer> list = new LogNList<>(); 
		list.add(11);
		list.add(0,4);
		list.add(14);
		list.add(1,6);
		list.add(0,3);
		list.add(4,12);
		list.add(15);
		list.add(16);	
		list.prettyPrint();
		
		System.out.println("before remove:  "+list.size());
		System.out.println("removed:  "+list.remove(4));	
		System.out.println("after remove:  "+list.size());
		System.out.println("list height: "+list.height());
		list.prettyPrint();

	}
	
	//	7 
	//	4 16 
	//	2 6 null null 
	//	null null null null 
	//	--------------------
	//	index:  4
	//	before remove:  5
	//	removing: removed.tmpObj16
	//	removed:  16
	//	after remove:  2
	//	list height: 2
	//	--------------------
	//	7 
	//	6 null 
	//	null null 
//	@Test
	public void WrongRemove2() { 
		LogNList<Integer> list = new LogNList<>(); 
		list.add(7);
		list.add(0,4);
		list.add(16);
		list.add(1,6);
		list.add(0,2);	
		list.prettyPrint();
		
		System.out.println("before remove:  "+list.size());
		System.out.println("removed:  "+list.remove(4));	
		System.out.println("after remove:  "+list.size());
		System.out.println("list height: "+list.height());
		list.prettyPrint();

	}
	
	//	8 
	//	5 12 
	//	2 6 10 14 
	//	null null null 7 9 11 13 15 
	//	null null null null null null null null null 16 null null 
	//	--------------------
	//	index:  1
	//	before remove:  13
	//	removing: removed.tmpObj5
	//	removed:  5
	//	after remove:  11
	//	list height: 4
	//	--------------------
	//	12 
	//	8 14 
	//	6 10 13 15 
	//	2 null 9 11 null null null 16 
	
//	@Test
	public void WrongRemove3() { 
		LogNList<Integer> list = new LogNList<>(); 
		list.add(8);
		list.add(0,5);
		list.add(12);
		list.add(1,6);
		list.add(0,2);
		
		list.add(4,10);
		list.add(14);
		list.add(3,7);
		
		
		list.add(5,9);
		list.add(7,11);
		list.add(9,13);
		list.add(15);
		list.add(12,16);
		list.prettyPrint();
		System.out.println("before remove:  "+list.size());
		System.out.println("removed:  "+list.remove(1));	
		System.out.println("after remove:  "+list.size());
		System.out.println("list height: "+list.height());
		list.prettyPrint();

	}
}
