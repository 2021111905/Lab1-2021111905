package se_lab1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
class MyFrame extends JFrame  {
    private static final long serialVersionUID = -6904245993409935448L;
    private static final int WIDTH = 520;
    private static final int height = 550;
    PicDisplayPanel picPanel;

    public MyFrame() {
        try {
            String feel = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(feel);
        } catch (Exception var10) {
            var10.printStackTrace();
        }

        this.setBackground(Color.WHITE);
        this.setTitle("Flow chart");
        this.setSize(520, 550);
        this.setDefaultCloseOperation(3);
        this.setExtendedState(6);
        this.setLocationRelativeTo((Component)null);
        this.setLayout(new BorderLayout());
        Container c = this.getContentPane();
        JMenuBar mb = new JMenuBar();
        JMenu mFile = new JMenu("文件(F)");
        mFile.setMnemonic('F');
        JMenuItem miOpen = new JMenuItem("打开(O)");
        miOpen.setMnemonic('O');
        JMenuItem miReset = new JMenuItem("回复默认图(R)");
        miReset.setMnemonic('O');
        mFile.add(miOpen);
        mFile.add(miReset);
        miReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    Lab1.readInFile();
                } catch (NullPointerException var3) {
                }

            }
        });
        miOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser fc = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", new String[]{"txt"});
                fc.setFileFilter(filter);
                fc.setDialogTitle("Choose file");
                fc.setFileSelectionMode(0);
                fc.showOpenDialog(Lab1.f);
                File filename = fc.getSelectedFile();
                if (filename != null) {
                    Lab1.fileUrl = fc.getSelectedFile().getAbsolutePath();
                    Lab1.readInFile();
                }

            }
        });
        mb.add(mFile);
        this.setJMenuBar(mb);
        JPanel mainPanel = new JPanel(new GridLayout());
        PicDisplayPanel picPanel = new PicDisplayPanel();
        JScrollPane sp = new JScrollPane(picPanel);
        sp.validate();
        mainPanel.add(sp);
        FunctionPanel funcPanel = new FunctionPanel();
        mainPanel.add(funcPanel);
        c.add(mainPanel);
        this.setVisible(true);
    }

}
