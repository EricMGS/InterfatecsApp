package ericmgs.interfatecsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class registerActivity extends AppCompatActivity {
    Button btnCadastrar;
    CheckBox chkCiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnCadastrar = findViewById(R.id.btnCadastrarEquipe);
        chkCiente = (CheckBox) findViewById(R.id.chkCiente);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkCiente.isChecked()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(registerActivity.this);
                    builder.setMessage("Confirmar cadastro?");
                    builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent returnIntent = new Intent();
                            returnIntent.putExtra(getString(R.string.intent_code_result), true);
                            setResult(Activity.RESULT_OK, returnIntent);
                            finish();
                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(registerActivity.this, "Cancelado", Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog alerta = builder.create();
                    alerta.show();
                }
                else {
                    Toast.makeText(registerActivity.this, "Marcar campo Ciente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}