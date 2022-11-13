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

    public GradientPaint getColor(){
        switch (type){
            case "motorway":
                return NavigationColors.color_street_motorway;
            case "trunk":
                return NavigationColors.color_street_trunk;
            case "primary":
                return NavigationColors.color_street_primary;
            case "secondary":
                return NavigationColors.color_street_secondary;
            case "tertiary":
                return NavigationColors.color_street_tertiary;
            case "unclassified":
                return NavigationColors.color_street_unclassified;
            case "residential":
                return NavigationColors.color_street_residential;
            default: return NavigationColors.color_street_default;
        }
    }

}
