package ericmgs.interfatecsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ericmgs.interfatecsapp.auxiliar.DatabaseHelper;

public class EquipeInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipe_info);

        Intent it = getIntent();
        final String nome = it.getStringExtra("nome");
        String cidade = it.getStringExtra("cidade");
        String cafe = it.getStringExtra("cafeComLeite");
        String primeira = it.getStringExtra("primeiraParticipacao");
        String participante1 = it.getStringExtra("participante1");
        String participante2 = it.getStringExtra("participante2");
        String participante3 = it.getStringExtra("participante3");
        final String ra1 = it.getStringExtra("ra1");
        final String ra2 = it.getStringExtra("ra2");
        final String ra3 = it.getStringExtra("ra3");
        String reserva = it.getStringExtra("reserva");
        final String ra4 = it.getStringExtra("ra4");
        String coach = it.getStringExtra("coach");
        final String rg = it.getStringExtra("rg");


        TextView txtNome = findViewById(R.id.txtInfoNomeDaEquipe);
        txtNome.setText(nome);
        TextView txtCidade = findViewById(R.id.txtInfoCidade);
        txtCidade.setText(cidade);
        TextView txtCafe = findViewById(R.id.txtInfoCafeComLeite);
        txtCafe.setText(cafe);
        TextView txtPrimeira = findViewById(R.id.txtInfoPrimeiraParticipacao);
        txtPrimeira.setText(primeira);

        TextView txtParticipante1 = findViewById(R.id.txtInfoParticipante1);
        txtParticipante1.setText(participante1);

        TextView txtParticipante2 = findViewById(R.id.txtInfoParticipante2);
        txtParticipante2.setText(participante2);

        TextView txtParticipante3 = findViewById(R.id.txtInfoParticipante3);
        txtParticipante3.setText(participante3);

        TextView txtReserva = findViewById(R.id.txtInfoReserva);
        txtReserva.setText(reserva);

        TextView txtRa1 = findViewById(R.id.txtInfoRA1);
        txtRa1.setText(ra1);

        TextView txtRa2 = findViewById(R.id.txtInfoRA2);
        txtRa2.setText(ra2);

        TextView txtRa3 = findViewById(R.id.txtInfoRA3);
        txtRa3.setText(ra3);
        TextView txtRa4 = findViewById(R.id.txtInfoRA4);
        txtRa4.setText(ra4);
        TextView txtCoach = findViewById(R.id.txtInfoCoach);
        txtCoach.setText(coach);
        TextView txtRG = findViewById(R.id.txtInfoRG);
        txtRG.setText(rg);


        Button btnExcluir = findViewById(R.id.btnInfoExcluir);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EquipeInfoActivity.this);
                builder.setMessage("Excluir?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        DatabaseHelper dh = new DatabaseHelper(EquipeInfoActivity.this);
                        dh.deleteCoach(rg);
                        dh.deleteParticipante(ra1);
                        dh.deleteParticipante(ra2);
                        dh.deleteParticipante(ra3);
                        dh.deleteParticipante(ra4);
                        dh.deleteEquipe(nome);

                        finish();
                    }
                });
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
                AlertDialog alerta = builder.create();
                alerta.show();
            }
        });

        Button btnUpdate = findViewById(R.id.btnInfoAtualizar);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(EquipeInfoActivity.this, registerActivity.class);
                it.putExtra("atualizar", "true");
                it.putExtra("raAntigo1", ra1);
                it.putExtra("raAntigo2", ra2);
                it.putExtra("raAntigo3", ra3);
                it.putExtra("raAntigo4", ra4);
                it.putExtra("rgAntigo", rg);
                it.putExtra("nomeAntigo", nome);
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
                    Toast.makeText(EquipeInfoActivity.this, "Equipe atualizada", Toast.LENGTH_SHORT).show();
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Login cancelado
            }
        }
    }
}
