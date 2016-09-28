import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * output example
 * america: [0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0]
 */
public class IncidenceMatrix {
    private Map<String, byte[]> dictionary;
    private List<String> files;
    private int scanned_words_count;

    public IncidenceMatrix(){
        dictionary = new HashMap<>();
        files = getFiles();
        getTokensFromAllFiles();
    }

    public int getScannedWordsCount() {
        return scanned_words_count;
    }

    public Map<String, byte[]> getDictionary() {
        return dictionary;
    }

    public String findWord(String word){
        String res = word+ ": ";// + dictionary.get(word);
        for(int i = 0; i < files.size(); i++){
            res+= dictionary.get(word)[i];
        }
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
            if(!dictionary.containsKey(token)){
                byte[] status = new byte[files.size()];
                status[file_id] = 0x01;
                dictionary.put(token,status);
            }
            dictionary.get(token)[file_id] = 0x01;
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
        for(int i = 0; i < files.size(); i++ ){
            readTokensFromFile("files/"+files.get(i),i);
        }
    }
}
