/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.until.color;

import java.awt.Color;

/**
 *
 * @author HUNGpYN
 */
public class SetColor implements SetColorInteface {

    @Override
    public Color mauVang() {
        // Tạo màu đỏ sáng (RGB: 255, 200, 0)
        Color color1 = new Color(255, 200, 0);

        // Tạo màu vàng sáng (RGB: 255, 255, 0)
        Color color2 = new Color(255, 255, 0);
        double ratio = 0.5;
        int red = (int) (color1.getRed() * ratio + color2.getRed() * (1 - ratio));
        int green = (int) (color1.getGreen() * ratio + color2.getGreen() * (1 - ratio));
        int blue = (int) (color1.getBlue() * ratio + color2.getBlue() * (1 - ratio));
        return new Color(red, green, blue);
    }

    @Override
    public Color thanDen() {
        Color thanDen = new Color(16, 24, 32, 255);
        return thanDen;
    }
}
