package de.riemerjonas.rmapdrawer.data;

import java.awt.*;

public class NavigationColors {


    public static GradientPaint color_street_motorway;
    public static GradientPaint color_street_trunk;
    public static GradientPaint color_street_primary;
    public static GradientPaint color_street_secondary;
    public static GradientPaint color_street_tertiary;
    public static GradientPaint color_street_unclassified;
    public static GradientPaint color_street_residential;
    public static GradientPaint color_street_default;
    public static GradientPaint color_route;

    public static void setWidth(int x, int y, int width){
        color_street_motorway = new GradientPaint(x + (width / 2),y, new Color(245, 66, 66), x + width - 30, y, new Color(255,255,255,0));
        color_street_trunk = new GradientPaint(x + (width / 2),y, new Color(245, 84, 66), x + width - 30, y, new Color(255,255,255,0));
        color_street_primary = new GradientPaint(x + (width / 2),y, new Color(245, 135, 66), x + width - 30, y, new Color(255,255,255,0));
        color_street_secondary = new GradientPaint(x + (width / 2),y, new Color(245, 209, 66), x + width - 30, y, new Color(255,255,255,0));
        color_street_tertiary = new GradientPaint(x + (width / 2),y, new Color(230, 245, 66), x + width - 30, y, new Color(255,255,255,0));
        color_street_unclassified = new GradientPaint(x + (width / 2),y, new Color(82, 82, 82), x + width - 30, y, new Color(255,255,255,0));
        color_street_residential = new GradientPaint(x + (width / 2),y, new Color(82, 82, 82), x + width - 30, y, new Color(255,255,255,0));
        color_street_default = new GradientPaint(x + (width / 2),y, new Color(82, 82, 82), x + width - 30, y, new Color(255,255,255,0));
        color_route = new GradientPaint(x + (width / 2),y, new Color(71, 99, 255), x + width - 30, y, new Color(255,255,255,0));
    }
}
