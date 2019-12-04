package ericmgs.interfatecsapp.ui;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ericmgs.interfatecsapp.EquipeInfoActivity;
import ericmgs.interfatecsapp.R;
import ericmgs.interfatecsapp.auxiliar.DatabaseHelper;
import ericmgs.interfatecsapp.recycler.RecyclerAdapterEquipe;
import retrofit2.Retrofit;

import android.content.Intent;
import android.util.Log;

import java.util.List;

import ericmgs.interfatecsapp.models.MultipleResource;
import ericmgs.interfatecsapp.models.Equipe;
import ericmgs.interfatecsapp.auxiliar.ApiServiceEquipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RankingFragment extends Fragment {
    private RecyclerAdapterEquipe adapter;
    List<Equipe> equipes;
    RecyclerView recyclerView;
    View rootCtx;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ranking, container, false);
        rootCtx = root;

        return root;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onResume() {
        super .onResume();
        DatabaseHelper db = new DatabaseHelper(getActivity());

        equipes = db.getEquipes();
        adapter = new RecyclerAdapterEquipe(equipes, getActivity());
        adapter.setOnItemClickListener(onItemClick);
        RecyclerView recyclerView = rootCtx.findViewById(R.id.recyclerViewEquipe);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    private View.OnClickListener onItemClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            Equipe res = equipes.get(viewHolder.getAdapterPosition());

            Intent it = new Intent(getActivity(), EquipeInfoActivity.class);
            it.putExtra("nome", res.getNome());
            it.putExtra("cidade", res.getCidade());
            String cafe = res.getCafeComLeite() ? "Sim" : "Não";
            it.putExtra("cafeComLeite", cafe);
            String primeira = res.getPrimeiraParticipacao() ? "Sim" : "Não";
            it.putExtra("primeiraParticipacao", primeira);
            it.putExtra("participante1", res.getIntegrante1().getName());
            it.putExtra("participante2", res.getIntegrante2().getName());
            it.putExtra("participante3", res.getIntegrante3().getName());
            it.putExtra("ra1", res.getIntegrante1().getRa());
            it.putExtra("ra2", res.getIntegrante2().getRa());
            it.putExtra("ra3", res.getIntegrante3().getRa());
            it.putExtra("reserva", res.getReserva().getName());
            it.putExtra("ra4", res.getReserva().getRa());
            it.putExtra("coach", res.getCoach().getNome());
            it.putExtra("rg", res.getCoach().getRg());
            startActivity(it);
        }
    };
}