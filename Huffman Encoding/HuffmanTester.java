import java.util.Map;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
 
public class HuffmanTester {
 
    @Test
    public void testHuffman() throws Exception {
        testEncodeDecode("a");
        testEncodeDecode("aa");
        testEncodeDecode("aaa");
        testEncodeDecode("aaaa");
        testEncodeDecode("aaaaa");
        testEncodeDecode("ab");
        testEncodeDecode("1");
        testEncodeDecode("101");
        testEncodeDecode(" !\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~");
        testEncodeDecode(" !\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~" +
                " !\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~");
 
        StringBuilder s = new StringBuilder(100000);
        for (int i = 0; i < 100000; i++) {
            s.append((char) (((i % 127) + 32) % 127));
        }
        testEncodeDecode(s.toString());
    }
 
    private void testEncodeDecode(String s) {
        Map<Character, Integer> map = Huffman.buildFrequencyMap(s);
        HuffmanNode node = Huffman.buildHuffmanTree(map);
        Map<Character, EncodedString> encodedmap = Huffman.buildEncodingMap(node);
        EncodedString es = Huffman.encode(encodedmap, s);
        String str = Huffman.decode(node, es);
        assertEquals("Encode should equal", s, str);
    }
}