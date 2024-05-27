package se_lab1;

class ShowRandomWaitingRunnable implements Runnable {
    String graphPath;
    int picNum;

    public ShowRandomWaitingRunnable(String graphPath, int picNum) {
        this.setPath(graphPath, picNum);
    }

    public void setPath(String graphPath, int picNum) {
        this.graphPath = graphPath;
        this.picNum = picNum;
    }

    public void run() {
        while(Lab1.imgState == 0) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException var3) {
                var3.printStackTrace();
            }
        }

        SetPicRunnable setPicRunnable = new SetPicRunnable(this.graphPath, this.picNum);
        Thread setPicThread = new Thread(setPicRunnable);
        setPicThread.start();
    }

}
