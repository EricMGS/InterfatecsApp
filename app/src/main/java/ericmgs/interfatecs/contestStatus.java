package ericmgs.interfatecs;

import java.sql.Time;

public class contestStatus {
    private String status = "encerrada";
    private Time remainingTime = new Time(0);

    public void changeToFirstPhase() {
        this.status = "primeira fase";
    }

    public void changeToFirstPhaseRunning() {
        this.status = "primeira fase ocorrendo";
    }

    public void changeToSecondPhase() {
        this.status = "segunda fase";
    }

    public void changeToSecondPhaseRunning() {
        this.status = "segunda fase ocorrendo";
    }

    public void changeToClosed() {
        this.status = "encerrada";
    }

    public void changeToSubscription() {
        this.status = "inscrições";
    }

    public void changeToNextStatus() {
        switch(status) {
            case "inscrições":
                this.changeToFirstPhase();
                break;
            case "primeira fase":
                this.changeToFirstPhaseRunning();
                break;
            case "primeira fase ocorrendo":
                this.changeToSecondPhase();
                break;
            case "segunda fase":
                this.changeToSecondPhaseRunning();
                break;
            case "segunda fase ocorrendo":
                this.changeToClosed();
                break;
            case "encerrada":
                this.changeToSubscription();
                break;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Time getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(Time remainingTime) {
        this.remainingTime = remainingTime;
    }

}
