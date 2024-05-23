package es.kingcreek.swiftycompanion.helper;
import android.graphics.Color;
import java.util.ArrayList;
import java.util.List;

public class ColorUtils {

    public static List<Integer> generateColorPalette(int numColors, float startHue, float endHue, float saturation, float lightness) {
        List<Integer> colors = new ArrayList<>();
        float hueStep = (endHue - startHue) / (numColors - 1);

        for (int i = 0; i < numColors; i++) {
            float hue = startHue + i * hueStep;
            colors.add(ColorUtils.HSLToColor(hue, saturation, lightness));
        }

        return colors;
    }

    public static int HSLToColor(float hue, float saturation, float lightness) {
        float c = (1 - Math.abs(2 * lightness - 1)) * saturation;
        float x = c * (1 - Math.abs((hue / 60) % 2 - 1));
        float m = lightness - c / 2;

        float r = 0, g = 0, b = 0;
        if (0 <= hue && hue < 60) {
            r = c; g = x; b = 0;
        } else if (60 <= hue && hue < 120) {
            r = x; g = c; b = 0;
        } else if (120 <= hue && hue < 180) {
            r = 0; g = c; b = x;
        } else if (180 <= hue && hue < 240) {
            r = 0; g = x; b = c;
        } else if (240 <= hue && hue < 300) {
            r = x; g = 0; b = c;
        } else if (300 <= hue && hue < 360) {
            r = c; g = 0; b = x;
        }

        int red = Math.round((r + m) * 255);
        int green = Math.round((g + m) * 255);
        int blue = Math.round((b + m) * 255);

        return Color.rgb(red, green, blue);
    }
}