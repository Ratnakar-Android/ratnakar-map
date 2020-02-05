package com.bus.map.demo.osrmmodels;

import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Route {

    private List<Leg> legs = null;
    private String weightName;
    private double weight;
    private double duration;
    private double distance;


    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    public String getWeightName() {
        return weightName;
    }

    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}