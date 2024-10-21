import edu.stanford.nlp.simple.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class B2Lemmatiser {

    public static void main(String[] args) {
    B2Lemmatiser lemmatiser = new B2Lemmatiser();
    lemmatiser.startLemmatisation("DataStore.json");
    }

    private void startLemmatisation(String filename) {

        // System.out.println(filename);
        JSONIOHelper jsonIO = new JSONIOHelper();
        jsonIO.loadJSON("DataStore.json");
        ConcurrentHashMap<String, String> documents = jsonIO.getDocumentsFromJSONStructure();
        ConcurrentHashMap<String, String> lemmatised = new ConcurrentHashMap<>();

        System.out.println("And done something with a CHM! Length: " + documents.size());

        for(Map.Entry<String, String> entry : documents.entrySet()) {
            lemmatised.put(entry.getKey(), lemmatiseSingleDocument(entry.getValue()));
        }

        jsonIO.addLemmasToJSONStructure(lemmatised);
        jsonIO.saveJSON(filename);

    }

    private String lemmatiseSingleDocument(String text) {

        text = text.replaceAll("\\p{Punct}", " ");
        text = text.replaceAll("\\s+", " ");
        text = text.toLowerCase().trim();

        Sentence sentence = new Sentence(text);
        List<String> lemmas = sentence.lemmas();
        text = String.join(" ", lemmas);
        System.out.println("Now in lemmatiseSingleDocument! " + text);
        return text;


    }
}
