package se_lab1;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

class RandomPanel extends JPanel {
    private static final long serialVersionUID = -2862015187279261925L;
    JLabel lbRst = new JLabel();
    JButton btnG = new JButton("开始生成");

    public RandomPanel() {
        this.btnG.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String random = Lab1.t.randomWalk();
                RandomPanel.this.lbRst.setText(random);
                DirectedGraph.createRandomDirectedGraph(Lab1.t, Lab1.fileUrl, "Verdana", 12, random);
            }
        });
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = 1;
        this.setLayout(new GridBagLayout());
        gbc.gridy = 1;
        this.add(this.btnG, gbc);
        gbc.gridy = 2;
        this.add(this.lbRst, gbc);
    }
}
