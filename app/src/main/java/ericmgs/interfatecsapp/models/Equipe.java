package ericmgs.interfatecsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Equipe {
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("cidade")
    @Expose
    private String cidade;
    @SerializedName("integrante1")
    @Expose
    private Participante integrante1;
    @SerializedName("integrante2")
    @Expose
    private Participante integrante2;
    @SerializedName("integrante3")
    @Expose
    private Participante integrante3;
    @SerializedName("reserva")
    @Expose
    private Participante reserva;
    @SerializedName("coach")
    @Expose
    private Coach coach;
    @SerializedName("cafeComLeite")
    @Expose
    private Boolean cafeComLeite;
    @SerializedName("primeiraParticipacao")
    @Expose
    private Boolean primeiraParticipacao;
    @SerializedName("senha")
    @Expose
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Participante getIntegrante1() {
        return integrante1;
    }

    public void setIntegrante1(Participante integrante1) {
        this.integrante1 = integrante1;
    }

    public Participante getIntegrante2() {
        return integrante2;
    }

    public void setIntegrante2(Participante integrante2) {
        this.integrante2 = integrante2;
    }

    public Participante getIntegrante3() {
        return integrante3;
    }

    public void setIntegrante3(Participante integrante3) {
        this.integrante3 = integrante3;
    }

    public Participante getReserva() {
        return reserva;
    }

    public void setReserva(Participante reserva) {
        this.reserva = reserva;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Boolean getCafeComLeite() {
        return cafeComLeite;
    }

    public void setCafeComLeite(Boolean cafeComLeite) {
        this.cafeComLeite = cafeComLeite;
    }

    public Boolean getPrimeiraParticipacao() {
        return primeiraParticipacao;
    }

    public void setPrimeiraParticipacao(Boolean primeiraParticipacao) {
        this.primeiraParticipacao = primeiraParticipacao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
