package se_lab1;

class SetPicRunnable implements Runnable  {
    String graphPath;
    int picNum;

    public void setgraphPath(String graph) {
        this.graphPath = graph;
    }

    public String getgraphPath() {
        return this.graphPath;
    }

    public SetPicRunnable(String graphPath, int picNum) {
        this.setPath(graphPath, picNum);
    }

    public void setPath(String graphPath, int picNum) {
        this.graphPath = graphPath;
        this.picNum = picNum;
    }

    public void run() {
        for(int i = 0; i < this.picNum; ++i) {
            try {
                PicDisplayPanel.setPic(this.graphPath.replace(".png", String.format("%d.png", i)));
                Thread.sleep(1000L);
            } catch (InterruptedException var3) {
                var3.printStackTrace();
            }
        }

    }

}
//  修改