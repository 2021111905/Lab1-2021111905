package se_lab1;

import java.io.IOException;
class CreateGraphRunnable implements Runnable {
    String txtPath;
    public String graphPath;

    public final void setPath(String txtPath, String graphPath) {
        this.txtPath = txtPath;
        this.graphPath = graphPath;
    }

    public CreateGraphRunnable(String txtPath, String graphPath) {
        this.setPath(txtPath, graphPath);
    }

    public void run() {
        Runtime run = Runtime.getRuntime();

        try {
            Lab1.imgState = 0;
            Process process = run.exec(String.format("dot -Tpng %s -o %s", this.txtPath, this.graphPath));
            process.waitFor();
            Lab1.imgState = 1;
        } catch (IOException var3) {
            var3.printStackTrace();
        } catch (InterruptedException var4) {
            var4.printStackTrace();
        }

    }

}
