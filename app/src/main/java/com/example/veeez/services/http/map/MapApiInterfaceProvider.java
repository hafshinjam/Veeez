package com.example.veeez.services.http.map;


public class MapApiInterfaceProvider {
    private static MapApiInterface mapApiInterface;

    public static MapApiInterface getInstance() {
        if (mapApiInterface == null){
            mapApiInterface = MapApiService.getRetrofit().create(MapApiInterface.class);
        }
        return mapApiInterface;
    }
}
