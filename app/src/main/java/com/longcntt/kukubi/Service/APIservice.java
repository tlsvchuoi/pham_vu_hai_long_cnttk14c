package com.longcntt.kukubi.Service;

public class APIservice {
    private static String base_url = "http://192.168.16.100/kukubi/";

    public static Dataservice getDataservice(){
        return new APIRetrofit().getClient(base_url).create(Dataservice.class);
    }
}
