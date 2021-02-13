package com.robosoft.dto.requestInputs.user;

import com.robosoft.dto.requestInputs.SimpleRequestInputs;
import play.core.parsers.Multipart;
import play.mvc.BodyParser;
import play.mvc.Result;


public class UserReqInput extends SimpleRequestInputs {

    private String nameofPhotographer;

    private Multipart photo;

    public String getNameofPhotographer() {
        return nameofPhotographer;
    }

    public void setNameofPhotographer(String nameofPhotographer) {
        this.nameofPhotographer = nameofPhotographer;
    }

    public Multipart getPhoto() {
        return photo;
    }

    public void setPhoto(Multipart photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "UserReqInput{" +
                "nameofPhotographer='" + nameofPhotographer + '\'' +
                ", photo=" + photo +
                '}';
    }


}
