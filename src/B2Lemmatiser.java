import edu.stanford.nlp.simple.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class B2Lemmatiser {

    /**
     * main method:
     * Instantiates an object of this class
     * Applies the startLemmatisation() method to it
     * Provide one argument in the command line: the name of the .json file to be lemmatised
     * Include the .json extension.
     * @param args
     */
    public static void main(String[] args) {

        B2Lemmatiser lemmatiser = new B2Lemmatiser();
        lemmatiser.startLemmatisation(args);

    }

    /**
     * Second method: startLemmatisation
     * Takes the user-provided argument as its parameter
     * See in-line commenting for explanation of how it works
     * @param args
     */

    private void startLemmatisation(String[] args) {

        // Uses jsonIOhelper methods
        JSONIOHelper jsonIO = new JSONIOHelper();
        jsonIO.loadJSON(args[0]);

        // This ConcurrentHashMap is populated from the JSON file specified by the user
        ConcurrentHashMap<String, String> documents = jsonIO.getDocumentsFromJSONStructure();

        // This ConcurrentHashMap will store the lemmatised strings
        ConcurrentHashMap<String, String> lemmatised = new ConcurrentHashMap<>();

        // For each entry (key:value pair) in the specific map ('documents', in this case)...
        for(Map.Entry<String, String> entry : documents.entrySet()) {
            // the below line adds the key as-is, and applies lemmatiseSingleDocument to the value
            lemmatised.put(entry.getKey(), lemmatiseSingleDocument(entry.getValue()));
        }

        // Adds the lemmatised strings in CHM 'lemmatised' to the JSON Object
        jsonIO.addLemmasToJSONStructure(lemmatised);
        // Saves the JSONObject as a .json file, using the specified name
        jsonIO.saveJSON(args[0]);

    }

    /**
     * Third method: lemmatiseSingleDocument
     * This takes in one string at a time - taken from the values of the 'document' CHM
     * It has three steps:
     *      1. Simple regex queries to clean the string
     *      2. Uses Stanford's CoreNLP library to lemmatise the string
     *      3. Converts the list of strings back into a single string
     * @param text
     * @return
     */

    private String lemmatiseSingleDocument(String text) {

        // The regex step

        text = text.replaceAll("\\p{Punct}", " "); // removes punctuation
        text = text.replaceAll("\\s+", " "); // turns all double (or more) spaces into a single space
        text = text.toLowerCase().trim(); // converts it to lower case, and strips leading & trailing spaces

        // The lemmatisation step

        Sentence sentence = new Sentence(text); // An object defined in the Stanford CoreNLP library
        List<String> lemmas = sentence.lemmas(); // calls the lemmatisation method from the Stanford library
        text = String.join(" ", lemmas); // turns list of strings back into one string, with space as delimiter
        return text;


    }
}
