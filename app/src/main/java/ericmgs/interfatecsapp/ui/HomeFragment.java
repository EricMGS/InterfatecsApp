package ericmgs.interfatecsapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ericmgs.interfatecsapp.LoginActivity;
import ericmgs.interfatecsapp.R;
import ericmgs.interfatecsapp.auxiliar.ContestStatus;
import ericmgs.interfatecsapp.auxiliar.HomePageButton;
import ericmgs.interfatecsapp.auxiliar.Login;
import ericmgs.interfatecsapp.auxiliar.saveData;

public class HomeFragment extends Fragment {
    View root;
    Login login;
    Button btnHomePage;
    HomePageButton botaoPrincipal;
    Activity act;
    final int REQUEST_CODE_LOGIN = R.integer.request_code_login;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        this.root = root;
        this.act = getActivity();
        btnHomePage = root.findViewById(R.id.btnHomePage);
        login = new Login(act);
        botaoPrincipal = new HomePageButton(btnHomePage, act);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        final ContestStatus contestStatus = new ContestStatus(act, root);
        contestStatus.updateLayout();


        if(login.isLogged()) {
            botaoPrincipal.setVerEquipe();
        }
        else {
            botaoPrincipal.setLogin();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_LOGIN) {
            if(resultCode == Activity.RESULT_OK){
                boolean result = data.getBooleanExtra(getString(R.string.intent_code_login), false);
                if(result) {
                    Toast.makeText(act, "Logado como " + login.getEquipe(), Toast.LENGTH_SHORT).show();
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Login cancelado
            }
        }
    }
}