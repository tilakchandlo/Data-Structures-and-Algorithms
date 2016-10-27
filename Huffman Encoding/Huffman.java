import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Huffman {

    // Do NOT add any variables (instance or static)

    /**
     * Builds a frequency map of characters for the given string.
     *
     * This should just be the count of each character.
     * No character not in the input string should be in the frequency map.
     *
     * @param s the string to map
     * @return the character -> Integer frequency map
     */
    public static Map<Character, Integer> buildFrequencyMap(String s) {
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException();
        }
        Map<Character, Integer> result = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (result.get(c) == null) {
                result.put(c, 1);
            } else {
                result.replace(c, result.get(c), result.get(c) + 1);
            }
        }
        return result;
    }

    /**
     * Build the Huffman tree using the frequencies given.
     *
     * Add the nodes to the tree based on their natural ordering (the order
     * given by the compareTo method).
     * The frequency map will not necessarily come from the
     * {@code buildFrequencyMap()} method. Every entry in the map should be in
     * the tree.
     *
     * @param freq the frequency map to represent
     * @return the root of the Huffman Tree
     */
    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> freq) {
        if (freq == null) {
            throw new IllegalArgumentException();
        }
        PriorityQueue<HuffmanNode> huffmanTree = new PriorityQueue();
        for (Map.Entry<Character, Integer> item : freq.entrySet()) {
            huffmanTree.add(new HuffmanNode(item.getKey(), item.getValue()));
        }

        while (huffmanTree.size() > 1) {
            HuffmanNode left = huffmanTree.poll();
            HuffmanNode right = huffmanTree.poll();
            huffmanTree.add(new HuffmanNode(left, right));
        }
        return huffmanTree.poll();
    }

    /**
     * Traverse the tree and extract the encoding for each character in the
     * tree.
     *
     * The tree provided will be a valid huffman tree but may not come from the
     * {@code buildHuffmanTree()} method.
     *
     * @param tree the root of the Huffman Tree
     * @return the map of each character to the encoding string it represents
     */
    public static Map<Character, EncodedString> buildEncodingMap(
            HuffmanNode tree) {
        if (tree == null) {
            throw new IllegalArgumentException();
        }
        HashMap<Character, EncodedString> encMap = new HashMap<>();
        EncodedString str = new EncodedString();
        if (tree.getLeft() == null && tree.getRight() == null) {
            str.zero();
            encMap.put(tree.getCharacter(), str);
            return encMap;
        }
        return buildEncodingMap(tree, str, encMap);
    }

    /**
     * Traverse the tree and extract the encoding for each character in the
     * tree.
     *
     * The tree provided will be a valid huffman tree but may not come from the
     * {@code buildHuffmanTree()} method.
     *
     * @param tree the root of the Huffman Tree
     * @param soFar the encoded string thus far
     * @param encMap the encoding map that will be returned
     * @return the map of each character to the encoding string it represents
     */
    private static Map<Character, EncodedString> buildEncodingMap(
            HuffmanNode tree, EncodedString soFar,
            HashMap<Character, EncodedString> encMap) {
        if (tree.getLeft() == null && tree.getRight() == null) {
            EncodedString str = new EncodedString();
            str.concat(soFar);
            encMap.put(tree.getCharacter(), str);
        }
        if (null != tree.getLeft()) {
            EncodedString str = new EncodedString();
            str.concat(soFar);
            str.zero();
            buildEncodingMap(tree.getLeft(), str, encMap);
        }
        if (null != tree.getRight()) {
            EncodedString str = new EncodedString();
            str.concat(soFar);
            str.one();
            buildEncodingMap(tree.getRight(), str, encMap);
        }
        return encMap;
    }

    /**
     * Encode each character in the string using the map provided.
     *
     * If a character in the string doesn't exist in the map ignore it.
     *
     * The encoding map may not necessarily come from the
     * {@code buildEncodingMap()} method, but will be correct for the tree given
     * to {@code decode()} when decoding this method's output.
     *
     * @param encodingMap the map of each character to the encoding string it
     * represents
     * @param s the string to encode
     * @return the encoded string
     */
    public static EncodedString encode(Map<Character, EncodedString>
            encodingMap, String s) {
        if (s == null || s.equals("") || encodingMap == null) {
            throw new IllegalArgumentException();
        }
        EncodedString str = new EncodedString();
        for (char letter : s.toCharArray()) {
            if (null != encodingMap.get(letter)) {
                str.concat(encodingMap.get(letter));
            }
        }
        return str;
    }

    /**
     * Decode the encoded string using the tree provided.
     *
     * The encoded string may not necessarily come from {@code encode()}, but
     * will be a valid string for the given tree.
     *
     * (tip: use StringBuilder to make this method faster -- concatenating
     * strings is SLOW.)
     *
     * @param tree the tree to use to decode the string
     * @param es the encoded string
     * @return the decoded string
     */
    public static String decode(HuffmanNode tree, EncodedString es) {
        if (tree == null) {
            throw new IllegalArgumentException();
        }
        Iterator<Byte> iter = es.iterator();
        StringBuilder builder = new StringBuilder();
        HuffmanNode current = tree;
        while (iter.hasNext()) {
            Byte next = iter.next();
            if (next == 0) {
                if (current.getLeft() == null && current.getRight() == null) {
                    current = tree;
                } else {
                    current = current.getLeft();
                }
            } else {
                current = current.getRight();
            }
            if (current.getLeft() == null && current.getRight() == null) {
                builder.append(current.getCharacter());
                current = tree;
            }
        }
        return builder.toString();
    }
}
