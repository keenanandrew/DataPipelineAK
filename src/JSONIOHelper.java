import org.json.simple.JSONObject;

public class JSONIOHelper {

    // declare the objects - not initialised though
    // just placeholders
    JSONObject rootObject;
    JSONObject documentsObject;

    public void createBasicJSONStructure() {

        rootObject = new JSONObject();
        documentsObject = new JSONObject();

    }
}
