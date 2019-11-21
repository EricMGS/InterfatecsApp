package ericmgs.interfatecsapp.auxiliar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class settingsFunctions {
    public void onClick(View v, Context context) {
        switch(v.getId()) {
            case 0:
                //final ContestStatus cs = new ContestStatus();
                //cs.setStatus("NEXT");
                new ContestStatus(context, v).setStatus("NEXT");
                Toast.makeText(context, "teste", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Intent it = new Intent(context, SetRemainingTimeActivity.class);
                ((Activity)context).startActivityForResult(it, 2);
                break;
            case 2:
                new Login(context).logout();
                Toast.makeText(context, "Deslogado", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
