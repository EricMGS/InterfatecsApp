package ericmgs.interfatecsapp.recycler;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import ericmgs.interfatecsapp.R;

public class ViewHolderRecyclerSettings extends RecyclerView.ViewHolder {
    Button btnSettingsItem;

    public ViewHolderRecyclerSettings(View view){
        super(view);

        btnSettingsItem = (Button)view.findViewById(R.id.btnSettingsItem);
    }
}
