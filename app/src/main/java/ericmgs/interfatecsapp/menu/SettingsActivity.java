package ericmgs.interfatecsapp.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ericmgs.interfatecsapp.R;
import ericmgs.interfatecsapp.recycler.RecyclerAdapterSettings;

public class SettingsActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        List<String> listaLabels = criaLabelsBotoes();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerSettings);
        recyclerView.setAdapter(new RecyclerAdapterSettings(listaLabels, this));

        RecyclerView.LayoutManager layout = new LinearLayoutManager(SettingsActivity.this);
        recyclerView.setLayoutManager(layout);
    }

    private List<String> criaLabelsBotoes() {
        List<String> labels = new ArrayList<>();
        labels.add(getString(R.string.setting_status));
        labels.add(getString(R.string.setting_tempo));
        labels.add(getString(R.string.setting_deslogar));

        return labels;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super .onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK){
                int result = data.getIntExtra("result", 0);
                Toast.makeText(this, "Tempo restante atualizado", Toast.LENGTH_SHORT).show();

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}
