package ericmgs.interfatecsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ericmgs.interfatecsapp.auxiliar.Login;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edtEquipe;
    EditText edtSenha;
    Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        edtEquipe = findViewById(R.id.edtEquipe);
        edtSenha = findViewById(R.id.edtSenha);
        login = new Login(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = login.login(edtEquipe.getText().toString(), (edtSenha.getText().toString()));
                Intent returnIntent = new Intent();
                if(result) {
                    returnIntent.putExtra(getString(R.string.intent_code_login), result);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            }
        });
    }
}
