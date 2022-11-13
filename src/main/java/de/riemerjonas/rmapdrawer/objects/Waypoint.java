package de.riemerjonas.rmapdrawer.objects;

public class Waypoint {

    private double lat, lon;

    public Waypoint(double lat, double lon){
        this.lat = lat;
        this.lon = lon;
    }

    //GETTER

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }


    //SETTER

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    //GET DISTANCE IN KM BETWEEN TO GEO-COORDINATES
    public double getDistanceTo(double lat_to, double lon_to){
        int earthRadiusKm = 6371;

        double dLat = degreesToRadians(lat_to - lat);
        double dLon = degreesToRadians(lon_to - lon);

        double lat1 = degreesToRadians(lat);
        double lat2 = degreesToRadians(lat_to);

        var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2);
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return earthRadiusKm * c;
    }

    //GET DISTANCE EAST / WEST TO GEO-COORDINATE
    public double getDistanceEastOst(double lat_to, double lon_to){
        double distance = getDistanceTo(lat_to, lon);
        if(lat_to < lat){
            return -distance;
        }else return distance;
    }

    public double getDistanceEastOst(Waypoint waypoint){
        return getDistanceEastOst(waypoint.getLat(), waypoint.getLon());
    }

    //GET DISTANCE NORTH/SOUTH TO GEO-COORDINATE
    public double getDistanceNorthSouth(double lat_to, double lon_to){
        double distance = getDistanceTo(lat, lon_to);
        if(lon_to < lon){
            return -distance;
        }else return distance;
    }

    public double getDistanceNorthSouth(Waypoint waypoint){
        return getDistanceNorthSouth(waypoint.getLat(), waypoint.getLat());
    }

    //TURNS DEGREES INTO RADIANS
    private double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180;
    }
}
