package com.robosoft.service;

public class FavouritePhotoService extends Service {

    private static FavouritePhotoService instance;
    public static FavouritePhotoService getInstance() {
        if (instance == null) {
            instance = new FavouritePhotoService();
        }
        return instance;
    }

    private FavouritePhotoService() {
    }
}
