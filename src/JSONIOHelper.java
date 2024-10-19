import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JSONIOHelper {

    // declare the objects - not initialised though
    // just placeholders
    JSONObject rootObject;
    JSONObject documentsObject;

    /**
     * This method creates two JSONObjects
     * rootObject, the outer wrapper
     * documentsObject, the inner content
     */
    public void createBasicJSONStructure() {

        rootObject = new JSONObject();
        documentsObject = new JSONObject();

        rootObject.put("documents", documentsObject);

    }

    /**
     * This method goes through each 'entry'
     * (that's a k:v pair)
     * in my ConcurrentHashMap, 'documents'
     * gets the key and the value out
     * and 'puts' them in documentsObject
     * @param documents
     */
    public void addDocumentsToJSONStructure(ConcurrentHashMap<String, String> documents) {

        // documentsObject.putAll(documents);

        for(Map.Entry<String, String> entry : documents.entrySet()) {
            documentsObject.put(entry.getKey(), entry.getValue());
        }
    }


    /**
     * This method takes the contents of rootObject
     * Including its child objects!
     * Turns it into a string
     * And then uses a FileWriter to write that string
     * To the file specified via...
     * @param filename
     * It also uses a try/catch block
     * Which will let us know if an exception is thrown
     * And close the FileWriter for us!
     */

    public void saveJSON(String filename) {

        String jsonString = rootObject.toJSONString();

        try(FileWriter writer = new FileWriter(filename)) {
            writer.write(jsonString);
            System.out.println("JSON save successful");

        } catch (Exception e){
            System.out.println("JSON save unsuccessful");
        }
    }

    public void loadJSON(String filename) {

        createBasicJSONStructure();

        try(FileReader file = new FileReader(filename)) {

            JSONParser parser = new JSONParser();
            rootObject = (JSONObject) parser.parse(file);

            if(rootObject.get("documents") != null) {
                documentsObject = (JSONObject) rootObject.get("documents");

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Right, loaded it. Documents: " + documentsObject.size());
        }


    }
}
