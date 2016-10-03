/**
 * Created by alex on 9/27/16.
 */
public class Tester {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        System.out.println("-------------Inverted Index-----------------");
        InvertedIndex index = new InvertedIndex();
        System.out.println("Scanned words: " + index.getScannedWordsCount());
        System.out.println("Dictionary size: " + index.getDictionarySize());
        System.out.println(index.findWord("america"));

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("time:" + totalTime); //2219 with  Map<String, Map<Integer>> dictionary;

        startTime = System.currentTimeMillis();

        System.out.println("\n-------------Incidence Matrix-----------------");
        IncidenceMatrix matrix = new IncidenceMatrix();
        System.out.println("Scanned words: " + matrix.getScannedWordsCount());
        System.out.println("Dictionary size: " + matrix.getDictionarySize());
        System.out.println(matrix.findWord("america"));

        endTime   = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("time:" + totalTime);
    }
}
