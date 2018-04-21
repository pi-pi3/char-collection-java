// this is a terrible implementation
// a decent implementation would use a HashMap<Character, Integer>
// a worse implementation would use an ArrayList<Entry>, with class Entry { char key; int value; }
// a terrible implementation uses ArrayList<Character>, like specified in the 2nd assignment

import java.util.ArrayList;

public class CharCollection {
    private static class Entry {
        public char key;
        public int index;
        public int count;

        public Entry(char key, int index, int count) {
            this.key = key;
            this.index = index;
            this.count = count;
        }

        public String toString() {
            StringBuilder str = new StringBuilder();

            str.append('[');
            str.append(this.index);
            str.append("] = '");
            str.append(this.key);
            str.append("'^");
            str.append(this.count);

            return str.toString();
        }
    }

    private ArrayList<Character> list;

    private CharCollection() {
        this.list = new ArrayList<>();
    }

    private CharCollection(ArrayList<Character> map) {
        this.list = map;
    }

    public CharCollection(char... cc) {
        this();

        for (char ch : cc) {
            this.add(ch);
        }
    }

    public CharCollection(String s) {
        this(s.toCharArray());
    }

    private void add(char ch) {
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i) == ch) {
                this.list.add(i, ch);
                return;
            }
        }

        this.list.add(ch);
    }

    private Entry entry(char ch) {
        return this.entryAt(0, ch);
    }

    private Entry entryAt(int i) {
        if (i >= this.list.size()) {
            return null;
        }

        return this.entryAt(i, this.list.get(i));
    }

    private Entry entryAt(int i, char ch) {
        int index = -1;
        int count = 0;

        for (; i < this.list.size(); i++) {
            if (this.list.get(i) == ch) {
                ++count;
                if (index < 0) {
                    index = i;
                }
            } else if (count != 0) {
                return new Entry(ch, index, count);
            }
        }

        if (count != 0) {
            return new Entry(ch, index, count);
        }

        return null;
    }

    // Liefert die Anzahl Buchstaben der Sammlung. Zum Beispiel:
    // System.out.println(cc.size()); // 11
    public int size() {
        return this.list.size();
    }

    // Liefert die Anzahl Vorkommen des Buchstabens c. Beispiele:
    // System.out.println(cc.count('R')); // 2
    // System.out.println(cc.count('X')); // 0
    public int count(char c) {
        Entry e = this.entry(c);

        if (e != null) {
            return e.count;
        }

        return 0;
    }

    // Liefert die Anzahl verschiedener Buchstaben in der Sammlung. Zum Beispiel:
    // System.out.println(cc.different()); // 5
    public int different() {
        int count = 0;
        int index = 0;
        Entry e = this.entryAt(0);

        while (e != null) {
            ++count;
            index += e.count;
            e = this.entryAt(index);
        }

        return count;
    }

    // Liefert den häufigsten Buchstaben in der Sammlung. Wenn es mehrere Kandidaten gibt, wird irgendeiner davon gewählt. Ein Beispiel:
    // System.out.println(cc.top()); // A
    // Eine leere Sammlung liefert das Zeichen mit dem Zeichencode 0zurück.
    public char top() {
        char top = (char) 0;
        int count = 0;

        int index = 0;
        Entry e = this.entryAt(0);

        while (e != null) {
            if (e.count > count) {
                top = e.key;
                count = e.count;
            }

            index += e.count;
            e = this.entryAt(index);
        }

        return top;
    }

    // Liefert eine neue Sammlung, in der nur noch die Buchstaben enthalten sind, die mehr als m-mal vorkommen. Beispiel: Nur Buchstaben, die wenigstens 2-mal vorkommen, bleiben übrig. K und D fallen heraus:
    // System.out.println(cc.moreThan(1)); // (A, B, R, A, A, A, B, R, A)
    public CharCollection moreThan(int m) {
        ArrayList<Character> map = new ArrayList<>();

        int index = 0;
        Entry e = this.entryAt(0);

        while (e != null) {
            if (e.count > m) {
                for (int i = 0; i < e.count; i++) {
                    map.add(e.key);
                }
            }

            index += e.count;
            e = this.entryAt(index);
        }

        return new CharCollection(map);
    }

    // Liefert eine neue Sammlung aus den Buchstaben dieser Sammlung, wobei alle Buchstaben von cc entfernt sind. Beispiel:
    // System.out.println(cc.except(new CharCollection("ABRAXAS"))); // (K, D, A, B, R, A)
    public CharCollection except(CharCollection cc) {
        ArrayList<Character> map = new ArrayList<>();

        int index = 0;
        Entry e = this.entryAt(0);

        while (e != null) {
            Entry other = cc.entry(e.key);
            if (other != null) {
                for (int i = 0; i < e.count - other.count; i++) {
                    map.add(e.key);
                }
            } else {
                for (int i = 0; i < e.count; i++) {
                    map.add(e.key);
                }
            }

            index += e.count;
            e = this.entryAt(index);
        }

        return new CharCollection(map);
    }

    // Liefert zurück, ob die als Parameter übergebene Sammlung in der Sammlung enthalten ist, für die die Methode aufgerufen wurde (this). Beispiel:
    // System.out.println(cc.isSubset(new CharCollection("ABRAAA"))); // true
    public boolean isSubset(CharCollection cc) {
        int index = 0;
        Entry e = cc.entryAt(0);

        while (e != null) {
            Entry self = this.entry(e.key);

            if (self != null) {
                if (e.count > self.count) {
                    return false;
                }
            } else {
                return false;
            }

            index += e.count;
            e = cc.entryAt(index);
        }

        return true;
    }

    // Liefert eine lesbare Darstellung der Sammlung.
    // System.out.println(cc); // (A, B, R, A, K, A, D, A, B, R, A)
    // Die Reihenfolge der Elemente ist irrelevant.
    public String toString() {
        if (this.list.isEmpty()) {
            return "()";
        }

        StringBuilder str = new StringBuilder();

        str.append('(');

        for (int i = 0; i < this.list.size(); i++) {
            str.append(this.list.get(i));
            if (i != this.list.size() - 1) {
                str.append(", ");
            }
        }

        str.append(')');

        return str.toString();
    }

    // Vergleicht eine Sammlung mit einem anderen Objekt x.
    // Liefert true, wenn x eine Sammlung mit den gleichen Buchstaben ist, und ansonsten false. Die Reihenfolge spielt keine Rolle.
    public boolean equals(CharCollection cc) {
        if (this.list.size() != cc.list.size()) {
            return false;
        }

        int index = 0;
        Entry e = this.entryAt(0);

        while (e != null) {
            Entry self = this.entry(e.key);
            if (e.count != self.count) {
                return false;
            }

            index += e.count;
            e = this.entryAt(index);
        }

        return true;
    }

    public int hashCode() {
        int hash = 0;

        for (char c : this.list) {
            hash += c;
        }

        return hash;
    }
}
