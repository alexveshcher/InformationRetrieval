import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class InvertedIndex {

    private Map<String, Set<Integer>> dictionary;
    private int scanned_words_count;

    public InvertedIndex(){
        dictionary = new HashMap<>();
        getTokensFromAllFiles();
    }

    public int getScannedWordsCount() {
        return scanned_words_count;
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
            scanned_words_count++;
            String token = scanner.next().toLowerCase();
            if(dictionary.containsKey(token)){
                dictionary.get(token).add(file_id);
                continue;
            }
            Set<Integer> file_ids = new HashSet<>();
            file_ids.add(file_id);
            dictionary.put(token,file_ids);
        }
    }

    /** Get list of all file names */
    private Map<Integer, String> getFiles(){
        Map<Integer, String > files = new HashMap<>();
        File dir = new File("files/");
        String[] files_in_dir = dir.list();
        for(int i = 0; i < files_in_dir.length; i++){
            files.put(i,files_in_dir[i]);
        }
        return files;
    }

    /**
     *  This method goes through all files
     *  and implements void readTokensFromFile(String file_path, int file_id)
     */
    private void getTokensFromAllFiles(){
        Map<Integer, String> files = getFiles();
        for(int i = 0; i < files.size(); i++ ){
            readTokensFromFile("files/"+files.get(i),i);
        }
    }
}
