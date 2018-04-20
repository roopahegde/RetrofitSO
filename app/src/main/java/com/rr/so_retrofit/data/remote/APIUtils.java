package com.rr.so_retrofit.data.remote;

public class APIUtils {
    public static final String BASE_URL = "https://api.stackexchange.com/2.2/";

    public static SOService getSOService(){
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
