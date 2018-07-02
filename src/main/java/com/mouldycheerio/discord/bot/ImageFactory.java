package com.mouldycheerio.discord.bot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageFactory {
    public ImageFactory() {
    }

    public File makeImage(String letter) throws IOException {
        File board = new File("parts/board.jpg");
        BufferedImage b = ImageIO.read(board);
        File output = new File("generated/" + letter +  ".png");
        output.mkdirs();
        boolean write = ImageIO.write(b, "PNG", output);
        return output;
    }
}
