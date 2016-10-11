import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class SPIMI {
    private String DIR = "files/";
    private Map<String, Set<Integer>> dictionary;
    private int scanned_words_count;

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
     * Scans specific file for tokens
     */
    private void readTokensFromFile(String file_path, int file_id) {
        File file = new File(file_path);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //you may want to improve this expression
        scanner.useDelimiter("[^A-Za-z]+");
        while (scanner.hasNext()) {
            scanned_words_count++;
            String token = scanner.next().toLowerCase();
            if (dictionary.containsKey(token)) {
                dictionary.get(token).add(file_id);
                continue;
            }
            Set<Integer> file_ids = new HashSet<>();
            file_ids.add(file_id);
            dictionary.put(token, file_ids);
        }
        writeBlockToDisk(dictionary, "output");
    }

    /**
     * Get list of all file names
     */
    private Map<Integer, String> getFiles() {
        Map<Integer, String> files = new HashMap<>();
        File dir = new File(DIR);
        String[] files_in_dir = dir.list();
        for (int i = 0; i < files_in_dir.length; i++) {
            files.put(i, files_in_dir[i]);
        }
        return files;
    }

    /**
     * This method goes through all files
     * and implements void readTokensFromFile(String file_path, int file_id)
     */
    private void getTokensFromAllFiles() {
        Map<Integer, String> files = getFiles();
        for (int i = 0; i < files.size(); i++) {
            readTokensFromFile(DIR + files.get(i), i);
        }
    }

    private void writeBlockToDisk(Map<String, Set<Integer>> dictionary, String output_file) {
        try {
            FileWriter writer = null;
            writer = new FileWriter(output_file, true);


        for (Map.Entry<String, Set<Integer>> entry : dictionary.entrySet()) {
            String key = entry.getKey();
            Set<Integer> value = entry.getValue();
            writer.write(key + "\n");
        }
        writer.close();


    } catch (IOException e) {
            e.printStackTrace();
        }

    }

}