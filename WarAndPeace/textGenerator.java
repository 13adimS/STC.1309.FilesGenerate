package STC1309.WarAndPeace;

import java.io.Serializable;
import java.io.*;
import java.util.Random;

public class textGenerator implements Serializable {

    String[] wordsArray;
    int probability;

    public textGenerator() {
        String[] wordsArray = generateWords(1000);   // Array of 1<n<1000 words
        this.wordsArray = wordsArray;
    }

    public String[] generateWords(int numberOfWords) {
        String[] randomString = new String[numberOfWords];
        Random random = new Random();
        for (int i = 0; i < numberOfWords; i++) {
            char[] word = new char[random.nextInt(15) + 1];
            for (int j = 0; j < word.length; j++) {
                word[j] = (char) ('a' + random.nextInt(26));
            }
            randomString[i] = new String(word);
        }
        return randomString;
    }

    public String generateSentence() {
        Random random = new Random();
        String[] endSymbols = {". ", "? ", "! ",};
        int probability = random.nextInt(this.probability) + 1;

        String[] sentenceWordsArray = generateWords(random.nextInt(15) + 1);

        String sentence = sentenceWordsArray[0].substring(0, 1).toUpperCase() + sentenceWordsArray[0].substring(1);

        int i = 1;
        while (i < sentenceWordsArray.length) {
            sentence += ", " + sentenceWordsArray[i];
            i++;
        }

        if (1 / probability == 1) {
            sentence += wordsArray[random.nextInt(wordsArray.length) + 1];
        }

        sentence = sentence + endSymbols[random.nextInt(endSymbols.length)];
        return sentence;
    }

    public String generateParagraph() {
        Random random = new Random();
        String paragraph = generateSentence();
        for (int i = 1; i < random.nextInt(20); i++) {
            paragraph = paragraph + generateSentence();
        }
        paragraph += "\n";
        return paragraph;
    }

    public void generateFiles(String path, int n, int size, int probability) {
        this.probability = probability;
        for (int i = 1; i < n; i++) {
            String filepath = String.format("%stext%2d", path, i);
            try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(filepath))) {
                try (FileInputStream fileInputStream = new FileInputStream(filepath)) {
                    while (fileInputStream.available() < size) {
                        dataOutputStream.writeUTF(generateParagraph());
                        System.out.println("Файл записан!");
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
