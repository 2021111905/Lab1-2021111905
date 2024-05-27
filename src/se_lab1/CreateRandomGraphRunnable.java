package se_lab1;

import java.io.IOException;

class CreateRandomGraphRunnable implements Runnable {
    String txtPath;
    String graphPath;
    int picNum;

    public void setPath(String txtPath, String graphPath, int picNum) {
        this.txtPath = txtPath;
        this.graphPath = graphPath;
        this.picNum = picNum;
    }

    public CreateRandomGraphRunnable(String txtPath, String graphPath, int picNum) {
        this.setPath(txtPath, graphPath, picNum);
    }

    public void run() {
        Runtime run = Runtime.getRuntime();

        try {
            Lab1.imgState = 0;
            Process process = null;

            for(int i = 0; i < this.picNum; ++i) {
                process = run.exec(String.format("dot -Tpng %s -o %s", this.txtPath.replace(".dot", String.format("%d.dot", i)), this.graphPath.replace(".png", String.format("%d.png", i))));
            }

            process.waitFor();
            Lab1.imgState = 1;
        } catch (IOException var4) {
            var4.printStackTrace();
        } catch (InterruptedException var5) {
            var5.printStackTrace();
        }

    }
}
