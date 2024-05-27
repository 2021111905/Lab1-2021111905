package se_lab1;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
class FunctionPanel extends JPanel {
    private static final long serialVersionUID = -1104559035947942491L;
    private JTabbedPane tp = new JTabbedPane(1);
    private String[] tabNames = new String[]{"查找桥接词", "生成新文本", "查找最短路", "随机游走"};

    public FunctionPanel() {
        this.setBackground(Color.WHITE);
        JPanel tab1 = new QueryBridgePanel();
        this.tp.addTab(this.tabNames[0], (Icon)null, tab1);
        JPanel tab2 = new NewTextPanel();
        this.tp.addTab(this.tabNames[1], (Icon)null, tab2);
        JPanel tab3 = new ShortestPanel();
        this.tp.addTab(this.tabNames[2], (Icon)null, tab3);
        JPanel tab4 = new RandomPanel();
        this.tp.addTab(this.tabNames[3], (Icon)null, tab4);
        this.add(this.tp);
    }

}
