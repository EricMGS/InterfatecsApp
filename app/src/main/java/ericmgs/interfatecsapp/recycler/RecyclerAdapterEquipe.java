package ericmgs.interfatecsapp.recycler;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import ericmgs.interfatecsapp.R;
import ericmgs.interfatecsapp.models.Equipe;

public class RecyclerAdapterEquipe extends RecyclerView.Adapter<RecyclerAdapterEquipe.ViewHolder> implements Filterable{
    private final List<Equipe> dados;
    private List<Equipe> dadosFiltrados;
    private View.OnClickListener onItemClick;
    private final Context contexto;

    public RecyclerAdapterEquipe(List<Equipe> dados, Context contexto){
        this.dados = dados;
        this.dadosFiltrados = dados;
        this.contexto = contexto;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 1) {
            View v = LayoutInflater.from(contexto).inflate(R.layout.equipe_item, viewGroup, false);
            return new ViewHolder(v);
        } else if (i == 0) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.equipe_item,viewGroup,false);
            return new ViewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        Equipe res = dadosFiltrados.get(i);
        holder.nome.setText(res.getNome());
    }

    public void setOnItemClickListener(View.OnClickListener listener) {
        onItemClick = listener;
    }

    // FILTRAR DADOS
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString();
                List<Equipe> filtered = new ArrayList<>();

                if (query.isEmpty()) {
                    filtered = dados;
                } else {
                    for (Object obj : dados) {
                        Equipe res = (Equipe)obj;
                        if (res.getNome().contains(query.toLowerCase())) {
                            filtered.add(res);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.count = filtered.size();
                results.values = filtered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                dadosFiltrados = (ArrayList<Equipe>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return (dadosFiltrados!=null) ? (dadosFiltrados.size()) : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nome;

        public ViewHolder(View view) {
            super(view);
            nome = itemView.findViewById(R.id.txtNomeDaEquipe);
            view.setTag(this);
            view.setOnClickListener(onItemClick);
        }
    }
}
