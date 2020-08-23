package hw6;

import java.util.Iterator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



public final class MarvelPaths2Test {
	
	
	String test1, test2, test3, test4, test5, test6, test7, test8, test8_dups, test8_2, marvel;
	
	@Before
	public void setUp() {
		test1 = "data/test1.csv";
		test2 = "data/test2.csv";
		test3 = "data/test3.csv";
		test4 = "data/test4.csv";
		test5 = "data/test5.csv";
		test6 = "data/test6.csv";
		test7 = "data/test7.csv";
		test8 = "data/test8.csv";
		test8_dups = "data/test8_dups.csv";
		test8_2 = "data/test8_2.csv";
		marvel = "data/marvel.csv";
	}


	@Test
	public void testDefaultConstructor() {
		new MarvelPaths2();
	}
	
	@Test
	public void testFileConstructor() {
		new MarvelPaths2(test1);
	}
	
	@Test
	public void testFileParseFailure() {
		String file1 = "data/test1_fail1.csv";
		String file2 = "data/test1_fail2.csv";
		String file3 = "data/test1_fail3.csv";
		
		MarvelPaths2 test = new MarvelPaths2();
		test.createNewGraph(file1);
		test.createNewGraph(file2);
		test.createNewGraph(file3);
	}
	
	@Test
	public void test234ListNodes() {
		MarvelPaths2 test = new MarvelPaths2(test2);
		Iterator<String> i = test.listNodes();
		assertEquals(i.next(), "char1");
		assertEquals(i.next(), "char2");
		assertEquals(i.next(), "char3");
		assertFalse(i.hasNext());
		
		test = new MarvelPaths2(test3);
		i = test.listNodes();
		assertEquals(i.next(), "char1");
		assertEquals(i.next(), "char2");
		assertFalse(i.hasNext());
		
		test = new MarvelPaths2(test4);
		i = test.listNodes();
		assertEquals(i.next(), "char1");
		assertFalse(i.hasNext());
	}
	
	@Test
	public void test2ListChildren() {
		MarvelPaths2 test = new MarvelPaths2(test2);
		Iterator<String> i = test.listNodes();
		Iterator<String> j;
		j = test.listChildren(i.next());
		assertEquals(j.next(), "char2(1.000)");
		assertEquals(j.next(), "char3(1.000)");
		assertFalse( j.hasNext() );
		j = test.listChildren(i.next());
		assertEquals(j.next(), "char1(1.000)");
		assertEquals(j.next(), "char3(1.000)");
		assertFalse( j.hasNext() );
		j = test.listChildren(i.next());
		assertEquals(j.next(), "char1(1.000)");
		assertEquals(j.next(), "char2(1.000)");
		assertFalse( j.hasNext() );
	}
	
	@Test
	public void test3ListChildren() {
		MarvelPaths2 test = new MarvelPaths2(test3);
		Iterator<String> i = test.listNodes();
		Iterator<String> j;
		j = test.listChildren(i.next());
		assertEquals(j.next(), "char2(1.000)");
		assertFalse( j.hasNext() );
		j = test.listChildren(i.next());
		assertEquals(j.next(), "char1(1.000)");
		assertFalse( j.hasNext() );
	}
	
	@Test
	public void test4ListChildren() {
		MarvelPaths2 test = new MarvelPaths2(test4);
		Iterator<String> i = test.listNodes();
		Iterator<String> j;
		j = test.listChildren(i.next());
		assertFalse( j.hasNext() );
	}
	
	
	@Test
	public void test1ListNodes() {
		MarvelPaths2 test = new MarvelPaths2(test1);
		Iterator<String> i = test.listNodes();
		assertEquals(i.next(), "char1");
		assertEquals(i.next(), "char2");
		assertEquals(i.next(), "char3");
		assertEquals(i.next(), "char4");
		assertEquals(i.next(), "char5");
		assertEquals(i.next(), "char6");
		assertFalse(i.hasNext());
	}
	
	@Test
	public void test1ListChildren() {
		MarvelPaths2 test = new MarvelPaths2(test1);
		Iterator<String> i = test.listNodes();
		Iterator<String> j;
		j = test.listChildren(i.next()); // char1
		assertEquals(j.next(), "char2(1.000)");
		assertEquals(j.next(), "char4(1.000)");
		assertEquals(j.next(), "char5(1.000)");
		assertEquals(j.next(), "char6(0.500)");
		assertFalse( j.hasNext() );
		j = test.listChildren(i.next()); // char2
		assertEquals(j.next(), "char1(1.000)");
		assertEquals(j.next(), "char3(1.000)");
		assertEquals(j.next(), "char4(1.000)");
		assertEquals(j.next(), "char5(1.000)");
		assertEquals(j.next(), "char6(1.000)");
		assertFalse( j.hasNext() );
		j = test.listChildren(i.next()); // char3
		assertEquals(j.next(), "char2(1.000)");
		assertEquals(j.next(), "char4(1.000)");
		assertEquals(j.next(), "char5(1.000)");
		assertFalse( j.hasNext() );
		j = test.listChildren(i.next()); // char4
		assertEquals(j.next(), "char1(1.000)");
		assertEquals(j.next(), "char2(1.000)");
		assertEquals(j.next(), "char3(1.000)");
		assertEquals(j.next(), "char5(0.500)");
		assertEquals(j.next(), "char6(1.000)");
		assertFalse( j.hasNext() );
		j = test.listChildren(i.next()); // char5
		assertEquals(j.next(), "char1(1.000)");
		assertEquals(j.next(), "char2(1.000)");
		assertEquals(j.next(), "char3(1.000)");
		assertEquals(j.next(), "char4(0.500)");
		assertEquals(j.next(), "char6(1.000)");
		assertFalse( j.hasNext() );
		j = test.listChildren(i.next()); // char6
		assertEquals(j.next(), "char1(0.500)");
		assertEquals(j.next(), "char2(1.000)");
		assertEquals(j.next(), "char4(1.000)");
		assertEquals(j.next(), "char5(1.000)");
		assertFalse( j.hasNext() );
	}
	
	
	@Test
	public void test6ListChildren() {
		MarvelPaths2 test = new MarvelPaths2(test6);
		Iterator<String> i = test.listNodes();
		Iterator<String> j;
		j = test.listChildren(i.next());
		assertEquals(j.next(), "char2(0.333)");
		assertEquals(j.next(), "char3(0.333)");
		assertFalse( j.hasNext() );
		j = test.listChildren(i.next());
		assertEquals(j.next(), "char1(0.333)");
		assertEquals(j.next(), "char3(0.333)");
		assertFalse( j.hasNext() );
		j = test.listChildren(i.next());
		assertEquals(j.next(), "char1(0.333)");
		assertEquals(j.next(), "char2(0.333)");
		assertFalse( j.hasNext() );
	}
	
	
	@Test
	public void testLargeFileConstructor() {
		new MarvelPaths2(marvel);
	}
	
	
	@Test
	public void testFindPathErrors() {
		MarvelPaths2 test = new MarvelPaths2(test1);
		assertEquals(test.findPath("char1", "asdf"), "unknown character asdf\n");
		assertEquals(test.findPath("asdf", "char1"), "unknown character asdf\n");
		assertEquals(test.findPath("asdf", "asdfasdf"), "unknown character asdf\nunknown character asdfasdf\n");
		assertEquals(test.findPath("char1", "char1"), "path from char1 to char1:\ntotal cost: 0.000\n");
		
		assertEquals(test.findPath("asdf", "asdf"), "unknown character asdf\n");
	}
	
	@Test
	public void testFindPathTest3() {
		MarvelPaths2 test = new MarvelPaths2(test3);
		assertEquals(test.findPath("char1", "char2"), "path from char1 to char2:\n"
				+ "char1 to char2 with weight 1.000\ntotal cost: 1.000\n");
	}
	
	
	@Test
	public void testFindPathTest1() {
		MarvelPaths2 test = new MarvelPaths2(test1);
		assertEquals(test.findPath("char1", "char4"), "path from char1 to char4:\n"
				+ "char1 to char4 with weight 1.000\n"
				+ "total cost: 1.000\n");
		assertEquals(test.findPath("char2", "char5"), "path from char2 to char5:\n"
				+ "char2 to char5 with weight 1.000\n"
				+ "total cost: 1.000\n");
		String pos1 = "path from char1 to char3:\n"
				+ "char1 to char5 with weight 1.000\n"
				+ "char5 to char3 with weight 1.000\n"
				+ "total cost: 2.000\n";
		String pos2 = "path from char1 to char3:\n"
				+ "char1 to char2 with weight 1.000\n"
				+ "char2 to char3 with weight 1.000\n"
				+ "total cost: 2.000\n";
		String actual = test.findPath("char1", "char3");
		assertTrue(actual.equals(pos1) || actual.equals(pos2));
	}
	
	@Test
	public void testFindPathTest5() {
		MarvelPaths2 test = new MarvelPaths2(test5);
		assertEquals(test.findPath("char1", "char3"), "path from char1 to char3:\n"
				+ "no path found\n");
	}
	
	@Test
	public void testFindPathMarvel() {
		MarvelPaths2 test = new MarvelPaths2(marvel);
		assertEquals(test.findPath("PETERS, SHANA TOC", "SEERESS"), "path from PETERS, SHANA TOC to SEERESS:\n"
				+ "PETERS, SHANA TOC to KNIGHT, MISTY with weight 1.000\n"
				+ "KNIGHT, MISTY to CAGE, LUKE/CARL LUCA with weight 0.017\n"
				+ "CAGE, LUKE/CARL LUCA to HULK/DR. ROBERT BRUC with weight 0.032\n"
				+ "HULK/DR. ROBERT BRUC to RAVAGE/PROF. GEOFFRE with weight 0.500\n"
				+ "RAVAGE/PROF. GEOFFRE to SEERESS with weight 1.000\n"
				+ "total cost: 2.549\n");
	}
	
	
	@Test
	public void testFindPathTest7() {
		MarvelPaths2 test = new MarvelPaths2(test7);
		assertEquals(test.findPath("B", "D"), "path from B to D:\n"
				+ "B to C with weight 0.500\n"
				+ "C to D with weight 0.500\n"
				+ "total cost: 1.000\n");
	}
	
	@Test
	public void testFindPathTest8() {
		MarvelPaths2 test;
		test = new MarvelPaths2(test8_dups);
		assertEquals(test.findPath("B", "D"), "path from B to D:\n"
				+ "B to C with weight 0.500\n"
				+ "C to D with weight 0.500\n"
				+ "total cost: 1.000\n");
		test = new MarvelPaths2(test8);
		assertEquals(test.findPath("B", "D"), "path from B to D:\n"
				+ "B to C with weight 0.500\n"
				+ "C to D with weight 0.500\n"
				+ "total cost: 1.000\n");
		assertEquals(test.findPath("A", "C"), "path from A to C:\n"
				+ "A to C with weight 0.250\n"
				+ "total cost: 0.250\n");
		test.createNewGraph(test8);
		assertEquals(test.findPath("B", "D"), "path from B to D:\n"
				+ "B to C with weight 0.500\n"
				+ "C to D with weight 0.500\n"
				+ "total cost: 1.000\n");
		test.createNewGraph(test8_2);
		assertEquals(test.findPath("A", "B"), "path from A to B:\n"
				+ "A to B with weight 1.000\n"
				+ "total cost: 1.000\n");
	}
	


	
	
}

