import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class CountFrequency {

    private final Scanner input;

    public CountFrequency(Scanner input) {
        this.input = input;
    }
    public void run() throws IOException {
        TreeItemRec itemFreq;
        PrintStream output = new PrintStream(new File("output.txt"));
        BinarySearchTree<TreeItemRec, String> wordList = new BinarySearchTree<>();
//        Scanner input = new Scanner(System.in);
        System.out.print("Please enter a file name: ");
        String s = this.input.next();
        Integer wordCount = 0;
        int[] wordLength = new int[30];

        try {
            BufferedReader fileInput = new BufferedReader(new FileReader(s));
            while (fileInput.ready()) {
                Integer frequency = 1;
                String next = getAWord(fileInput);
                int length;

                while (!next.equals(" ")) {
                    length = next.length();
                    itemFreq = new TreeItemRec(frequency, next);
                    try {
                        String compare = wordList.retrieve(next).getKey();
                        if (!wordList.isEmpty() && compare.equals(next)) {
                            // change frequency of an existing word
                            Integer oldFreq = wordList.retrieve(next).getCount();
                            oldFreq++;
                            wordList.retrieve(next).setCount(oldFreq);
                        }
                    } catch (NullPointerException e) {
                        // new word here
                        wordList.insert(itemFreq);
                        wordLength[length]++;
                    }
                    wordCount++;
                    next = getAWord(fileInput);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        printText(s, wordCount, output, wordLength, wordList);
        System.out.println("Output written to: \"output.txt\"");
    }

    public void printText(String s, Integer wordCount, PrintStream output, int[] wordLength,
                                 BinarySearchTree<TreeItemRec, String> wordList) {
        output.println("File Processed: " + s);
        output.println();
        output.println("Total Words Processed: " + wordCount);
        int sum = 0;
        for (int i = 0; i < 30; i++) {
            sum += wordLength[i];
        }
        output.println("Total Different Words: " + sum);
        output.printf("\n%-20s %10s %10s", "WORD", "FREQUENCY", "FREQ %");
        output.println();
        traverseInorder(wordList, wordCount, output);
        output.printf("\n\n%-20s %10s", "Word Length", "Number of Distinct Words\n");
        for (int i = 1; i < 30; i++) {
            output.printf("\n%6s %25s", i, wordLength[i]);
        }
    }

    public void traverseInorder(BinarySearchTree<TreeItemRec, String> aTree, Integer wordCount, PrintStream fileOutput) {
        TreeIterator<TreeItemRec> output = new TreeIterator<>(aTree);
        output.setInorder();

        DecimalFormat f = new DecimalFormat("0.000");
        while (output.hasNext()) {
            TreeItemRec item = output.next();
            float percent = (item.getCount() * 100.0f) / wordCount;
            fileOutput.printf("\n%-20s %5s %15s", item.getKey(), item.getCount(), f.format(percent));
        }
    }

    public String getAWord(BufferedReader fileInput) throws IOException {
        char word = (char) fileInput.read();
        StringBuilder outputBuffer = new StringBuilder(20);
        while (!Character.isLetter(word) && fileInput.ready()) {
            word = (char) fileInput.read();
        }
        if (fileInput.ready()) {
            outputBuffer.append(word);
            word = (char) fileInput.read();
            while (Character.isLetter(word) && fileInput.ready()) {
                outputBuffer.append(word);
                word = (char) fileInput.read();
            }
        } else {
            return " ";
        }
        return outputBuffer.toString().toLowerCase();
    }
}
