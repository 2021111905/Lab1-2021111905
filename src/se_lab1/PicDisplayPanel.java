package se_lab1;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class PicDisplayPanel extends JPanel {
    private static final long serialVersionUID = -466385864846654643L;
    public static JLabel picLabel;
    public static int WIDTH;
    public static int height;
    public static ImageIcon pic;
    boolean isAltDown = false;
    int percent = 100;

    public PicDisplayPanel() {
        picLabel = new JLabel();
        this.setBackground(Color.WHITE);
        this.add(picLabel);
    }

    public static void setPic(String path) {
        try {
            pic = new ImageIcon(ImageIO.read(new File(path)));
            WIDTH = pic.getIconWidth();
            height = pic.getIconHeight();
            picLabel.setIcon(pic);
            picLabel.repaint();
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public static void changeSize(int percent) {
        pic.setImage(pic.getImage().getScaledInstance(percent * WIDTH, percent * height, 1));
        picLabel.setIcon(pic);
    }
}
// 注释