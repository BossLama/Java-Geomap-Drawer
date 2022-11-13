package de.riemerjonas.rmapdrawer.objects;

import java.util.ArrayList;

public enum StreetType {

    MOTORWAY("Autobahn", "motorway"),
    TRUNK("Autobahnähnliche Straße", "trunk"),
    PRIMARY("Bundesstraße", "primary"),
    SECONDARY("Kreisstraße", "secondary"),
    TERTIARY("Kreisstraße", "tertiary"),
    UNCLASSIFIED("Nebenstraßen", "unclassified"),
    RESIDENTIAL("Wohnstraße", "residential"),
    ROAD("Sonstige Straße", "road");


    private String name;
    private String tag;

    private StreetType(String name, String tag){
        this.name = name;
        this.tag = tag;
    }


    public String getName(){
        return name;
    }

    public String getTag(){
        return tag;
    }
}
