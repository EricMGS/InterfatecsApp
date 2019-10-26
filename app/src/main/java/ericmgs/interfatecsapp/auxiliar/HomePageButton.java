package ericmgs.interfatecsapp.auxiliar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import ericmgs.interfatecsapp.LoginActivity;
import ericmgs.interfatecsapp.R;

public class HomePageButton {
    Button btn;
    Context context;

    public HomePageButton(Button btn, Context context) {
        this.btn = btn;
        this.context = context;
    }

    public void setLogin() {
        btn.setText(context.getString(R.string.btn_login));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, LoginActivity.class);
                ((Activity)context).startActivityForResult(it, 1);
            }
        });
    }

    public void setVerEquipe() {
        btn.setText(context.getString(R.string.btn_verEquipe));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
