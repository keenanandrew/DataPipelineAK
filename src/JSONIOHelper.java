import org.json.simple.JSONObject;

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

        for()
    }
}
