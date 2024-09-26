package com.cucumberAPI.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    public static RequestSpecification req;
    public RequestSpecification requestSpecification() throws IOException {

        if(req==null) {
            PrintStream logs = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("browseUrl")).addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(logs))
                    .addFilter(ResponseLoggingFilter.logResponseTo(logs))
                    .setContentType(ContentType.JSON).build();
            return req;
        }
        return req;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties properties=new Properties();
        FileInputStream fos=new FileInputStream("C:\\Users\\Admin\\IdeaProjects\\CucmberFrameworkAPI\\src\\test\\java\\com\\cucumberAPI\\resource\\Global.properties");
        properties.load(fos);
        return (String) properties.get(key);

    }

    public String getJsonPath(Response response, String key){
        String responseString = response.asString();
        JsonPath js = new JsonPath(responseString);
        return js.get(key).toString();
    }
}
