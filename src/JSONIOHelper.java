import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// TODO - put methods in a sensible order, bringing together similar ones
// TODO - Check if any can be further abstracted

public class JSONIOHelper {

    // Declaring the JSONObjects to be used later
    JSONObject rootObject;
    JSONObject documentsObject;
    JSONObject lemmasObject;

    /**
     * First method: createBasicJSONStructure
     * This method creates three JSONObjects:
     * rootObject, the outer wrapper
     * documentsObject, the inner content pre-lemmatisation
     * lemmasObject, the inner content post-lemmatisation
     */

    public void createBasicJSONStructure() {

        rootObject = new JSONObject();
        documentsObject = new JSONObject();
        lemmasObject = new JSONObject();

        // defining the hierarchy
        rootObject.put("documents", documentsObject);
        rootObject.put("lemmas", lemmasObject);

    }

    /**
     * Second method: addDocumentsToJSONStructure
     * This method goes through each 'entry' (that's a k:v pair)
     * in my ConcurrentHashMap, 'documents'
     * gets the key and the value out
     * and 'puts' them in documentsObject
     * @param documents
     */
    public void addDocumentsToJSONStructure(ConcurrentHashMap<String, String> documents) {

        // for each k:v pair in the 'documents' ConcurrentHashMap
        // put them into the documentsObject
        for(Map.Entry<String, String> entry : documents.entrySet()) {
            documentsObject.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Third method: saveJSON
     * This creates a file, so that information can be passed from B1 to B2
     * This method takes the contents of rootObject (including child objects)
     * Turns it into a string
     * And then uses a FileWriter to write that string
     * To the file specified via...
     * @param filename
     * It also uses a try/catch block, which will let us know if an exception is thrown
     * And close the FileWriter for us!
     */

    public void saveJSON(String filename) {

        // simple method for turning JSONObject into a string
        String jsonString = rootObject.toJSONString();

        try(FileWriter writer = new FileWriter(filename)) {
            writer.write(jsonString);
            System.out.println("JSON save successful");

        } catch (Exception e){
            System.out.println("JSON save unsuccessful");
        }

        // the try-catch block will close FileWriter for us
    }

    /**
     * Fourth method: loadJSON
     * The reverse of saveJSON: this takes a .json file
     * @param filename
     * and turns it back into a JSONObject
     */

    public void loadJSON(String filename) {

        // the empty root / documents / lemmas structure
        createBasicJSONStructure();

        try(FileReader file = new FileReader(filename)) {

            // Uses external library, json.simple
            JSONParser parser = new JSONParser();

            // the bit in brackets is specifying an expected data type
            rootObject = (JSONObject) parser.parse(file);

            // check if documents exists
            if(rootObject.get("documents") != null) {
                // and populate it
                documentsObject = (JSONObject) rootObject.get("documents");
            }

            // check if lemmas exists
            if(rootObject.get("lemmas") != null) {

                // and populate it too
                lemmasObject = (JSONObject) rootObject.get("lemmas");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Successfully loaded " + documentsObject.size() + " documents");
        }

        // the try-catch-finally block will close the FileReader for us


    }


    /**
     * Fifth method: getDocumentsFromJSONStructure
     * This takes the JSONObject populated by loadJSON
     * And in turn, populates a ConcurrentHashMap with it
     * Which enables us to then do things to the contents - ie, lemmatise them
     * @return
     */

    public ConcurrentHashMap<String, String> getDocumentsFromJSONStructure() {

        ConcurrentHashMap<String, String> documents = new ConcurrentHashMap<String, String>(); // just creates an empty CHM

        // goes through each key in documentsObject
        for(String key: (Iterable<String>)documentsObject.keySet()){

            // and adds that key, and its value, to the 'documents' ConcurrentHashMap
            documents.put(key, (String)documentsObject.get(key));
        }
        return documents;


    }

    /**
     * Sixth method: getLemmasFromJSONStructure
     * Not actually used, so I have to assume this is for the later modules
     * Perhaps called from B3 or B4
     * @return
     */

    public ConcurrentHashMap<String,String> getLemmasFromJSONStructure(){

        ConcurrentHashMap<String, String> lemmas = new ConcurrentHashMap<String, String>(); // just creates an empty CHM

        for(String key: (Iterable<String>)lemmasObject.keySet()){
            lemmas.put(key, (String)lemmasObject.get(key));
        }
        return lemmas;
    }

    /**
     * Seventh method: addLemmasToJSONStructure
     * Goes through the 'lemmas' ConcurrentHashMap
     * And adds each k:v pair to the 'lemmasObject' part of the JSONObject
     * @param lemmas
     */

    public void addLemmasToJSONStructure(ConcurrentHashMap<String, String> lemmas) {

        for(Map.Entry<String, String> entry : lemmas.entrySet()) {
            lemmasObject.put(entry.getKey(), entry.getValue());
        }
        rootObject.put("lemmas", lemmasObject);

    }

}
