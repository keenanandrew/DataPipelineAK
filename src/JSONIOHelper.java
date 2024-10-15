import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JSONIOHelper {

    // declare the objects - not initialised though
    // just placeholders
    JSONObject rootObject;
    JSONObject documentsObject;

    public void createBasicJSONStructure() {

        rootObject = new JSONObject();
        documentsObject = new JSONObject();

        rootObject.put("documents", documentsObject);

    }

    public void addDocumentsToJSONStructure(ConcurrentHashMap<String, String> documents) {

        // documentsObject.putAll(documents);

        for(Map.Entry<String, String> entry : documents.entrySet()) {
            documentsObject.put(entry.getKey(), entry.getValue());
        }
    }

    public void saveJSON(String filename) {

        String jsonString = rootObject.toJSONString();

        try(FileWriter writer = new FileWriter(filename)) {
            writer.write(jsonString);
            System.out.println("JSON save successful");

        } catch (Exception e){
            System.out.println("JSON save unsuccessful");
        }
    }
}
