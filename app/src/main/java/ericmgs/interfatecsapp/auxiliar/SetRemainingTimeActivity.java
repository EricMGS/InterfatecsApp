package ericmgs.interfatecsapp.auxiliar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ericmgs.interfatecsapp.R;

public class SetRemainingTimeActivity extends AppCompatActivity {
    Button btn;
    EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_remaining_time);

        edt = findViewById(R.id.edtNumeroDeDias);
        btn = findViewById(R.id.btnNumDiasOK);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", Integer.valueOf(edt.getText().toString()));
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }
}
