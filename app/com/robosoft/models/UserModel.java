package com.robosoft.models;

import io.ebean.Model;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "tbluser")
public class UserModel extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserSeq")
    @SequenceGenerator(name = "UserSeq", sequenceName = "tblUser_SEQ", allocationSize = 1)
    @Column(name = "fldId")
    private Integer id;

    @Column(name = "fldUserName")
    private String userName;

    @Column(name = "fldProfilePicImage")
    private byte[] profilePicImage;


    @Column(name = "fldProfilePicUrl")
    private String profilePicUrl;


    public UserModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public byte[] getProfilePicImage() {
        return profilePicImage;
    }

    public void setProfilePicImage(byte[] profilePicImage) {
        this.profilePicImage = profilePicImage;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", profilePicImage=" + Arrays.toString(profilePicImage) +
                ", profilePicUrl='" + profilePicUrl + '\'' +
                '}';
    }
}
