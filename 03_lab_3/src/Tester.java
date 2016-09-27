/**
 * Created by alex on 9/27/16.
 */
public class Tester {

    public static void main(String[] args) {
        BiIndex index = new BiIndex();
        System.out.println("Scanned words: " + index.getScannedWordsCount());
        System.out.println("Dictionary size: " + index.getDictionarySize());
        System.out.println(index.findWord("likes it"));
//        System.out.println(index.getDictionary());
    }
}
