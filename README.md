# RMapDrawer

Java-Libary um Koordinaten und Straßennetze via Graphics2D auf ein JPanel zeichnen zu können.

## Straßen- und Kartendaten

Um Kartendaten zu konvertieren, wird das Format geojson benötigt. 
Verwenden Sie die Klasse StreetMapLoader um die Daten zu konvertieren.

### Straßen aus geojson extrahieren

```java
StreetMapLoader streetMapLoader = new StreetMapLoader();
streetMapLoader.convertMapData("pathto.geojson", "pathtosavefile.json");
```

### Straßendaten laden
```java
StreetMapLoader streetMapLoader = new StreetMapLoader();
streetMapLoader.load("pathtosavefile.json");
```

### Mit Straßendaten arbeiten
```java
StreetMapLoader streetMapLoader = new StreetMapLoader();
streetMapLoader.load("pathtosavefile.json");
ArrayList<Street> streets = streetMapLoader.getStreets();
```


### Street-Object
In diesem Objekt werden folgende Attribute gespeichert:
- String name (Straßenname)
- ArrayList<Waypoint> waypoints (Wegpunkte der Straßen)
- Integer maxspeed (Tempolimit)
- String type (Straßentyp - vgl. Openstreetmap - Key highway)
```java
Street street = new Street("name");
```

### Waypoint-Object
Ein Wegpunkt speichert eine Koordinate (lat, lon)
```java
double lat = 49.7643;
double lon = 11.6762
Waypoint waypoint = new Waypoint(lat, lon);
```
Zudem können in Wegpunkten Distanzen zu einer anderen Koordinate berechnet werden:
```java
double distance = waypoint.getDistanceTo(48.8725, 11.7643);
double distance_x = waypoint.getDistanceNorthSouth(48.8725, 11.7643);
double distance_y = waypoint.getDistanceEastWest(48.8725, 11.7643)
```

## Straßennetz zeichnen
```java
int x = 0;
int y = 0;
int width = 400;
int height = 400;

RGPSMap rgpsMap = new RGPSMap(x, y, width, height);
rgpsMap.setStreets(streetMapLoader.getStreets());     //ÜBERGIBT ALLE STRASSEN
rgpsMap.updateCurrentPosition(48.3, 11.3);            //SETZT DIE AKTUELLE POSITION (ist immer die Mitte der Karte)
rgpsMap.setScale(7);                                  //1km = 100px * scale
```


