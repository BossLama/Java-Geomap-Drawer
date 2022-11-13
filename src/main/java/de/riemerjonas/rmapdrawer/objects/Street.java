package de.riemerjonas.rmapdrawer.objects;

import de.riemerjonas.rmapdrawer.data.NavigationColors;

import java.awt.*;
import java.util.ArrayList;

public class Street {

    private ArrayList<Waypoint> waypoints;
    private String name;
    private int maxspeed;
    private String type;

    public Street(String name){
        this.name = name;
        waypoints = new ArrayList<>();
        maxspeed = 0;
        type = "road";
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getMaxspeed() {
        return maxspeed;
    }

    public void setMaxspeed(int maxspeed) {
        this.maxspeed = maxspeed;
    }

    public void addWaypoint(Waypoint waypoint){
        waypoints.add(waypoint);
    }

    public ArrayList<Waypoint> getWaypoints(){
        return waypoints;
    }

    public Color getColor(){
        switch (type){
            case "motorway":
                return NavigationColors.color_street_motorway = new Color(184, 73, 48);
            case "trunk":
                return NavigationColors.color_street_trunk = new Color(184, 111, 48);
            case "primary":
                return NavigationColors.color_street_primary = new Color(184, 168, 48);
            case "secondary":
                return NavigationColors.color_street_secondary = new Color(140, 140, 140);
            case "tertiary":
                return NavigationColors.color_street_tertiary = new Color(107, 107, 107);
            case "unclassified":
                return NavigationColors.color_street_unclassified = new Color(61, 61, 61);
            case "residential":
                return NavigationColors.color_street_residential = new Color(82, 82, 82);
            default: return NavigationColors.color_street_default = new Color(48, 98, 184);
        }
    }

}
