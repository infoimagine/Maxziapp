package com.zingbytes.maxziapp.model;


public class ItemObject {


    private String heading;
    private String description;
    private String photo;

    public ItemObject(String heading, String photo, String description) {
        this.heading = heading;
        this.photo = photo;
        this.description = description;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}