package com.robosoft.models;

import io.ebean.Model;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "tblphoto")
public class PhotoModel extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PhotoSeq")
    @SequenceGenerator(name = "PhotoSeq", sequenceName = "tblphoto_SEQ", allocationSize = 1)
    @Column(name = "fldPhotoId")
    private Integer photoId;

    @Column(name = "fldtitle")
    private String title;

    @Column(name = "fldImage")
    private byte[] image;


    @Column(name = "fldPhotoUrl")
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "fldUser")
    private UserModel user;

    public PhotoModel() {
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PhotoModel{" +
                "photoId=" + photoId +
                ", title='" + title + '\'' +
                ", image=" + Arrays.toString(image) +
                ", photoUrl='" + photoUrl + '\'' +
                ", user=" + user +
                '}';
    }
}

