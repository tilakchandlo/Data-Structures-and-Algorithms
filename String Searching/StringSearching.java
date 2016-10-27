import java.util.LinkedList;
import java.util.List;

public class StringSearching implements StringSearchingInterface {

    @Override
    public List<Integer> boyerMoore(CharSequence pattern, CharSequence text) {
        if (pattern == null || pattern.length() == 0 || text == null) {
            throw new IllegalArgumentException();
        }
        List<Integer> result = new LinkedList<Integer>();
        int n = text.length();
        int m = pattern.length();
        if (text.length() == 0 || m > n) {
            return result;
        }
        int[] last = buildLastTable(pattern);
        int i = m - 1;
        int j = m - 1;

        while (i < n) {
            char charAt = text.charAt(i);
            if (charAt == pattern.charAt(j)) {
                if (j == 0) {
                    result.add(i);
                    i += m - Math.min(j, 1 + last[charAt]);
                    j = m - 1;
                } else {
                    i--;
                    j--;
                }
            } else {
                i += m - Math.min(j, 1 + last[charAt]);
                j = m - 1;
            }
        }
        return result;
    }

    @Override
    public int[] buildLastTable(CharSequence pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException();
        }
        int[] map = new int[Character.MAX_VALUE + 1];
        for (int i = 0; i < map.length; i++) {
            map[i] = -1;
        }
        for (int i = 0; i < pattern.length(); i++) {
            map[pattern.charAt(i)] = i;
        }
        return map;
    }

    @Override
    public int generateHash(CharSequence current, int length) {
        if (current == null || length > current.length() || length < 0) {
            throw new IllegalArgumentException();
        }
        int result = 0;
        for (int i = 0; i < length; i++) {
            result = result + current.charAt(i) * power(BASE, length - i - 1);
        }
        return result;
    }

    @Override
    public int updateHash(int oldHash, int length, char oldChar, char newChar) {
        return (oldHash - (oldChar * power(BASE, length - 1))) * BASE + newChar;
    }

    /**
     * Helper power math function
     *
     * @param base the base
     * @param exponent the exponent
     * @return power
     */
    private static int power(int base, int exponent) {
        if (base == 0) {
            return 0;
        }
        int i = 0;
        int result = 1;
        while (i < exponent) {
            result *= base;
            i++;
        }
        return result;
    }

    @Override
    public List<Integer> rabinKarp(CharSequence pattern, CharSequence text) {
        if (pattern == null || text == null || pattern.length() == 0) {
            throw new IllegalArgumentException();
        }
        List<Integer> result = new LinkedList<Integer>();
        int n = text.length();
        int m = pattern.length();

        if (text.length() == 0  || m > n) {
            return result;
        }

        int textHash = generateHash(text, pattern.length());
        int patternHash = generateHash(pattern, pattern.length());
        if (textHash == patternHash) {
            result.add(0);
        }
        for (int i = m; i < n; i++) {
            textHash = updateHash(textHash,
                                  pattern.length(),
                                  text.charAt(i - pattern.length()),
                                  text.charAt(i));
            if (textHash == patternHash) {
                result.add(i - pattern.length() + 1);
            }
        }
        return result;
    }
}
