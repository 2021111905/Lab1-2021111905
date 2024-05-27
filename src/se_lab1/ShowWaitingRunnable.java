package se_lab1;

class ShowWaitingRunnable implements Runnable {
    String graphPath;

    public final void setPath(String graphPath) {
        this.graphPath = graphPath;
    }

    public ShowWaitingRunnable(String graphPath) {
        this.setPath(graphPath);
    }

    public void run() {
        while(Lab1.imgState == 0) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }
        }

        PicDisplayPanel.setPic(this.graphPath);
    }
}
