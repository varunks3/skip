import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class text2 {
    public static void main(String[] args) throws IOException {

        // Define the text to write
        String textToWrite = "127.0.0.1";

        // Open all kpas in write mode and write the text to each kpa
        File kgc = new File("KGC/kgc.txt");
        File kpa1 = new File("KGC/kpa1.txt");
        File kpa2 = new File("KGC/kpa2.txt");
        File kpa3 = new File("KGC/kpa3.txt");
        File kpa4 = new File("KGC/kpa4.txt");
        File kpa5 = new File("KGC/kpa5.txt");
        File kpa6 = new File("KGC/kpa6.txt");


        BufferedWriter writer7 = new BufferedWriter(new FileWriter(kgc));
        BufferedWriter writer1 = new BufferedWriter(new FileWriter(kpa1));
        BufferedWriter writer2 = new BufferedWriter(new FileWriter(kpa2));
        BufferedWriter writer3 = new BufferedWriter(new FileWriter(kpa3));
        BufferedWriter writer4 = new BufferedWriter(new FileWriter(kpa4));
        BufferedWriter writer5 = new BufferedWriter(new FileWriter(kpa5));
        BufferedWriter writer6 = new BufferedWriter(new FileWriter(kpa6));

        writer1.write(textToWrite);
        writer2.write(textToWrite);
        writer3.write(textToWrite);
        writer4.write(textToWrite);
        writer5.write(textToWrite);
        writer6.write(textToWrite);
        writer7.write(textToWrite);

        // Close all writers
        writer1.close();
        writer2.close();
        writer3.close();
        writer4.close();
        writer5.close();
        writer6.close();
        writer7.close();
    }
}
