package de.riemerjonas.rmapdrawer.demo;

import de.riemerjonas.rmapdrawer.data.StreetMapLoader;
import de.riemerjonas.rmapdrawer.map.RGPSMap;
import de.riemerjonas.rmapdrawer.objects.Route;
import de.riemerjonas.rmapdrawer.objects.Waypoint;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static RGPSMap rgpsMap = new RGPSMap(0,0, 800, 480);
    private static StreetMapLoader loader;

    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setSize(800, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initMap();
        frame.add(drawPanel());

        frame.setVisible(true);

    }

    private static void initMap(){
        StreetMapLoader streetMapLoader = new StreetMapLoader();
        //streetMapLoader.convertMapData("schwabhausen.geojson", "streets.json");
        streetMapLoader.convertMapData("schwabhausen.geojson", "streets.json");

        Route route = new Route();
        route.addWaypoint(new Waypoint(48.302243,11.350694));
        route.addWaypoint(new Waypoint(48.302749,11.350710));
        route.addWaypoint(new Waypoint(48.302735,11.351064));
        route.addWaypoint(new Waypoint(48.302310,11.351692));
        route.addWaypoint(new Waypoint(48.302714,11.352245));
        route.addWaypoint(new Waypoint(48.303038,11.353602));

        rgpsMap.setCurrentRoute(route);
        rgpsMap.setStreets(streetMapLoader.getStreets());
        rgpsMap.updateCurrentPosition(48.301875,11.351336);
        rgpsMap.setScale(1.8);

    }

    private static JPanel drawPanel(){
        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(Color.BLACK);
                g.fillRect(0,0,800,480);
                rgpsMap.draw((Graphics2D) g);
                //repaint();
            }
        };
        return panel;
    }

}
