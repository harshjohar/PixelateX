import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Shows of popup of the scaled image re-scaled to 400x400 pixel.
 *
 * @author harshjohar
 */
public class ImageShower extends JFrame {
    static JFrame frame = new JFrame();

    public ImageShower(String text, int side) {
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = image.createGraphics();

        Font font = new Font("Courier New", Font.PLAIN, 15);
        graphics2D.setFont(font);

        FontMetrics metrics = graphics2D.getFontMetrics();
        int width = metrics.stringWidth("0") * side;
        int height = metrics.getHeight() * side;

        graphics2D.dispose();
        String[] textArray = text.split("\n");
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        int h = height /side;

        // image properties
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setFont(font);

        for (int i = 0; i < textArray.length; i++) {
            g2d.drawString(textArray[i], 0, i * h);
        }
        g2d.dispose();


        BufferedImage image1 = new BufferedImage(400, 400, image.getType());
        Image im = image.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        image1.getGraphics().drawImage(im, 0, 0, null);
        frame.setTitle("Image");
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(400, 400));
        frame.add(new JLabel(new ImageIcon(image1)));
        frame.pack();
        frame.setVisible(true);
    }
    public void setVisible(boolean val) {
        frame.setVisible(val);
    }
}
