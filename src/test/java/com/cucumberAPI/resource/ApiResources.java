package com.cucumberAPI.resource;

public enum ApiResources {

    AddPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");

    private String resources;

    ApiResources(String resources) {
        this.resources=resources;
    }

    public String getResources(){
        return resources;
    }
}
