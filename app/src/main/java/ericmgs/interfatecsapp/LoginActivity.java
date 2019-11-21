package ericmgs.interfatecsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ericmgs.interfatecsapp.auxiliar.Login;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    Button btnCadastrar;
    EditText edtEquipe;
    EditText edtSenha;
    Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        edtEquipe = findViewById(R.id.edtEquipe);
        edtSenha = findViewById(R.id.edtSenha);
        login = new Login(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = login.login(edtEquipe.getText().toString(), (edtSenha.getText().toString()));
                Intent returnIntent = new Intent();
                if(result) {
                    returnIntent.putExtra(getString(R.string.intent_code_result), result);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this, registerActivity.class);
                startActivityForResult(it, 3);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super .onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3) {
            if(resultCode == Activity.RESULT_OK){
                boolean result = data.getBooleanExtra(getString(R.string.intent_code_result), false);
                if(result) {
                    Toast.makeText(LoginActivity.this, "Equipe cadastrada", Toast.LENGTH_SHORT).show();
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Login cancelado
            }
        }
    }
}
