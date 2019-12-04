package ericmgs.interfatecsapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import ericmgs.interfatecsapp.R;
import ericmgs.interfatecsapp.auxiliar.ApiServiceEquipe;
import ericmgs.interfatecsapp.auxiliar.ContestStatus;
import ericmgs.interfatecsapp.auxiliar.HomePageButton;
import ericmgs.interfatecsapp.auxiliar.Login;
import ericmgs.interfatecsapp.models.Equipe;
import ericmgs.interfatecsapp.models.MultipleResource;
import ericmgs.interfatecsapp.models.Registered;
import ericmgs.interfatecsapp.models.Usuario;
import ericmgs.interfatecsapp.recycler.RecyclerAdapterEquipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    View root;
    Login login;
    Button btnHomePage;
    HomePageButton botaoPrincipal;
    Activity act;
    final int REQUEST_CODE_LOGIN = R.integer.request_code_login;
    private static final String TAG = RankingFragment.class.getSimpleName();
    public static final String BASE_URL = "https://randomuser.me/api/";
    private static Retrofit retrofit = null;
    private List<Usuario> resultados;
    ContestStatus contestStatus;
    Date fdata;

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
        contestStatus = new ContestStatus(act, root);
        contestStatus.updateLayout();

        if(login.isLogged()) {
            botaoPrincipal.setVerEquipe();
        }
        else {
            botaoPrincipal.setLogin();
        }

        retornarResultadosAPI();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_LOGIN) {
            if(resultCode == Activity.RESULT_OK){
                boolean result = data.getBooleanExtra(getString(R.string.intent_code_result), false);
                if(result) {
                    Toast.makeText(act, "Logado como " + login.getEquipe(), Toast.LENGTH_SHORT).show();
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Login cancelado
            }
        }
    }

    public void retornarResultadosAPI(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        ApiServiceEquipe api = retrofit.create(ApiServiceEquipe.class);

        Call<MultipleResource> call = api.getUsuario();

        call.enqueue(new Callback<MultipleResource>() {
            @Override
            public void onResponse(Call<MultipleResource> call, Response<MultipleResource> response) {
                resultados = response.body().getResults();
                Usuario usuario = resultados.get(0);
                Registered registered = usuario.getRegistered();
                String data = registered.getDate();
                String mes = data.substring(5,7);
                String dia = data.substring(8,10);
                String sdata = dia + "/" + mes + "/" + "2020";
                try {
                    fdata = new SimpleDateFormat("dd/MM/yyyy").parse(sdata);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date currentTime = Calendar.getInstance().getTime();
                long diff = fdata.getTime() - currentTime.getTime();
                long dias = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                TextView txtRemainingTime = getActivity().findViewById(R.id.txtRemainingTime);
                txtRemainingTime.setText("Dias restantes: " + String.valueOf(dias));
            }

            @Override
            public void onFailure(Call<MultipleResource> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}