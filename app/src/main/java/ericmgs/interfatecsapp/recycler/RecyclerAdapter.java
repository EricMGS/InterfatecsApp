package ericmgs.interfatecsapp.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ericmgs.interfatecsapp.R;
import ericmgs.interfatecsapp.auxiliar.settingsFunctions;

public class RecyclerAdapter extends RecyclerView.Adapter {
    private Context context;
    private final List<String> labels;

    public RecyclerAdapter(List<String> labels, Context context) {
        this.labels = labels;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.settings_item, parent, false);
        return new ViewHolderRecycler(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderRecycler vh = (ViewHolderRecycler) holder;

        String labelButton = labels.get(position);
        vh.btnSettingsItem.setId(position);
        vh.btnSettingsItem.setText(labelButton);
        vh.btnSettingsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new settingsFunctions().onClick(v, context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (labels != null)?labels.size():0;
    }
}
