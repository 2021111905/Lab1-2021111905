package se_lab1;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JComponent;
public class Lab1 extends JComponent {
    private static final long serialVersionUID = -4654513992552014113L;
    public static MyFrame f;
    public static String fileUrl;
    public static String[] words;
    public static Tree t;
    public static int imgState;

    public static void readInFile() {
        File file = new File(fileUrl);
        String wordsStr = "";

        try {
            Scanner in;
            String str;
            for(in = new Scanner(file); in.hasNextLine(); wordsStr = wordsStr.concat(replaceStr(str) + " ")) {
                str = in.nextLine();
            }

            words = wordsStr.split("\\s+");
            t = new Tree(words);
            DirectedGraph.createDirectedGraph(t, fileUrl, "Verdana", 12);
            in.close();
        } catch (FileNotFoundException var4) {
            var4.printStackTrace();
        }

    }

    public Lab1() {
        this.setBackground(Color.WHITE);
    }

    public static String[] wordSplit(String str) {
        return str.split("\\s+");
    }

    public static String replaceStr(String str) {
        return str.replaceAll("[^a-zA-Z]", " ").toLowerCase();
    }

    public static void main(String[] args) {
        f = new MyFrame();
    }

}
