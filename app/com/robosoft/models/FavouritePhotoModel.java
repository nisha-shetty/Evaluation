package com.robosoft.models;

import io.ebean.Model;

import javax.persistence.*;

@Entity
@Table(name = "tblfavouritephoto")
public class FavouritePhotoModel  extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FavouritePhotoSeq")
    @SequenceGenerator(name = "FavouritePhotoSeq", sequenceName = "tblfavouritephoto_SEQ", allocationSize = 1)
    @Column(name = "fldFavouritePhotoId")
    private Integer favouritePhotoId;

    @Column(name = "fldPhotoId")
    private Integer photoId;

    @Column(name = "fldUserId")
    private Integer userId;

    @Column(name = "fldIsFavourite")
    private Boolean isFavourite;

    public FavouritePhotoModel() {
    }

    public Integer getFavouritePhotoId() {
        return favouritePhotoId;
    }

    public void setFavouritePhotoId(Integer favouritePhotoId) {
        this.favouritePhotoId = favouritePhotoId;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }

    @Override
    public String toString() {
        return "FavouritePhotoModel{" +
                "favouritePhotoId=" + favouritePhotoId +
                ", photoId=" + photoId +
                ", userId=" + userId +
                ", isFavourite=" + isFavourite +
                '}';
    }
}
