import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ThreegramIndex {

    private Map<String, List<Integer>> dictionary;
    private int scanned_words_count;

    public ThreegramIndex(){
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
        String token = null;
        String last_token = null;
        String last_last_token = null;
        while(scanner.hasNext()){
            if(last_token != null){
                token = last_token;
            }
            if(last_last_token != null){
                last_token = last_last_token;
            }
            else
                token = scanner.next().toLowerCase();
            if(scanner.hasNext()) {
                last_last_token = scanner.next().toLowerCase();
                token += " " + last_token + " " +  last_last_token;
            }


            if(!dictionary.containsKey(token)){
                List<Integer> integers = new ArrayList<>();
                integers.add(file_id);
                dictionary.put(token,integers);
            }
            else if(!dictionary.get(token).contains(file_id))
                dictionary.get(token).add(file_id);
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