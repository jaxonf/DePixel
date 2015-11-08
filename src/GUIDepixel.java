import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by jaxonf on 7/07/2014.
 */
public class GUIDepixel {

    private String input;
    private int multiplier;
    private String outputDirectory;
    private BufferedImage newImage;
    private BufferedImage img;


    public GUIDepixel(String input, int multiplier, String outputDirectory) {
        this.input = input;
        this.multiplier = multiplier;
        this.outputDirectory = outputDirectory;
    }



    public void run() {

        try {
            img = ImageIO.read(new File(input));
            for(int i = 0; i < multiplier; i++) {
                newImage = new BufferedImage(img.getWidth() * 2, img.getHeight() * 2, BufferedImage.TYPE_INT_ARGB);
                int one = 0;
                int two = 0;
                int three = 0;
                int four = 0;

                for(int y = 0; y < img.getHeight(); y++) {
                    for(int x = 0; x < img.getWidth(); x++) {
                        one = img.getRGB(x, y);
                        two = img.getRGB(x, y);
                        three = img.getRGB(x, y);
                        four = img.getRGB(x, y);

                        if(compare(x - 1, y, true, x, y - 1) &&
                                compare(x - 1, y, false, x, y + 1) &&
                                compare(x, y - 1, false, x + 1, y)) {
                            one = img.getRGB(x, y - 1);
                        }

                        if(compare(x, y - 1, true, x + 1, y) &&
                                compare(x, y - 1, false, x - 1, y) &&
                                compare(x + 1, y, false, x, y + 1)) {
                            two = img.getRGB(x + 1, y);
                        }

                        if(compare(x + 1, y, true, x, y + 1) &&
                                compare(x + 1, y, false, x, y - 1) &&
                                compare(x, y + 1, false, x - 1, y)) {
                            four = img.getRGB(x, y + 1);
                        }

                        if(compare(x, y + 1, true, x - 1, y) &&
                                compare(x, y + 1, false, x + 1, y) &&
                                compare(x - 1, y, false, x, y - 1)) {
                            three = img.getRGB(x - 1, y);
                        }

                        newImage.setRGB(x * 2, y * 2, one);
                        newImage.setRGB((x * 2) + 1, y * 2, two);
                        newImage.setRGB(x * 2, (y * 2) + 1, three);
                        newImage.setRGB((x * 2) + 1, (y * 2) + 1, four);
                    }
                }
                img = newImage;
            }


            File outputfile = new File("/" + outputDirectory + ".png");
            ImageIO.write(newImage, "png", outputfile);
        } catch(Exception e) {}

    }

    public boolean compare(int xa, int ya, boolean equal, int xb, int yb) {
        if(xa < 0 || xb < 0 || ya < 0 || yb < 0 ||
                xa == img.getWidth() || xb == img.getWidth() ||
                ya == img.getHeight() || yb == img.getHeight()) {
            return false;
        } else if(equal) {
            return (img.getRGB(xa, ya) == img.getRGB(xb, yb));
        } else {
            return (img.getRGB(xa, ya) != img.getRGB(xb, yb));
        }
    }

}
