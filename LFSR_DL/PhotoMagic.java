/*
* Encrypts images through LFSR operations:
* 
* Colors of individual pixels are extracted and the RGB components are XOR'd 
* with an 8-bit integer that the given LFSR creates. The new color is then 
* returned and replaces the original colored pixel.
*
* The goal of this is to be able to encrypt and decrypt images with a single
* LFSR seed and 'tap' position. Images encrypted through this method are able
* to be decrypted if the right seed and tap position are given.
*
* Usage: java PhotoMagic <IMAGE FILE> <SEED> <TAP>
* Example: java PhotoMagic Xbasketball.png 01101000010100010000 17
*
* For standardization purposes, all images prefaced by an 'X' in the 'images' folder 
* are encrypted through the seed + tap combo of 01101000010100010000 and 17.
*
* Daniel Li
*
* Java 1.8.0
*/
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
        String image = String.format("images/%s",args[0]);
        String seed = args[1];
        int tap = Integer.parseInt(args[2]);
        LFSR_DL lfsr = new LFSR_DL(seed, tap);
        Picture pic = new Picture(image);
        PhotoMagic magic = new PhotoMagic();
        Picture transformed = magic.transform(pic, lfsr);
        transformed.show();
    }
}