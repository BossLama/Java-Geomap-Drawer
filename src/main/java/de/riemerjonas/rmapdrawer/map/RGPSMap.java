package de.riemerjonas.rmapdrawer.map;

import de.riemerjonas.rmapdrawer.data.NavigationColors;
import de.riemerjonas.rmapdrawer.objects.Route;
import de.riemerjonas.rmapdrawer.objects.Street;
import de.riemerjonas.rmapdrawer.objects.Waypoint;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RGPSMap {

    /*
        Scale -> scale * 100px = 1km on map
     */

    private int x, y, width, height;
    private double lat_current, lon_current, scale;
    private Route route;

    private Color path_color = new Color(252, 182, 3);
    private Color current_color = new Color(252, 98, 3);

    private ArrayList<Street> streets;

    public RGPSMap(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.scale = 1;
        this.streets = new ArrayList<>();
        NavigationColors.setWidth(x, y, width);
    }

    //SETTING SCALE-FACTOR
    public void setScale(double scale){
        this.scale =scale;
    }

    //GET SCALE FACTOR
    public double getScale(){
        return scale;
    }

    //SET PATH COLOR
    public void setNavColor(Color path_color, Color current_position_color){
        this.path_color = path_color;
        this.current_color = current_position_color;
    }

    //SET CURRENT ROUTE
    public void setCurrentRoute(Route route){
        this.route = route;
    }

    //GET CURRENT ROUTE
    public Route getCurrentRoute(Route route){
        return route;
    }

    //SETTING CURRENT GEO_POSITION
    public void updateCurrentPosition(double lat, double lon){
        this.lat_current = lat;
        this.lon_current = lon;
    }

    //SETTING STREETMAP
    public void setStreets(ArrayList<Street> streets){
        this.streets = streets;
    }

    //DRAW ON MAP
    public void draw(Graphics2D g2d){
        int middle_x = x + width / 2;
        int middle_y = y + height / 2;

        int last_waypoint_x = -1;
        int last_waypoint_y = -1;

        g2d.setColor(Color.RED);
        g2d.fillOval(middle_x, middle_y, 10, 10);

        for(Street street : streets){
            //BERECHNE DEN ERSTEN WEGPUNKT
            Waypoint first = street.getWaypoints().get(0);
            double distance_x = - first.getDistanceNorthSouth(lat_current, lon_current);
            double distance_y = first.getDistanceEastOst(lat_current, lon_current);

            int distance_x_px = (int) (distance_x * 100 * scale);
            int distance_y_px = (int) (distance_y * 100 * scale);

            int waypoint_x = middle_x + distance_x_px;
            int waypoint_y = middle_y + distance_y_px;

            //SETZTE DEN LETZTEN WEGPUNKT AUF DIESEN
            last_waypoint_x = waypoint_x;
            last_waypoint_y =waypoint_y;

            //SETZE STRASSENFARBE
            g2d.setStroke(new BasicStroke(2));
            if(street.getType().equalsIgnoreCase("residential")){
                g2d.setStroke(new BasicStroke(3));
            }
            if(street.getType().equalsIgnoreCase("secondary")){
                g2d.setStroke(new BasicStroke(5));
            }
            if(street.getType().equalsIgnoreCase("tertiary")){
                g2d.setStroke(new BasicStroke(4));
            }

            g2d.setPaint(street.getColor());

            //BERECHNE ANDERE WEGPUNKTE UND ZEICHNE LINIE
            for(int i = 1; i < street.getWaypoints().size(); i++){
                Waypoint waypoint = street.getWaypoints().get(i);
                distance_x = - waypoint.getDistanceNorthSouth(lat_current, lon_current);
                distance_y = waypoint.getDistanceEastOst(lat_current, lon_current);

                distance_x_px = (int) (distance_x * 100 * scale);
                distance_y_px = (int) (distance_y * 100 * scale);

                waypoint_x = middle_x + distance_x_px;
                waypoint_y = middle_y + distance_y_px;

                g2d.drawLine(last_waypoint_x, last_waypoint_y, waypoint_x, waypoint_y);

                //SETZTE DEN LETZTEN WEGPUNKT AUF DIESEN
                last_waypoint_x = waypoint_x;
                last_waypoint_y =waypoint_y;
            }
        }

    }
}
