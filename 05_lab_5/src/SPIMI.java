import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class SPIMI {
    private String DIR = "files/";
    private Map<String, List<Integer>> dictionary;
    private int scanned_words_count;
    private int block_count;

    public SPIMI() {
        dictionary = new HashMap<>();
        getTokensFromAllFiles();
    }

    public int getScannedWordsCount() {
        return scanned_words_count;
    }

    public String findWord(String word) {
        String res = word + ": " + dictionary.get(word);
        return res;
    }

    public int getDictionarySize() {
        return dictionary.size();
    }



    /**
     * Get list of all file names
     */
    private List<String> getFiles() {
        File dir = new File(DIR);
        List<String> files = Arrays.asList(dir.list());
        return files;
    }

    /**
     * This method goes through all files
     * and implements void readTokensFromFile(String file_path, int file_id)
     */
    private void getTokensFromAllFiles() {
        List<String> files = getFiles();
        for (int i = 0; i < files.size(); i++) {

            File file = new File(DIR + files.get(i));
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //you may want to improve this expression
            scanner.useDelimiter("[^A-Za-z]+");


            while (scanner.hasNext() /* and memory available*/) {
                scanned_words_count++;
                String token = scanner.next().toLowerCase();
                if (dictionary.containsKey(token)) {
                    if(!dictionary.get(token).contains(i)){
                        dictionary.get(token).add(i);
                    }
                    continue;
                }
                List<Integer> file_ids = new ArrayList<>();
                file_ids.add(i);
                dictionary.put(token, file_ids);
                if(Runtime.getRuntime().freeMemory() < 10){ //memory is full
                    writeBlockToDisk(dictionary, "output" + block_count++);
                    dictionary = new HashMap<>();
                }
            }
        }
        writeBlockToDisk(dictionary, "output" + block_count++);
        //readBlock("output0");
    }

    private void writeBlockToDisk(Map<String, List<Integer>> dictionary, String output_file) {
        try {
            FileWriter writer = null;
            writer = new FileWriter(output_file, true);
        for(Map.Entry<String, List<Integer>> entry : dictionary.entrySet()) {
            String key = entry.getKey();
            List<Integer> value = entry.getValue();
            String res = key + " ";
            for(int i : value){
                res+= i + " ";
            }
            writer.write(res + "\n");
        }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mergeOutput(){
        for(int i = 0; i < block_count; i++){

        }
    }

    private void readBlock(String file_name){
        Scanner x = null;
        try {
            x = new Scanner(new File(file_name));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(x.hasNext()){
            String a = x.next();
            System.out.println(a);
        }
        x.close();
    }

}