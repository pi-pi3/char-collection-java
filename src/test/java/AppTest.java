import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;

public class AppTest {

	CharCollection a, b, c, d;

	@Before
	public void setUp()
	{
        a = new CharCollection('A', 'N', 'A', 'N', 'A', 'S');
        b = new CharCollection('H', 'O', 'C', 'H', 'S', 'C', 'H', 'U', 'L', 'E');
        c = new CharCollection("AAANNS");
        d = new CharCollection();
	}	

	@Test
	public void Laenge(){
		assertEquals(6,a.size());
		assertEquals(10,b.size());
		assertEquals(6,c.size());
		assertEquals(0,d.size());
	}
	
	@Test
	public void Anzahl(){
		assertEquals(2,a.count('N'));
		assertEquals(0,a.count('X'));
		assertEquals(3,b.count('H'));
		assertEquals(1,c.count('S'));
		assertEquals(0,d.count('H'));
	}
	
	@Test
	public void Verschieden(){
		assertEquals(3,a.different());
		assertEquals(7,b.different());
		assertEquals(3,c.different());
		assertEquals(0,d.different());
	}
	
	@Test
	public void Haeufigster(){
		assertEquals('A',a.top());
		assertEquals('H',b.top());
		assertEquals('A',c.top());
		assertEquals(0,d.top());
	}
	
	@Test
	public void Vergleich(){
		assertTrue(a.equals(c));
		assertTrue(b.equals(new CharCollection("CCEHHHLOSU")));
		assertFalse(b.equals(new CharCollection("CEHLOSU")));
		assertTrue(d.equals(new CharCollection("")));
	}

	@Test
	public void Ausgabe(){
		assertEquals(a.toString().length(),c.toString().length());
		assertEquals(b.toString().length(),new CharCollection("CCEHHHLOSU").toString().length());
		assertEquals("()",d.toString());

		char[] aa = a.toString().toCharArray();
		char[] ca = c.toString().toCharArray();
		Arrays.sort(aa);
		Arrays.sort(ca);
		assertTrue(Arrays.equals(aa, ca));

		char[] ba = b.toString().toCharArray();
		char[] b2a = "(C, C, H, H, H, L, O, E, S, U)".toCharArray();
		Arrays.sort(ba);
		Arrays.sort(b2a);
		assertTrue(Arrays.equals(ba, b2a));
	}

	@Test
	public void Mindestens(){
		assertTrue(a.moreThan(1).equals(new CharCollection("AAANN")));
		assertTrue(a.moreThan(2).equals(new CharCollection("AAA")));
		assertEquals("(A, A, A)",a.moreThan(2).toString());
		assertTrue(b.moreThan(1).equals(new CharCollection("CCHHH")));
		assertTrue(b.moreThan(3).equals(new CharCollection("")));
		assertEquals("()",b.moreThan(3).toString());
	}
	
	@Test
	public void Differenz(){
		assertTrue(a.except(new CharCollection("NASE")).equals(new CharCollection("AAN")));
		assertTrue(a.except(new CharCollection("KIWI")).equals(new CharCollection("ANANAS")));
		assertTrue(a.except(new CharCollection()).equals(new CharCollection("ANANAS")));
		assertTrue(a.except(new CharCollection("ANANAS")).equals(new CharCollection()));
		assertTrue(b.except(new CharCollection("CHHO")).equals(new CharCollection("SCHULE")));
		assertTrue(c.except(new CharCollection("HOSIANNA")).equals(new CharCollection('A')));
		assertTrue(d.except(new CharCollection("ABCD")).equals(new CharCollection()));
	}
	
	@Test
	public void Untermenge(){
		assertTrue(a.isSubset(new CharCollection("ANANAS")));
		assertTrue(a.isSubset(new CharCollection("NASA")));
		assertTrue(a.isSubset(new CharCollection("")));
		assertFalse(a.isSubset(new CharCollection("NASE")));
		assertFalse(a.isSubset(new CharCollection("AAAA")));
	}
}
