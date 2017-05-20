import java.io.IOException;
import java.util.Scanner;

public class WordCounter {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        CountFrequency count = new CountFrequency(input);
        try {
            count.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
