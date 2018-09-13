package STC1309.WarAndPeace;

public class Main {
    public static void main(String[] args) {
        String path = "/home/vadim/IdeaProjects/WarAndPeace/text_files/";
        textGenerator text = new textGenerator();
        text.generateFiles(path, 5, 100, 10);
    }
}
