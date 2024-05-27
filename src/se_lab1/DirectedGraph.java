package se_lab1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DirectedGraph {
    static String filetxt = "txt";
    static String filedot = "dot";

    public DirectedGraph() {
    }

    public static boolean createShortestDirectedGraph(Tree t, String fileUrl, String fontname, int fontsize, String shortest, PathGraphAssist pga) {
        String[] shroads = shortest.split("\n");

        for(int i = 0; i < shroads.length; ++i) {
            shroads[i] = shroads[i].replaceAll("Path [0-9]+ :", "");
            shroads[i] = shroads[i].replace(".", "");
        }

        String[] colors = new String[]{"#1abc9c", "#3498db", "#f1c40f", "#8e44ad", "#c0392b"};
        File dotFile = new File(fileUrl.replace(filetxt, filedot));
        File sdotFile = new File(fileUrl.replace(".txt", "s.dot"));

        try {
            sdotFile.createNewFile();
            Scanner in = new Scanner(dotFile);
            BufferedWriter outBuffer = new BufferedWriter(new FileWriter(sdotFile));

            while(in.hasNextLine()) {
                String str = in.nextLine();

                int i;
                for(i = 0; i < pga.allNodes.size(); ++i) {
                    for(int j = 0; j < pga.allNodes.size(); ++j) {
                        TreeNode node1 = (TreeNode)pga.allNodes.get(i);
                        TreeNode node2 = (TreeNode)pga.allNodes.get(j);
                        str = str.replace(String.format("%s -> %s [color = \"#3498db\"]", node1.getWord(), node2.getWord()), String.format("%s -> %s [color = \"%s\"]", node1.getWord(), node2.getWord(), "#778899"));
                    }
                }

                for(i = 0; i < shroads.length; ++i) {
                    str = replaceResult(str, shroads[i], colors[i], pga);
                }

                outBuffer.write(str);
            }

            outBuffer.flush();
            outBuffer.close();
            in.close();
        } catch (IOException var17) {
            var17.printStackTrace();
        }

        Runnable createGraphR = new CreateGraphRunnable(Lab1.fileUrl.replace(".txt", "s.dot"), Lab1.fileUrl.replace(".txt", "s.png"));
        Runnable showWaitingRunnable = new ShowWaitingRunnable(Lab1.fileUrl.replace(".txt", "s.png"));
        Thread createGraphThread = new Thread(createGraphR);
        Thread showWaitingThread = new Thread(showWaitingRunnable);
        Lab1.imgState = 0;
        createGraphThread.start();
        showWaitingThread.start();
        return true;
    }

    public static boolean createRandomDirectedGraph(Tree t, String fileUrl, String fontname, int fontsize, String random) {
        String[] randomWords = random.split(" ");
        String[] colors = new String[]{"#1abc9c", "#3498db", "#f1c40f", "#8e44ad", "#c0392b"};
        File dotFile = new File(fileUrl.replace(filetxt, filedot));
        File sdotFile = null;

        try {
            for(int i = 0; i < randomWords.length; ++i) {
                sdotFile = new File(fileUrl.replace(".txt", String.format("%d.dot", i)));
                sdotFile.createNewFile();
                Scanner scannerin = new Scanner(dotFile);
                BufferedWriter outBuffer = new BufferedWriter(new FileWriter(sdotFile));

                while(scannerin.hasNextLine()) {
                    String str = scannerin.nextLine();
                    str = replaceRandomResult(str, randomWords, colors[2], i);
                    outBuffer.write(str);
                }

                outBuffer.flush();
                outBuffer.close();
                scannerin.close();
            }
        } catch (IOException var13) {
            var13.printStackTrace();
        }

        Runnable createRandGR = new CreateRandomGraphRunnable(Lab1.fileUrl.replace(filetxt, filedot), Lab1.fileUrl.replace(filetxt, "png"), randomWords.length);
        Runnable showRandWR = new ShowRandomWaitingRunnable(Lab1.fileUrl.replace(filetxt, "png"), randomWords.length);
        Thread createRandomGraphThread = new Thread(createRandGR);
        Thread showWaitingThread = new Thread(showRandWR);
        Lab1.imgState = 0;
        createRandomGraphThread.start();
        showWaitingThread.start();
        return true;
    }

    public static String replaceRandomResult(String ain, String[] random, String color, int num) {
        String intemp;
        if (num > 1) {
            intemp = ain.replace(String.format("%s -> %s", random[num - 1], random[num]), String.format("%s -> %s [color = \"%s\"]", random[num - 1], random[num], color));
        } else {
            intemp = ain;
        }

        return intemp + "\n";
    }

    public static String replaceResult(String ain, String shortroad, String color, PathGraphAssist pga) {
        String[] srNodes = shortroad.split("->");

        for(int i = 0; i < srNodes.length - 1; ++i) {
            TreeNode node1 = pga.allNodes.nodeCheck(srNodes[i]);
            TreeNode node2 = pga.allNodes.nodeCheck(srNodes[i + 1]);
            int state = pga.queryNodeToNode(node1, node2);
            if (state == Integer.MAX_VALUE) {
                color = "#B71C1C";
            }

            ain = ain.replace(String.format("%s -> %s", srNodes[i], srNodes[i + 1]), String.format("%s -> %s [color = \"%s\"]", srNodes[i], srNodes[i + 1], color));
        }

        return ain + "\n";
    }

    public static boolean createDirectedGraph(Tree t, String fileUrl, String fontname, int fontsize) {
        File dotFile = new File(fileUrl.replace("txt", "dot"));

        try {
            dotFile.createNewFile();
            BufferedWriter outBuffer = new BufferedWriter(new FileWriter(dotFile));
            outBuffer.write(String.format("digraph %s {\n\tfontname = \"%s\";\n\tfontsize = %d;\n\n", "test", fontname, fontsize));
            outBuffer.write(String.format("\tnode [ fontname = \"%s\", fontsize = %d ]\n", fontname, fontsize));
            outBuffer.write(String.format("\tedge [ fontname = \"%s\", fontsize = %d ]\n\n", fontname, fontsize));

            int i;
            for(i = 0; i < Lab1.words.length; ++i) {
                outBuffer.write(String.format("\t%s;\n", Lab1.words[i]));
            }

            for(i = 0; i < t.treeNodes.size(); ++i) {
                for(int j = 0; j < t.treeNodes.size(); ++j) {
                    TreeNode node1 = (TreeNode)t.treeNodes.get(i);
                    TreeNode node2 = (TreeNode)t.treeNodes.get(j);
                    if (node1.childList.indexOf(node2) != -1) {
                        outBuffer.write(String.format("\t%s -> %s [label=\"%d\"];\n", node1.getWord(), node2.getWord(), node1.getWeightOfNode(node2)));
                    }
                }
            }

            outBuffer.write("}");
            outBuffer.flush();
            outBuffer.close();
        } catch (IOException var10) {
            var10.printStackTrace();
        }

        Runnable createGraphR = new CreateGraphRunnable(Lab1.fileUrl.replace("txt", "dot"), Lab1.fileUrl.replace("txt", "png"));
        Runnable showWaitingRunnable = new ShowWaitingRunnable(Lab1.fileUrl.replace("txt", "png"));
        Thread createGraphThread = new Thread(createGraphR);
        Thread showWaitingThread = new Thread(showWaitingRunnable);
        Lab1.imgState = 0;
        createGraphThread.start();
        showWaitingThread.start();
        return true;
    }

}
