import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by alex on 10/18/16.
 */
public class CompressedIndex {

    public CompressedIndex(){
        getInputIndex("input/pre_index.txt");
    }

    private Map<String, List<Integer>> getInputIndex(String file_name){

        Map<String, List<Integer>> block_dictionary = null;
        try {
            block_dictionary = new HashMap<>();
            FileWriter writer = null;
            writer = new FileWriter("compressed_index.txt", true);
            Scanner in = null;

            in = new Scanner(new File(file_name));

            while (in.hasNext()) {
                String a = in.next();
                List<Integer> b = new ArrayList<>();
                block_dictionary.put(a, b);
                int prev = 0;
                while (in.hasNextInt()) {
                    if(b.size() == 0){
                        prev = Integer.valueOf(in.next());
                        b.add(prev);
                    }
                    else {
                        int current = Integer.valueOf(in.next());
                        b.add(current - prev);
                        prev = current;
                    }
                }
                //System.out.println(a + " " + b);
                writer.write(a + " " + b + "\n");
            }
            in.close();
        } catch (IOException e){
            System.out.println(e.toString());
        }

        return block_dictionary;
    }
}
