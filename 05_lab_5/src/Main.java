public class Main {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        System.out.println("-------------Inverted Index-----------------");
        SPIMI index = new SPIMI();
        System.out.println("Scanned words: " + index.getScannedWordsCount());
        System.out.println("Dictionary size: " + index.getDictionarySize());
        System.out.println(index.findWord("america"));

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("time:" + totalTime); //2219 with  Map<String, Map<Integer>> dictionary;
        System.out.println("Free memory (MBytes): " +
                Runtime.getRuntime().freeMemory()/1024.0/1024.0);

    }
}
