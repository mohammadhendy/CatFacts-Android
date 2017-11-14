package com.mohammadhendy.catfacts.model.core;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by mohammadhendy on 11/14/17.
 */

@JsonObject(fieldNamingPolicy = JsonObject.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
public class CatFact {
    @JsonField
    private String fact;
    @JsonField
    private int length;

    public CatFact() {
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public String getFact() {
        return fact;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
