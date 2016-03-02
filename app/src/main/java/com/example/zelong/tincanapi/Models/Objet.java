package com.example.zelong.tincanapi.Models;

/**
 * Created by zelong on 3/2/16.
 */
public class Objet {

    private String objectType;
    private Object data;

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
