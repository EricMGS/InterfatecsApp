package ericmgs.interfatecsapp.auxiliar;

import ericmgs.interfatecsapp.models.MultipleResource;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceEquipe {
    @GET("?")
    Call<MultipleResource> getUsuario();

}
