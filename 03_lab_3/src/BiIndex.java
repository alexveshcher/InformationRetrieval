import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by alex on 9/27/16.
 * This is a mixed class which might me useful all labs 1-3
 * It's an inverted index implementation
 */
public class BiIndex {

    private Map<String, List<Integer>> dictionary;
    private int scanned_words_count;

    public BiIndex(){
        dictionary = new HashMap<>();
        getTokensFromAllFiles();
    }

    public int getScannedWordsCount() {
        return scanned_words_count;
    }

    public Map<String, List<Integer>> getDictionary() {
        return dictionary;
    }

    public String findWord(String word){
        String res = word+ ": " + dictionary.get(word);
        return res;
    }

    public int getDictionarySize(){
        return dictionary.size();
    }


    /** Scans specific file for tokens */
    private void readTokensFromFile(String file_path, int file_id){
        File file = new File(file_path);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //you may want to improve this expression
        scanner.useDelimiter("[^A-Za-z]+");
        while(scanner.hasNext()){
            String token = scanner.next().toLowerCase();
            if(scanner.hasNext())
                token += " " + scanner.next().toLowerCase();
            if(dictionary.containsKey(token)){ //&& !dictionary.get(token).contains(file_id)){
                if(!dictionary.get(token).contains(file_id)){
                    dictionary.get(token).add(file_id);
                    dictionary.put(token, dictionary.get(token));
                }
            }
            else {
                List<Integer> integers = new ArrayList<>();
                integers.add(file_id);
                dictionary.put(token,integers);
            }
            scanned_words_count++;
        }
    }

    /** Get list of all file names */
    private List<String> getFiles(){
        List<String> files = new ArrayList<>();
        File dir = new File("files/");
        String[] files_in_dir = dir.list();
        for(String i : files_in_dir){
            files.add(i);
        }
        return files;
    }

    /**
     *  This method goes through all files
     *  and implements void readTokensFromFile(String file_path, int file_id)
     */
    private void getTokensFromAllFiles(){
        List<String> files = getFiles();
        for(int i = 0; i < files.size(); i++ ){
            readTokensFromFile("files/"+files.get(i),i);
        }
    }
}
