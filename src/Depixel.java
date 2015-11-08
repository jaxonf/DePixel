import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by jaxonf on 7/07/2014.
 */
public class Depixel {

    public Depixel() {

    }

    public static void main(String[] args) {

        Depixel depixel = new Depixel();
        depixel.run(args);
    }

    private void run(String[] args) {

        if(args.length > 1) {
            try {
                BufferedImage img = ImageIO.read(new File(args[0]));
                BufferedImage newImage = new BufferedImage(img.getWidth() * 2, img.getHeight() * 2, BufferedImage.TYPE_INT_ARGB);
                int one = 0;
                int two = 0;
                int three = 0;
                int four = 0;

                for(int y = 1; y < img.getHeight() - 1; y++) {
                    for(int x = 1; x < img.getWidth() - 1; x++) {
                        one = img.getRGB(x, y);
                        two = img.getRGB(x, y);
                        three = img.getRGB(x, y);
                        four = img.getRGB(x, y);

                        if(img.getRGB(x - 1, y) == img.getRGB(x, y - 1) &&
                                img.getRGB(x - 1, y) != img.getRGB(x, y + 1) &&
                                img.getRGB(x, y - 1) != img.getRGB(x + 1, y)) {
                            one = img.getRGB(x, y - 1);
                        }

                        if(img.getRGB(x, y - 1) == img.getRGB(x + 1, y) &&
                                img.getRGB(x, y - 1) != img.getRGB(x - 1, y) &&
                                img.getRGB(x + 1, y) != img.getRGB(x, y + 1)) {
                            two = img.getRGB(x + 1, y);
                        }

                        if(img.getRGB(x + 1, y) == img.getRGB(x, y + 1) &&
                                img.getRGB(x + 1, y) != img.getRGB(x, y - 1) &&
                                img.getRGB(x, y + 1) != img.getRGB(x - 1, y)) {
                            four = img.getRGB(x, y + 1);
                        }

                        if(img.getRGB(x, y + 1) == img.getRGB(x - 1, y) &&
                                img.getRGB(x, y + 1) != img.getRGB(x + 1, y) &&
                                img.getRGB(x - 1, y) != img.getRGB(x, y - 1)) {
                            three = img.getRGB(x - 1, y);
                        }

                        newImage.setRGB(x * 2, y * 2, one);
                        newImage.setRGB((x * 2) + 1, y * 2, two);
                        newImage.setRGB(x * 2, (y * 2) + 1, three);
                        newImage.setRGB((x * 2) + 1, (y * 2) + 1, four);
                    }
                }


                File outputfile = new File(args[1] + ".png");
                ImageIO.write(newImage, "png", outputfile);
            } catch(Exception e) {}
        }
    }

}
