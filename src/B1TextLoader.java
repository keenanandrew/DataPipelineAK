import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;


public class B1TextLoader { // Declare a class

    /**
     * First method: instantiate an object of this class
     * And apply other methods to it, eg loadTextFile
     * This is main, which means this is where Java starts running
     * So we need to call other methods from in here
     */

    public static void main(String[] args) {
        B1TextLoader loader = new B1TextLoader();
        loader.loadTextFile("BasicTextFile.txt");
    }

    /**
     * Second method, LoadTextFile
     * Takes filename of intended file as argument
     * Uses established File package
     * Has three steps:
     *      1. Create a File object, which is a reference to the actual file
     *      2. Create a BufferedReader object
     *      3. Iterate until we hit the end, which will be a null return
     * @param filename
     */

    public void loadTextFile(String filename) {
        File f = new File(filename); // an object, f, of class File, is created
        try {
            BufferedReader br = new BufferedReader(new FileReader(f)); // an object, br, of class BufferedReader
            String line = br.readLine();
            while(line != null) {
                System.out.println(br.ready());
                System.out.println(line);
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error while loading file: " + e.getMessage());
        }

    }
}

