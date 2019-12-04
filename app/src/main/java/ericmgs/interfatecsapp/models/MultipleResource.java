package ericmgs.interfatecsapp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MultipleResource {
    @SerializedName("results")
    @Expose
    private List<Usuario> results = null;

    public List<Usuario> getResults() {
        return results;
    }

    public void setResults(List<Usuario> results) {
        this.results = results;
    }
}
