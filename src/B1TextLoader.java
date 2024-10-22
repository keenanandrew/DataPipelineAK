import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.ConcurrentHashMap;

// TODO - Fork and implement using Java Stream rather than BufferedReader
// TODO - Check everything is the right privacy, encapsulation, etc

public class B1TextLoader { // Declare a class

    ConcurrentHashMap<String, String> documents = new ConcurrentHashMap<>();

    /**
     * main method:
     * Instantiates an object of this class
     * Loads the provided text file into a ConcurrentHashMap
     * Loads the contents of the ConcurrentHashMap into a JSON Object
     * Saves the JSON Object as a .json file
     * Provide two arguments in the command line:
     *      1. The name of the text file to be lemmatised, including its file extension (.txt)
     *      2. The name of the JSON file where the text will be stored, including its file extension (.json)
     * @param args
     */

    public static void main(String[] args) {

        B1TextLoader loader = new B1TextLoader();
        loader.loadTextFile(args[0]);
        loader.saveDocumentsToJSON(args[1]);

    }

    /**
     * Second method: LoadTextFile
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

        // below method call removed as not needed for pipeline functionality
        // countWordsInDocuments(documents);
    }


    /**
     * Third method: saveDocumentsToJSON
     * This method calls various methods from the JSONIOHelper package
     * It creates the basic, empty JSON structure
     * Then adds each document (each line of the .txt file)
     * Then saves it, using the user-provided filename
     * @param filename
     */

    public void saveDocumentsToJSON(String filename) {
        JSONIOHelper jsonIO = new JSONIOHelper();
        jsonIO.createBasicJSONStructure();
        jsonIO.addDocumentsToJSONStructure(documents);
        jsonIO.saveJSON(filename);
    }

    /**
     * The below methods are commented out
     * as they do not contribute to the pipeline's functionality.
     * However they may be needed at a later stage,
     * or for debugging.
     * @param documents
     */

//    public void countWordsInDocuments(ConcurrentHashMap<String, String> documents) {
//        // This is a way to apply the function countWordsInSingleDocument to each key:value pair in documents
//        // one at a time
//        documents.forEach(this::countWordsInSingleDocument);
//    }
//
//    public void countWordsInSingleDocument(String key, String value) {
//        String[] words = value.split(" ");
//        System.out.println(key + " has " + words.length + " words.");
//    }
}


