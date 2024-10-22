import edu.stanford.nlp.simple.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// TODO - Put methods in a sensible order
// TODO - Complete commenting and explanation of functions / methods

public class B2Lemmatiser {

    public static void main(String[] args) {
    B2Lemmatiser lemmatiser = new B2Lemmatiser();
    lemmatiser.startLemmatisation(args);
    }

    private void startLemmatisation(String[] args) {

        JSONIOHelper jsonIO = new JSONIOHelper();
        jsonIO.loadJSON(args[0]);
        ConcurrentHashMap<String, String> documents = jsonIO.getDocumentsFromJSONStructure();
        ConcurrentHashMap<String, String> lemmatised = new ConcurrentHashMap<>();


        for(Map.Entry<String, String> entry : documents.entrySet()) {
            lemmatised.put(entry.getKey(), lemmatiseSingleDocument(entry.getValue()));
        }

        jsonIO.addLemmasToJSONStructure(lemmatised);
        jsonIO.saveJSON(args[0]);

    }

    private String lemmatiseSingleDocument(String text) {

        text = text.replaceAll("\\p{Punct}", " ");
        text = text.replaceAll("\\s+", " ");
        text = text.toLowerCase().trim();

        Sentence sentence = new Sentence(text);
        List<String> lemmas = sentence.lemmas();
        text = String.join(" ", lemmas);
        return text;


    }
}
