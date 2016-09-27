/**
 * Created by alex on 9/27/16.
 */
public class Tester {

    public static void main(String[] args) {
        BigramIndex index = new BigramIndex();
        System.out.println("Scanned words: " + index.getScannedWordsCount());
        System.out.println("Dictionary size: " + index.getDictionarySize());
        System.out.println(index.findWord("title anna"));
        System.out.println(index.getDictionary());
    }
}
