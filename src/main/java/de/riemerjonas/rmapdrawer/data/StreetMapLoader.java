package de.riemerjonas.rmapdrawer.data;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.riemerjonas.rmapdrawer.objects.Street;
import de.riemerjonas.rmapdrawer.objects.StreetType;
import de.riemerjonas.rmapdrawer.objects.Waypoint;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class StreetMapLoader {

    String path;
    private ArrayList<Street> streets = new ArrayList<>();
    private int coordinates_length = 0;
    public boolean loading = false;

    public StreetMapLoader(){

    }

    public ArrayList<Street> getStreets(){
        return streets;
    }

    private void save(String path){
        File folder = new File("mapdata");
        File roads = new File("mapdata/" + path);
        if(!folder.exists()) folder.mkdirs();
        if(!roads.exists()){
            try {
                roads.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {

            ArrayList<String> street_json = new ArrayList<>();
            for(Street street : streets){
                String json = new Gson().toJson(street);
                street_json.add(json);
            }

            FileWriter writer = new FileWriter(roads);
            String data = new Gson().toJson(street_json);
            writer.write(data);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(String path){
        try {
            String content =  Files.readString(Paths.get("mapdata/" + path));
            ArrayList<String> jsons = new Gson().fromJson(content, ArrayList.class);
            streets.clear();
            for(String json : jsons){
                Street street = new Gson().fromJson(json, Street.class);
                streets.add(street);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void convertMapData(String path_form, String path_to){
        try {
            String content = Files.readString(Paths.get(path_form));
            JsonObject object = new Gson().fromJson(content, JsonObject.class);
            JsonArray features = object.getAsJsonArray("features");
            for(int i = 0; i < features.size(); i++) {
                Street street = new Street("");
                JsonObject feature = features.get(i).getAsJsonObject();
                JsonObject properties = feature.getAsJsonObject("properties");

                if (properties.has("highway")) {

                    String highway_type = properties.get("highway").getAsString();

                    street.setType(highway_type);


                    JsonObject geometry = feature.getAsJsonObject("geometry");
                    if(geometry.get("type").getAsString().equals("LineString")){
                        JsonArray coordinates = geometry.getAsJsonArray("coordinates");


                        for (int x = 0; x < coordinates.size(); x++) {
                            JsonArray coordinate = coordinates.get(x).getAsJsonArray();
                            double lat = coordinate.get(1).getAsDouble();
                            double lon = coordinate.get(0).getAsDouble();

                            Waypoint waypoint = new Waypoint(lat, lon);
                            street.addWaypoint(waypoint);

                            if (properties.has("name")) {
                                street.setName(properties.get("name").getAsString());
                            }

                            if (properties.has("maxspeed")) {
                                street.setMaxspeed(properties.get("maxspeed").getAsInt());
                            }

                            //System.out.println("Straße " + street.getName() + " ("+ highway_type +") [" + i + "]-->" + lat + ";" + lon);
                        }
                    }else if(geometry.get("type").getAsString().equals("MultiLineString")){
                        JsonArray lines = geometry.getAsJsonArray("coordinates");
                        for(int linesid = 0; linesid < lines.size(); linesid++){
                            JsonArray coordinates = lines.get(linesid).getAsJsonArray();
                            for (int x = 0; x < coordinates.size(); x++) {
                                JsonArray coordinate = coordinates.get(x).getAsJsonArray();
                                double lat = coordinate.get(1).getAsDouble();
                                double lon = coordinate.get(0).getAsDouble();

                                Waypoint waypoint = new Waypoint(lat, lon);
                                street.addWaypoint(waypoint);

                                if (properties.has("name")) {
                                    street.setName(properties.get("name").getAsString());
                                }

                                if (properties.has("maxspeed")) {
                                    street.setMaxspeed(properties.get("maxspeed").getAsInt());
                                }
                            }
                        }
                    }

                    //System.out.println("Street " + street.getName() + " [max "+ street.getMaxspeed()+"] is a " + street.getType());
                    if (street.getWaypoints().size() > 0) {
                        switch (street.getType()){
                            default:
                                streets.add(street);
                                break;
                            case "track":
                                break;
                            case "footway":
                                break;
                            case "path":
                                break;
                            case "steps":
                                break;
                            case "cycleway":
                                break;
                            case "service":
                                break;

                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        save(path_to);
    }


}
