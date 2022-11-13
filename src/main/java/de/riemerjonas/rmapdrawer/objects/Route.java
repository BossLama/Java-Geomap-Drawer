package de.riemerjonas.rmapdrawer.objects;

import java.util.ArrayList;

public class Route {

    /*
        === POSITIONS IDS ===
        -1 = Finish
     */

    private ArrayList<Waypoint> waypoints;
    private int position;

    public Route(){
        waypoints = new ArrayList<>();
        position = 0;
    }

    //ADD A WAYPOINT
    public void addWaypoint(Waypoint waypoint){
        waypoints.add(waypoint);
    }

    //SET POSITION IN ROUTE
    public void setWaypointPosition(int position){
        this.position = position;
    }

    //CHECK NEXT WAYPOINT
    public void checkNextWaypoint(){
        if(!(waypoints.size() < position + 1)){
            position++;
        }else position = -1;
    }

    //GET IS ROUTE FINISHED
    public boolean isFinished(){
        return position == -1;
    }


    //GET NEXT WAYPOINT
    public Waypoint getNextWaypoint(){
        return waypoints.get(position);
    }

    //GET DISTANCE TO WAYPOINT
    public double getDistanceToNextWaypoint(double lat_current, double lon_current){
        return waypoints.get(position).getDistanceTo(lat_current, lon_current);
    }

    //GET ALL WAYPOINTS
    public ArrayList<Waypoint> getWaypoints(){
        return waypoints;
    }

    //GET CURRENT POSITION
    public int getWaypointPosition(){
        return position;
    }
}
