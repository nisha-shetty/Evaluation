package com.robosoft.models;

import io.ebean.Model;

import javax.persistence.*;

@Entity
@Table(name = "tblvideo")
public class VideoModel extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VideoModelSeq")
    @SequenceGenerator(name = "VideoModelSeq", sequenceName = "tblVideo_SEQ", allocationSize = 1)
    @Column(name = "fldId")
    private Integer id;

    @Column(name = "fldTitle" )
    private String title;

    @Column(name = "fldVideoUrl" )
    private String videoUrl;

    @ManyToOne
    @JoinColumn(name = "fldUser")
    private UserModel user;

    public VideoModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "VideoModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", user=" + user +
                '}';
    }
}
