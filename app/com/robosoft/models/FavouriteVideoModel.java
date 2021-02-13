package com.robosoft.models;

import io.ebean.Model;

import javax.persistence.*;

@Entity
@Table(name = "tblfavouritevideo")
public class FavouriteVideoModel extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FavouriteVideoSeq")
    @SequenceGenerator(name = "FavouriteVideoSeq", sequenceName = "tblfavouritevideo_SEQ", allocationSize = 1)
    @Column(name = "fldFavouriteVideoId")
    private Integer favouriteVideoId;

    @Column(name = "fldVideoId")
    private Integer videoId;

    @Column(name = "fldUserId")
    private Integer userId;

    @Column(name = "fldIsFavourite")
    private Boolean isFavourite;

    public FavouriteVideoModel() {
    }

    public Integer getFavouriteVideoId() {
        return favouriteVideoId;
    }

    public void setFavouriteVideoId(Integer favouriteVideoId) {
        this.favouriteVideoId = favouriteVideoId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
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
        return "FavouriteVideoModel{" +
                "favouriteVideoId=" + favouriteVideoId +
                ", videoId=" + videoId +
                ", userId=" + userId +
                ", isFavourite=" + isFavourite +
                '}';
    }
}
