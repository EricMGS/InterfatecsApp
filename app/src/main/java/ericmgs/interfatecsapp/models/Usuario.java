package ericmgs.interfatecsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Usuario {
    @SerializedName("registered")
    @Expose
    private Registered registered;

    public Registered getRegistered() {
        return registered;
    }

    public void setRegistered(Registered registered) {
        this.registered = registered;
    }
}
