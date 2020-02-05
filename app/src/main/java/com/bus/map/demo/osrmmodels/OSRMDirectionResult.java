package com.bus.map.demo.osrmmodels;

import java.util.List;

public class OSRMDirectionResult {

    private static final long serialVersionUID = 1L;

    /**
     * Details about the geocoding of origin, destination, and waypoints. See <a
     * href="https://developers.google.com/maps/documentation/directions/intro#GeocodedWaypoints">
     * Geocoded Waypoints</a> for more detail.
     */
    private List<OSRMGeocodedWaypoint> geocodedWaypointsList;

    /**
     * Routes from the origin to the destination. See <a
     * href="https://developers.google.com/maps/documentation/directions/intro#Routes">Routes</a> for
     * more detail.
     */
    public OSRMDirectionsRoute routes[];



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public List<OSRMGeocodedWaypoint> getGeocodedWaypointsList() {
        return geocodedWaypointsList;
    }

    public void setGeocodedWaypointsList(List<OSRMGeocodedWaypoint> geocodedWaypointsList) {
        this.geocodedWaypointsList = geocodedWaypointsList;
    }

    public OSRMDirectionsRoute[] getRoutes() {
        return routes;
    }

    public void setRoutes(OSRMDirectionsRoute[] routes) {
        this.routes = routes;
    }
}
