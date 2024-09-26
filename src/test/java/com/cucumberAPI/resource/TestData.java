package com.cucumberAPI.resource;

import com.cucumberAPI.pojo.Location;
import com.cucumberAPI.pojo.SerializeData;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    public SerializeData addPlacePayload(String name, String language, String address){
        SerializeData serializeData=new SerializeData();

        serializeData.setAccuracy(50);
        serializeData.setName(name);
        serializeData.setLanguage(language);
        serializeData.setAddress(address);
        serializeData.setPhone_number("(+91) 983 893 3937");
        serializeData.setWebsite("http://google.com");

        Location location=new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        serializeData.setLocation(location);

        List<String> myList=new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");

        serializeData.setTypes(myList);
        return serializeData;
    }

    public String deletePlacePayload(String placeId){
        return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";

    }
}
