package com.reflection.model;

import java.util.Date;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

/*
 * An extension of ParseObject that makes
 * it more convenient to access information
 * about a given shot
 */

@ParseClassName("RModel")
public class RModel extends ParseObject {

    public RModel() {
        // A default constructor is required.
    }

    public ParseUser getAuthor() {
        return getParseUser("user");
    }

    public void setAuthor(ParseUser user) {
        put("user", user);
    }

    public void setTitle(String s) {
        put("title", s);
    }

    public String getTitle() {
        return this.getString("title");
    }

    public void setResponse(String q) {
        put("res", q);
    }

    public String getResponse() {
        return this.getString("res");
    }

    public ParseFile getImage() {
        return this.getParseFile("img");
    }

    public void setImage(ParseFile image) {
        put("img", image);
    }

    public void setPersonal(Boolean l) {
        put("personal", l);
    }

    public Boolean getPersonal() {
        return this.getBoolean("personal");
    }

}