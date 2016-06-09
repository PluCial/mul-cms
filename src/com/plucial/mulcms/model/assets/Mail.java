package com.plucial.mulcms.model.assets;

import java.io.Serializable;

import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class Mail extends Assets implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    } 
}
