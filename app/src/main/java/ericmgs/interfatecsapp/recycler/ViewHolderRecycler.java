package ericmgs.interfatecsapp.recycler;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import ericmgs.interfatecsapp.R;

public class ViewHolderRecycler extends RecyclerView.ViewHolder {
    Button btnSettingsItem;

    public ViewHolderRecycler(View view){
        super(view);

        btnSettingsItem = (Button)view.findViewById(R.id.btnSettingsItem);
    }
}
