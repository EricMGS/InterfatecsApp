package ericmgs.interfatecsapp.auxiliar;

import android.content.Context;
import android.widget.Toast;

import ericmgs.interfatecsapp.R;

public class Login {
    private Context context;
    private saveData sd;
    private String equipe = null;
    private String senha = null;
    private final int LOGIN_KEY = R.string.pref_logged;

    public Login(Context context) {
        this.context = context;
        sd = new saveData(context);
    }

    public boolean login(String equipe, String senha) {
        if(equipe.equals("")) {
            Toast.makeText(context, "Campo equipe vazio!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(senha.equals("")) {
            Toast.makeText(context, "Campo senha vazio!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            sd.setData(LOGIN_KEY, true);
            this.equipe = equipe;
            this.senha = senha;
            return true;
        }
    }

    public void logout() {
        sd.setData(LOGIN_KEY, false);
        this.equipe = null;
        this.senha = null;
    }

    public boolean isLogged() {
        return sd.getBoolData(LOGIN_KEY);
    }

    public String getEquipe() {
        return equipe;
    }
}
