import edu.stanford.nlp.simple.*;

import java.util.concurrent.ConcurrentHashMap;

public class B2Lemmatiser {
    public static void main(String[] args) {
        B2Lemmatiser lemmatiser = new B2Lemmatiser();
        lemmatiser.startLemmatisation("DataStore.json"); // for now, just prints the filename
        lemmatiser.lemmatiseSingleDocument("DataStore.json");


    }

    private void startLemmatisation(String filename) {

        System.out.println(filename);
        JSONIOHelper jsonIO = new JSONIOHelper();
        jsonIO.loadJSON("DataStore.json");

        ConcurrentHashMap<String, String> documents = jsonIO.getDocumentsFromJSONStructure();
        System.out.println("And done something with a CHM");

    }

    private String lemmatiseSingleDocument(String text) {
        System.out.println("Yep works - look: " + text);
        return text;


    }
}
