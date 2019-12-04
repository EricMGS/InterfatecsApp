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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import ericmgs.interfatecsapp.auxiliar.DatabaseHelper;

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
                            registrarEquipe();
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

    public void registrarEquipe() {
        EditText edtNome = findViewById(R.id.edtCadNomeEquipe);
        String nome = edtNome.getText().toString();
        EditText edtInt1 = findViewById(R.id.edtCadIntegrante1);
        String int1 = edtInt1.getText().toString();
        EditText edtRa1 = findViewById(R.id.edtCadRA1);
        String ra1 = edtRa1.getText().toString();
        EditText edtInt2 = findViewById(R.id.edtCadIntegrante2);
        String int2 = edtInt2.getText().toString();
        EditText edtRa2 = findViewById(R.id.edtCadRA2);
        String ra2 = edtRa2.getText().toString();
        EditText edtInt3 = findViewById(R.id.edtCadIntegrante3);
        String int3 = edtInt3.getText().toString();
        EditText edtRa3 = findViewById(R.id.edtCadRA3);
        String ra3 = edtRa3.getText().toString();
        EditText edtReserva = findViewById(R.id.edtCadReserva);
        String reserva = edtReserva.getText().toString();
        EditText edtra4 = findViewById(R.id.edtCadRA4);
        String ra4 = edtra4.getText().toString();
        EditText edtCoach = findViewById(R.id.edtCoach);
        String coach = edtCoach.getText().toString();
        EditText edtrg = findViewById(R.id.edtRG);
        String rg = edtrg.getText().toString();
        Spinner spnCidade = findViewById(R.id.spnCidade);
        String cidade = spnCidade.getSelectedItem().toString();
        RadioGroup rdgTipo = findViewById(R.id.grpTipoEquipe);
        int selectedRadio = rdgTipo.getCheckedRadioButtonId();
        RadioButton rdbTipo = findViewById(selectedRadio);
        String txtTipo = rdbTipo.getText().toString();
        boolean boolTipo = txtTipo.equals("Equipe Café com leite");
        int tipo = boolTipo == false ? 0 : 1;
        Switch swtPrimeira = findViewById(R.id.swtPrimeiraVez);
        boolean boolPrimeira = swtPrimeira.isChecked();
        int primeira = boolPrimeira == false ? 0 : 1;

        DatabaseHelper bd = new DatabaseHelper(this);
        Intent it = getIntent();
        if(it.getStringExtra("atualizar").equals("true")) {
            String raAntigo1 = it.getStringExtra("raAntigo1");
            bd.updateParticipante(raAntigo1, int1, ra1, cidade, nome, 0);
            String raAntigo2 = it.getStringExtra("raAntigo2");
            bd.updateParticipante(raAntigo2, int2, ra2, cidade, nome, 0);
            String raAntigo3 = it.getStringExtra("raAntigo3");
            bd.updateParticipante(raAntigo3, int3, ra3, cidade, nome, 0);
            String raAntigo4 = it.getStringExtra("raAntigo4");
            bd.updateParticipante(raAntigo4, reserva, ra4, cidade, nome, 1);
            String rgAntigo = it.getStringExtra("rgAntigo");
            bd.updateCoach(rgAntigo, coach, cidade, rg);
            String nomeAntigo = it.getStringExtra("nomeAntigo");
            bd.updateEquipe(nomeAntigo, nome, cidade, int1, int2, int3, reserva, coach, tipo, primeira, "senha", 1);
        }
        else {
            bd.insertParticipante(int1, ra1, cidade, nome, 0);
            bd.insertParticipante(int2, ra2, cidade, nome, 0);
            bd.insertParticipante(int3, ra3, cidade, nome, 0);
            bd.insertParticipante(reserva, ra4, cidade, nome, 1);
            bd.insertCoach(coach, cidade, rg);
            bd.insertEquipe(nome, cidade, int1, int2, int3, reserva, coach, tipo, primeira, "senha", 1);
        }
    }
}