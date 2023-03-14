package com.kakaouo.mods.kacosmetics.util;

import com.mojang.blaze3d.platform.NativeImage;

import java.awt.*;

public enum KakaUtils {
    ;

    public static int getHexInFormatABGR(Color color) {
        int a = color.getAlpha();
        int b = color.getBlue();
        int g = color.getBlue();
        int r = color.getRed();
        return (a & 0xff) << 24 |
                (b & 0xff) << 16 |
                (g & 0xff) << 8 |
                (r & 0xff);
    }

    public static Color colorFromHexInFormatABGR(int hex) {
        int a = (hex >> 24) & 0xff;
        int b = (hex >> 16) & 0xff;
        int g = (hex >> 8) & 0xff;
        int r = hex & 0xff;
        return new Color(r, g, b, a);
    }

    public static Color colorAtPixel(NativeImage image, int x, int y) {
        return KakaUtils.colorFromHexInFormatABGR(image.getPixelRGBA(x, y));
    }

    /**
     * Get the pixel of an image, and return it in RGBA format.<br/>
     * The returned value has a format of <code>0xRRGGBBAA</code>.
     * @param image The image to get pixel from
     * @param x The X position of the pixel
     * @param y The Y position of the pixel
     * @return The pixel in <code>0xRRGGBBAA</code> format.
     */
    public static int rgbaAtPixel(NativeImage image, int x, int y) {
        int hex = image.getPixelRGBA(x, y);

        int a = (hex >> 24) & 0xff;
        int b = (hex >> 16) & 0xff;
        int g = (hex >> 8) & 0xff;
        int r = hex & 0xff;

        return (r & 0xff) << 24 |
                (g & 0xff) << 16 |
                (b & 0xff) << 8 |
                (a & 0xff);
    }
}
