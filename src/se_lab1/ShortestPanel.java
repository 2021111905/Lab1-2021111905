package se_lab1;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
class ShortestPanel extends JPanel {
    private static final long serialVersionUID = 7264749733574435443L;
    JTextField tfWord1 = new JTextField(112);
    JLabel lbWord1 = new JLabel("单词1: ");
    JTextField tfWord2 = new JTextField(12);
    JLabel lbWord2 = new JLabel("单词2: ");
    JTextArea txRst = new JTextArea(3, 30);
    JButton btnQB = new JButton("开始计算");
    PathGraphAssist pga;

    public ShortestPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        this.btnQB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ShortestPanel.this.pga = new PathGraphAssist(Lab1.t.treeNodes);
                    String shortest = Lab1.t.calcShortestPath(ShortestPanel.this.tfWord1.getText(), ShortestPanel.this.tfWord2.getText(), ShortestPanel.this.pga);
                    ShortestPanel.this.txRst.setText(shortest);
                    DirectedGraph.createShortestDirectedGraph(Lab1.t, Lab1.fileUrl, "Verdana", 12, shortest, ShortestPanel.this.pga);
                } catch (CloneNotSupportedException var3) {
                    var3.printStackTrace();
                } catch (NullPointerException var4) {
                }

            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = 1;
        this.setLayout(new GridBagLayout());
        this.add(this.lbWord1, gbc);
        gbc.gridy = 1;
        this.add(this.tfWord1, gbc);
        gbc.gridy = 2;
        this.add(this.lbWord2, gbc);
        gbc.gridy = 3;
        this.add(this.tfWord2, gbc);
        gbc.gridy = 4;
        this.add(this.btnQB, gbc);
        gbc.gridy = 5;
        this.add(this.txRst, gbc);
    }

}
// 修改再修改
