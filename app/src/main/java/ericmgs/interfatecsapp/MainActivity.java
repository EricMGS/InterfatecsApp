package ericmgs.interfatecsapp;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;


import ericmgs.interfatecsapp.auxiliar.saveData;
import ericmgs.interfatecsapp.menu.AboutActivity;
import ericmgs.interfatecsapp.menu.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Hide Status Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_edicao_atual, R.id.nav_preparacao,
                R.id.nav_sobre, R.id.nav_ranking, R.id.nav_edicoes_anteriores,
                R.id.nav_formato_e_regras, R.id.nav_como_chegar)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        int diasRestantes = new saveData(this).getIntData(R.string.pref_remaining_time);
        criarNotificacao("Interfatecs", "Dias restantes: " + String.valueOf(diasRestantes));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent it;
        switch (item.getItemId()) {
            case R.id.action_settings:
                it = new Intent(this, SettingsActivity.class);
                startActivity(it);
                return true;
            case R.id.action_sobre_o_app:
                it = new Intent(this, AboutActivity.class);
                startActivity(it);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void criarNotificacao(String titulo, String texto){

        // 01. Definir as propriedades da Notificação
        final int NOTIFICATION_ID = 123;
        final String CHANNEL_ID = "Notificação";

        // 02. Instanciar o gerenciador de notificações
        android.app.NotificationManager notificationManager = (android.app.NotificationManager) this.getSystemService(android.content.Context.NOTIFICATION_SERVICE);


        // 03. Definir um Canal de Notificação para API >= 28
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = android.app.NotificationManager.IMPORTANCE_HIGH;
            android.app.NotificationChannel canal = new android.app.NotificationChannel(CHANNEL_ID, "canal", importance);
            canal.setDescription("Canal de Notificação");
            canal.enableLights(true);
            canal.setLightColor(android.graphics.Color.RED);
            canal.enableVibration(true);
            canal.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            canal.setShowBadge(true);
            notificationManager.createNotificationChannel(canal);
        }

        // 04. Especificar o ícone, o título e a mensagem da notificação
        androidx.core.app.NotificationCompat.Builder builder = new androidx.core.app.NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(titulo)
                .setContentText(texto);

        // 05. Definir qual Atividade será chamada quando o usuário clicar na notificação
        androidx.core.app.TaskStackBuilder stackBuilder = androidx.core.app.TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(new Intent(this, MainActivity.class));
        android.app.PendingIntent it = stackBuilder.getPendingIntent(0, android.app.PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(it);

        // 06. Exibir a notificação
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
