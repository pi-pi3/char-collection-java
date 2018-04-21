import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
        CharCollection a, b, c, d;

        a = new CharCollection('A', 'N', 'A', 'N', 'A', 'S');
        b = new CharCollection('H', 'O', 'C', 'H', 'S', 'C', 'H', 'U', 'L', 'E');
        c = new CharCollection("AAANNS");
        d = new CharCollection();

		if (!(6 == a.size())) throw new Exception("fail");
		if (!(10 == b.size())) throw new Exception("fail");
		if (!(6 == c.size())) throw new Exception("fail");
		if (!(0 == d.size())) throw new Exception("fail");
	
		if (!(2 == a.count('N'))) throw new Exception("fail");
		if (!(0 == a.count('X'))) throw new Exception("fail");
		if (!(3 == b.count('H'))) throw new Exception("fail");
		if (!(1 == c.count('S'))) throw new Exception("fail");
		if (!(0 == d.count('H'))) throw new Exception("fail");
	
		if (!(3 == a.different())) throw new Exception("fail");
		if (!(7 == b.different())) throw new Exception("fail");
		if (!(3 == c.different())) throw new Exception("fail");
		if (!(0 == d.different())) throw new Exception("fail");
	
		if (!('A' == a.top())) throw new Exception("fail");
		if (!('H' == b.top())) throw new Exception("fail");
		if (!('A' == c.top())) throw new Exception("fail");
		if (!(0 == d.top())) throw new Exception("fail");
	
		if (!(a.equals(c))) throw new Exception("fail");
		if (!(b.equals(new CharCollection("CCEHHHLOSU")))) throw new Exception("fail");
		if (!(!b.equals(new CharCollection("CEHLOSU")))) throw new Exception("fail");
		if (!(d.equals(new CharCollection("")))) throw new Exception("fail");

		if (!(a.toString().length() == c.toString().length())) throw new Exception("fail");
		if (!(b.toString().length() == new CharCollection("CCEHHHLOSU").toString().length())) throw new Exception("fail");
		if (!("()" == d.toString())) throw new Exception("fail");

		char[] aa = a.toString().toCharArray();
		char[] ca = c.toString().toCharArray();
		Arrays.sort(aa);
		Arrays.sort(ca);
		if (!(Arrays.equals(aa, ca))) throw new Exception("fail");

		char[] ba = b.toString().toCharArray();
		char[] b2a = "(C, C, H, H, H, L, O, E, S, U)".toCharArray();
		Arrays.sort(ba);
		Arrays.sort(b2a);
		if (!(Arrays.equals(ba, b2a))) throw new Exception("fail");

		if (!(a.moreThan(1).equals(new CharCollection("AAANN")))) throw new Exception("fail");
		if (!(a.moreThan(2).equals(new CharCollection("AAA")))) throw new Exception("fail");
		if (!("(A, A, A)".equals(a.moreThan(2).toString()))) throw new Exception("fail");
		if (!(b.moreThan(1).equals(new CharCollection("CCHHH")))) throw new Exception("fail");
		if (!(b.moreThan(3).equals(new CharCollection("")))) throw new Exception("fail");
		if (!("()".equals(b.moreThan(3).toString()))) throw new Exception("fail");
	
		if (!(a.except(new CharCollection("NASE")).equals(new CharCollection("AAN")))) throw new Exception("fail");
		if (!(a.except(new CharCollection("KIWI")).equals(new CharCollection("ANANAS")))) throw new Exception("fail");
		if (!(a.except(new CharCollection()).equals(new CharCollection("ANANAS")))) throw new Exception("fail");
		if (!(a.except(new CharCollection("ANANAS")).equals(new CharCollection()))) throw new Exception("fail");
		if (!(b.except(new CharCollection("CHHO")).equals(new CharCollection("SCHULE")))) throw new Exception("fail");
		if (!(c.except(new CharCollection("HOSIANNA")).equals(new CharCollection('A')))) throw new Exception("fail");
		if (!(d.except(new CharCollection("ABCD")).equals(new CharCollection()))) throw new Exception("fail");
	
		if (!(a.isSubset(new CharCollection("ANANAS")))) throw new Exception("fail");
		if (!(a.isSubset(new CharCollection("NASA")))) throw new Exception("fail");
		if (!(a.isSubset(new CharCollection("")))) throw new Exception("fail");
		if (!(!a.isSubset(new CharCollection("NASE")))) throw new Exception("fail");
		if (!(!a.isSubset(new CharCollection("AAAA")))) throw new Exception("fail");
    }
}
