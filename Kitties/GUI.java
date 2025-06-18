import org.json.simple.JSONObject;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class GUI implements ActionListener {

    private static JFrame window = new JFrame("Kitties");
    private  static  JPanel mainPanel = new JPanel();
    private static JLabel catLabel = new JLabel();
    private static JButton catButton = new JButton("Click?");

    public static void mainGui(){
        window.setSize(400, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(mainPanel);

        mainPanel.setLayout(null);

        catLabel.setBounds(100, 80 ,200, 200);
        mainPanel.add(catLabel);

        catButton.setBounds(150,300, 100, 25);
        catButton.addActionListener(new GUI());
        mainPanel.add(catButton);

        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JSONObject catData = CatApi.getImageData();
        String catUrl = catData.get("url").toString();

        URL imageUrl = null;
        try {
            imageUrl = new URL(catUrl);
            BufferedImage urlImage = ImageIO.read(imageUrl);
            ImageIcon scalcedicon = new ImageIcon(urlImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
            catLabel.setIcon(scalcedicon);
            catButton.setText("Another!!");
            window.revalidate();
            window.repaint();
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
