/**
 * Created by alex on 9/27/16.
 */
public class Tester {

    public static void main(String[] args) {
        System.out.println("-------------Inverted Index-----------------");
        InvertedIndex index = new InvertedIndex();
        System.out.println("Scanned words: " + index.getScannedWordsCount());
        System.out.println("Dictionary size: " + index.getDictionarySize());
        System.out.println(index.findWord("america"));

        System.out.println("\n-------------Incidence Matrix-----------------");
        IncidenceMatrix matrix = new IncidenceMatrix();
        System.out.println("Scanned words: " + matrix.getScannedWordsCount());
        System.out.println("Dictionary size: " + matrix.getDictionarySize());
        System.out.println(matrix.findWord("america"));
    }
}
