package Genetic;

import java.util.*;


public class GenetikUtils {

    public static List<String> KromozomOlusturucu(int n, int uzunluk) throws Exception {

        long seed = 5L;
        List<String> strings = new ArrayList<String>();
        StringBuilder kromozomBuilder = null;
        for (BitSet bitSet : createSequeces(n, uzunluk, seed)) {
            kromozomBuilder = new StringBuilder();
            for (int i = 0; i < uzunluk; i++) {
                kromozomBuilder.append(((bitSet.get(i)) ? "0" : "1"));
            }
            strings.add(kromozomBuilder.toString());
        }
        return strings;
    }

    private static Set<BitSet> createSequeces(int n, int length, long seed) {
        Set<BitSet> set = new HashSet<BitSet>();
        Random rand = new Random(seed);
        while (set.size() < n) {
            set.add(createRandom(rand, length));
        }
        return set;
    }

    private static BitSet createRandom(Random rand, int length) {
        BitSet secuence = new BitSet(length);
        for (int i = 0; i < length; i++) {
            secuence.set(i, rand.nextBoolean());
        }
        return secuence;
    }


}
