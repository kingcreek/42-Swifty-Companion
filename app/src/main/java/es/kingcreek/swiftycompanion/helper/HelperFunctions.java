package es.kingcreek.swiftycompanion.helper;

public class HelperFunctions {

    public static String getLevelFormat(double value) {
        int level = (int) value;
        int percent = (int) ((value - level) * 100);

        return "level " + level + " - " + percent + "%";
    }

    public static int getLvlPercent(double value) {
        int lvl = (int) value;
        return (int) ((value - lvl) * 100);
    }

}
