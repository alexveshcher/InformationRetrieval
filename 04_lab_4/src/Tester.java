public class Tester {

    public static void main(String[] args) {
        ThreegramIndex index = new ThreegramIndex();
        System.out.println("Scanned words: " + index.getScannedWordsCount());
        System.out.println("Dictionary size: " + index.getDictionarySize());
        System.out.println(index.findWord("title anna"));
        System.out.println(index.getDictionary());

    }
}
