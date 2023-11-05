import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Color Chooser");
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ColorChooser selfColorChooser = new ColorChooser();

        JPanel content = new JPanel();
        content.setPreferredSize(new Dimension(1000, 600));
        content.add(selfColorChooser);

        frame.getContentPane().add(content);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}