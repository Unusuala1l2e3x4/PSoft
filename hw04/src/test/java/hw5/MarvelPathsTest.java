package hw5;

import java.util.Iterator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



public final class MarvelPathsTest {
	
	
	String test1, test2, test3, test4, test5, marvel;
	
	@Before
	public void setUp() {
		test1 = "data/test1.csv";
		test2 = "data/test2.csv";
		test3 = "data/test3.csv";
		test4 = "data/test4.csv";
		test5 = "data/test5.csv";
		marvel = "data/marvel.csv";
	}


	@Test
	public void testDefaultConstructor() {
		new MarvelPaths();
	}
	
	@Test
	public void testFileConstructor() {
		new MarvelPaths(test1);
	}
	
	@Test
	public void testFileParseFailure() {
		String file1 = "data/test1_fail1.csv";
		String file2 = "data/test1_fail2.csv";
		String file3 = "data/test1_fail3.csv";
		
		MarvelPaths test = new MarvelPaths();
		test.createNewGraph(file1);
		test.createNewGraph(file2);
		test.createNewGraph(file3);
	}
	
	@Test
	public void test234ListNodes() {
		MarvelPaths test = new MarvelPaths(test2);
		Iterator<String> i = test.listNodes();
		assertEquals(i.next(), "char1");
		assertEquals(i.next(), "char2");
		assertEquals(i.next(), "char3");
		assertFalse(i.hasNext());
		
		test = new MarvelPaths(test3);
		i = test.listNodes();
		assertEquals(i.next(), "char1");
		assertEquals(i.next(), "char2");
		assertFalse(i.hasNext());
		
		test = new MarvelPaths(test4);
		i = test.listNodes();
		assertEquals(i.next(), "char1");
		assertFalse(i.hasNext());
	}
	
	@Test
	public void test2ListChildren() {
		MarvelPaths test = new MarvelPaths(test2);
		Iterator<String> i = test.listNodes();
		Iterator<String> j;
		j = test.listChildren(i.next());
		assertEquals(j.next(), "char2(book1)");
		assertEquals(j.next(), "char3(book1)");
		assertFalse( j.hasNext() );
		j = test.listChildren(i.next());
		assertEquals(j.next(), "char1(book1)");
		assertEquals(j.next(), "char3(book1)");
		assertFalse( j.hasNext() );
		j = test.listChildren(i.next());
		assertEquals(j.next(), "char1(book1)");
		assertEquals(j.next(), "char2(book1)");
		assertFalse( j.hasNext() );
	}
	
	@Test
	public void test3ListChildren() {
		MarvelPaths test = new MarvelPaths(test3);
		Iterator<String> i = test.listNodes();
		Iterator<String> j;
		j = test.listChildren(i.next());
		assertEquals(j.next(), "char2(book1)");
		assertFalse( j.hasNext() );
		j = test.listChildren(i.next());
		assertEquals(j.next(), "char1(book1)");
		assertFalse( j.hasNext() );
	}
	
	@Test
	public void test4ListChildren() {
		MarvelPaths test = new MarvelPaths(test4);
		Iterator<String> i = test.listNodes();
		Iterator<String> j;
		j = test.listChildren(i.next());
		assertFalse( j.hasNext() );
	}
	
	
	@Test
	public void test1ListNodes() {
		MarvelPaths test = new MarvelPaths(test1);
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
		MarvelPaths test = new MarvelPaths(test1);
		Iterator<String> i = test.listNodes();
		Iterator<String> j;
		j = test.listChildren(i.next()); // char1
		assertEquals(j.next(), "char2(book1)");
		assertEquals(j.next(), "char4(book2)");
		assertEquals(j.next(), "char5(book2)");
		assertEquals(j.next(), "char6(book1)");
		assertEquals(j.next(), "char6(book2)");
		assertFalse( j.hasNext() );
		j = test.listChildren(i.next()); // char2
		assertEquals(j.next(), "char1(book1)");
		assertEquals(j.next(), "char3(book3)");
		assertEquals(j.next(), "char4(book3)");
		assertEquals(j.next(), "char5(book3)");
		assertEquals(j.next(), "char6(book1)");
		assertFalse( j.hasNext() );
		j = test.listChildren(i.next()); // char3
		assertEquals(j.next(), "char2(book3)");
		assertEquals(j.next(), "char4(book3)");
		assertEquals(j.next(), "char5(book3)");
		assertFalse( j.hasNext() );
		j = test.listChildren(i.next()); // char4
		assertEquals(j.next(), "char1(book2)");
		assertEquals(j.next(), "char2(book3)");
		assertEquals(j.next(), "char3(book3)");
		assertEquals(j.next(), "char5(book2)");
		assertEquals(j.next(), "char5(book3)");
		assertEquals(j.next(), "char6(book2)");
		assertFalse( j.hasNext() );
		j = test.listChildren(i.next()); // char5
		assertEquals(j.next(), "char1(book2)");
		assertEquals(j.next(), "char2(book3)");
		assertEquals(j.next(), "char3(book3)");
		assertEquals(j.next(), "char4(book2)");
		assertEquals(j.next(), "char4(book3)");
		assertEquals(j.next(), "char6(book2)");
		assertFalse( j.hasNext() );
		j = test.listChildren(i.next()); // char6
		assertEquals(j.next(), "char1(book1)");
		assertEquals(j.next(), "char1(book2)");
		assertEquals(j.next(), "char2(book1)");
		assertEquals(j.next(), "char4(book2)");
		assertEquals(j.next(), "char5(book2)");
		assertFalse( j.hasNext() );
	}
	
	
	@Test
	public void testLargeFileConstructor() {
		new MarvelPaths(marvel);
	}
	
	
	@Test
	public void testFindPathErrors() {
		MarvelPaths test = new MarvelPaths(test1);
		assertEquals(test.findPath("char1", "asdf"), "unknown character asdf\n");
		assertEquals(test.findPath("asdf", "char1"), "unknown character asdf\n");
		assertEquals(test.findPath("asdf", "asdfasdf"), "unknown character asdf\nunknown character asdfasdf\n");
		assertEquals(test.findPath("char1", "char1"), "path from char1 to char1:\n");
		
		assertEquals(test.findPath("asdf", "asdf"), "unknown character asdf\n");
	}
	
	@Test
	public void testFindPathTest3() {
		MarvelPaths test = new MarvelPaths(test3);
		assertEquals(test.findPath("char1", "char2"), "path from char1 to char2:\n"
				+ "char1 to char2 via book1\n");
	}
	
	@Test
	public void testNoPathTest5() {
		MarvelPaths test = new MarvelPaths(test5);
		assertEquals(test.findPath("char1", "char3"), "path from char1 to char3:\n"
				+ "no path found\n");
	}
	
	

	
	
}

