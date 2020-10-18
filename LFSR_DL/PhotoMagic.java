import java.awt.Color;

public class PhotoMagic {
    public static Picture transform(Picture picture, LFSR_DL lfsr) {
        // Start looping through all pixels in the image
        for (int i = 0; i < picture.width(); i++) {
            for (int j = 0; j < picture.height(); j++) {
                // Get the color at specified pixel i,j
                Color c = new Color(picture.getRGB(i, j));
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                // Use the LFSR to generate an 8-bit integer to perform xor with the old RGB
                // values
                int new_r = lfsr.generate(8) ^ r;
                int new_g = lfsr.generate(8) ^ g;
                int new_b = lfsr.generate(8) ^ b;
                // Initialize the new RGB color and set the pixel to that color.
                Color new_c = new Color(new_r, new_g, new_b);
                picture.set(i, j, new_c);
            }
        }
        return picture;
    }

    // Tester method
    public static void main(String args[]) {
        // Take command line arguments for image file, LFSR seed, and tap position
        String image = args[0];
        String seed = args[1];
        int tap = Integer.parseInt(args[2]);
        LFSR_DL lfsr = new LFSR_DL(seed, tap);
        Picture pic = new Picture(image);
        PhotoMagic magic = new PhotoMagic();
        Picture transformed = magic.transform(pic, lfsr);
        transformed.show();
    }
}