import java.io.BufferedReader;
import java.io.File
import java.io.FileReader;

public class B1TextLoader {

    public static void main(String[] args) {
        B1TextLoader loader = new B1TextLoader();
        loader.loadTextFile("BasicTextFile.txt");
    }
    public void loadTextFile(String filename) {
        System.out.println("Loading your file, beep boop...");

        File f = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(f));
    }
}

