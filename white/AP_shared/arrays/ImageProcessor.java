import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class ImageProcessor extends JFrame {
    private BufferedImage originalImage;
    private BufferedImage processedImage;
    private JPanel imagePanel;
    private final int PADDING = 20;

    public ImageProcessor() {
        setTitle("AP CS Image Processor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeUI();
        setSize(800, 600);
    }

    private void initializeUI() {
        // Create main layout
        setLayout(new BorderLayout(PADDING, PADDING));

        // Create toolbar with buttons
        JPanel toolbar = new JPanel();
        JButton loadButton = new JButton("Load Image");
        JButton sharpenButton = new JButton("Sharpen");
        JButton blurButton = new JButton("Blur");
        
        toolbar.add(loadButton);
        toolbar.add(sharpenButton);
        toolbar.add(blurButton);
        add(toolbar, BorderLayout.NORTH);

        // Create image display panel
        imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (processedImage != null) {
                    // Calculate scaling to fit panel while maintaining aspect ratio
                    double scale = Math.min(
                        (double) getWidth() / processedImage.getWidth(),
                        (double) getHeight() / processedImage.getHeight()
                    );
                    
                    int scaledWidth = (int) (processedImage.getWidth() * scale);
                    int scaledHeight = (int) (processedImage.getHeight() * scale);
                    
                    // Center the image
                    int x = (getWidth() - scaledWidth) / 2;
                    int y = (getHeight() - scaledHeight) / 2;
                    
                    g.drawImage(processedImage, x, y, scaledWidth, scaledHeight, null);
                }
            }
        };
        imagePanel.setBackground(Color.LIGHT_GRAY);
        add(imagePanel, BorderLayout.CENTER);

        // Add button listeners
        loadButton.addActionListener(e -> loadImage());
        sharpenButton.addActionListener(e -> applyKernel(getSharpenKernel()));
        blurButton.addActionListener(e -> applyKernel(getBlurKernel()));

        // Add window resize listener
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                imagePanel.repaint();
            }
        });
    }

    private void loadImage() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Image files", "jpg", "jpeg", "png", "gif");
        chooser.setFileFilter(filter);

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                originalImage = ImageIO.read(chooser.getSelectedFile());
                processedImage = originalImage;
                imagePanel.repaint();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error loading image: " + ex.getMessage());
            }
        }
    }

    // STUDENTS WILL IMPLEMENT THIS METHOD
    private double[][] getSharpenKernel() {
        return new double[][] {
            {-1, -1, -1},
            {-1,  9, -1},
            {-1, -1, -1}
        };
    }

    // STUDENTS WILL IMPLEMENT THIS METHOD
    private double[][] getBlurKernel() {
        double weight = 1.0 / 9.0;
        return new double[][] {
            {weight, weight, weight},
            {weight, weight, weight},
            {weight, weight, weight}
        };
    }

    // STUDENTS WILL IMPLEMENT THIS METHOD
    private void applyKernel(double[][] kernel) {
        if (originalImage == null) return;
        
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        processedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // For each pixel in the image
        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                int[] rgb = applyKernelToPixel(x, y, kernel);
                processedImage.setRGB(x, y, (rgb[0] << 16) | (rgb[1] << 8) | rgb[2]);
            }
        }
        
        imagePanel.repaint();
    }

    // STUDENTS WILL IMPLEMENT THIS METHOD
    private int[] applyKernelToPixel(int x, int y, double[][] kernel) {
        int[] rgb = new int[3];
        
        // For each color channel (R, G, B)
        for (int c = 0; c < 3; c++) {
            double sum = 0;
            
            // Apply kernel to 3x3 neighborhood
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int pixel = originalImage.getRGB(x + i, y + j);
                    int channel = (pixel >> (16 - c * 8)) & 0xFF;
                    sum += channel * kernel[i + 1][j + 1];
                }
            }
            
            // Clamp values between 0 and 255
            rgb[c] = Math.min(255, Math.max(0, (int)sum));
        }
        
        return rgb;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ImageProcessor().setVisible(true);
        });
    }
}
