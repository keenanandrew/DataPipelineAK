import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.ConcurrentHashMap;

// TODO - Tidy all comments and function explanations
// TODO - Remove all unnecessary print statements
// TODO - Put methods in a sensible order
// TODO - Check everything is the right privacy, encapsulation, etc

public class B1TextLoader { // Declare a class

    ConcurrentHashMap<String, String> documents = new ConcurrentHashMap<>();

    /**
     * First method: instantiate an object of this class
     * and apply other methods to it, e.g. loadTextFile.
     * This is main, which means this is where Java starts running.
     * So we need to call other methods from in here
     */

    /**
     *
     * @param args
     */

    public static void main(String[] args) {
        B1TextLoader loader = new B1TextLoader();
        loader.loadTextFile(args[0]);
        loader.saveDocumentsToJSON(args[1]);
    }

    /**
     * Second method, LoadTextFile
     * Takes filename of intended file as argument
     * Uses established File package
     * Has three steps:
     *      1. Create a File object, which is a reference to the actual file
     *      2. Create a BufferedReader object
     *      3. Iterate until we hit the end, which will be a null return
     * @param filename
     */

    public void loadTextFile(String filename) {
        File f = new File(filename); // an object, f, of class File, is created
        try {
            BufferedReader br = new BufferedReader(new FileReader(f)); // an object, br, of class BufferedReader
            String line = br.readLine();
            int counter = 0;
            while(line != null) {
                if(line.trim().length() > 0) { // trims whitespace and removes blank lines
                    documents.put("doc" + counter, line);
                    counter++;
                }
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error while loading file: " + e.getMessage());
        }
        System.out.println("Loading complete. Documents loaded: " + documents.size());
        countWordsInDocuments(documents);
    }


    // TODO - Fork and implement using Java Stream rather than BufferedReader

    public void countWordsInDocuments(ConcurrentHashMap<String, String> documents) {
        // This is a way to apply the function countWordsInSingleDocument to each key:value pair in documents
        // one at a time
        documents.forEach(this::countWordsInSingleDocument);
    }

    public void countWordsInSingleDocument(String key, String value) {
        String[] words = value.split(" ");
        System.out.println(key + " has " + words.length + " words.");
    }

    public void saveDocumentsToJSON(String filename) {
        JSONIOHelper jsonIO = new JSONIOHelper();
        jsonIO.createBasicJSONStructure();
        jsonIO.addDocumentsToJSONStructure(documents);
        jsonIO.saveJSON(filename);
    }
}


