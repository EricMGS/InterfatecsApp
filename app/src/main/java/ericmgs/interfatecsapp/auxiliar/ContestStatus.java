package ericmgs.interfatecsapp.auxiliar;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;

import ericmgs.interfatecsapp.R;

public class ContestStatus {
    private saveData sd;
    private TextView txtContestStatus;
    private TextView txtRemainingTime;
    private Button btnHomePage;

    //Constants
    private final String FIRST_PHASE = "FIRST_PHASE";
    private final String SECOND_PHASE = "SECOND_PHASE";
    private final String FIRST_PHASE_RUNNING = "FIRST_PHASE_RUNNING";
    private final String SECOND_PHASE_RUNNING = "SECOND_PHASE_RUNNING";
    private final String SUBSCRIPTION = "SUBSCRIPTION";
    private final String CLOSED = "CLOSED";
    private final String NEXT = "NEXT";
    private final int STATUS_KEY = R.string.pref_status;
    private final int REMAINING_TIME_KEY = R.string.pref_remaining_time;

    public ContestStatus(Context context, View v) {
        this.sd = new saveData(context);
        if(sd.getStringData(STATUS_KEY).equals(""))
            sd.setData(STATUS_KEY, SUBSCRIPTION);

        txtContestStatus = v.findViewById(R.id.txtContestStatus);
        txtRemainingTime = v.findViewById(R.id.txtRemainingTime);
        btnHomePage = v.findViewById(R.id.btnHomePage);
    }

    private void setFirstPhase() {
        sd.setData(STATUS_KEY, FIRST_PHASE);
    }

    private void setFirstPhaseRunning() {
        sd.setData(STATUS_KEY, FIRST_PHASE_RUNNING);
    }

    private void setSecondPhase() {
        sd.setData(STATUS_KEY, SECOND_PHASE);
    }

    private void setSecondPhaseRunning() {
        sd.setData(STATUS_KEY, SECOND_PHASE_RUNNING);
    }

    private void setClosed() {
        sd.setData(STATUS_KEY, CLOSED);
    }

    private void setSubscription() {
        sd.setData(STATUS_KEY, SUBSCRIPTION);
    }

    public void setStatus(String status) {
        String currentStatus = sd.getStringData(STATUS_KEY);
        if (status.equals(NEXT)) {
            switch (currentStatus) {
                case SUBSCRIPTION:
                    this.setFirstPhase();
                    break;
                case FIRST_PHASE:
                    this.setFirstPhaseRunning();
                    break;
                case FIRST_PHASE_RUNNING:
                    this.setSecondPhase();
                    break;
                case SECOND_PHASE:
                    this.setSecondPhaseRunning();
                    break;
                case SECOND_PHASE_RUNNING:
                    this.setClosed();
                    break;
                case CLOSED:
                    this.setSubscription();
                    break;
            }
        } else {
            switch (status) {
                case SUBSCRIPTION:
                    this.setSubscription();
                    break;
                case FIRST_PHASE:
                    this.setFirstPhase();
                    break;
                case FIRST_PHASE_RUNNING:
                    this.setFirstPhaseRunning();
                    break;
                case SECOND_PHASE:
                    this.setSecondPhase();
                    break;
                case SECOND_PHASE_RUNNING:
                    this.setSecondPhaseRunning();
                    break;
                case CLOSED:
                    this.setClosed();
                    break;
            }
        }
    }

    public void setRemainingTime(int remainingTime) {
        sd.setData(REMAINING_TIME_KEY, remainingTime);
    }

    public void updateLayout() {
        String status = sd.getStringData(STATUS_KEY);
        int remainingTime = sd.getIntData(REMAINING_TIME_KEY);
        txtContestStatus.setText(status);
        txtRemainingTime.setText(R.string.txt_remaining_time + String.valueOf(remainingTime));
    }

}