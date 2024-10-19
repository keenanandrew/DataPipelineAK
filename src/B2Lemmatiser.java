import edu.stanford.nlp.simple.*;

public class B2Lemmatiser {
    public static void main(String[] args) {
        B2Lemmatiser lemmatiser = new B2Lemmatiser();
        lemmatiser.startLemmatisation("DataStore.json"); // for now, just prints the filename
        JSONIOHelper jsonIO = new JSONIOHelper();
        jsonIO.loadJSON("DataStore.json");

    }

    private void startLemmatisation(String filename) {
        System.out.println(filename);


    }
}
