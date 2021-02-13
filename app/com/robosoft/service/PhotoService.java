package com.robosoft.service;

public class PhotoService extends Service{

    private static PhotoService instance;
    public static PhotoService getInstance() {
        if (instance == null) {
            instance = new PhotoService();
        }
        return instance;
    }

    private PhotoService() {
    }
}
