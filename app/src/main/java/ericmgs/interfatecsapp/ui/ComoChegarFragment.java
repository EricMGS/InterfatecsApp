package ericmgs.interfatecsapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import ericmgs.interfatecsapp.R;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.security.Permission;

public class ComoChegarFragment extends Fragment implements OnMapReadyCallback{

    private double latitude;
    private double longitude;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_como_chegar, container, false);

        return root;
    }

    public void OnStart() {
        SupportMapFragment mapFragment = (SupportMapFragment)
                getActivity().getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);

        //FATEC-RP
        this.latitude = -21.1875179;
        this.longitude = -47.8336921;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        CameraUpdate cam = CameraUpdateFactory.newLatLngZoom(
                new LatLng(this.latitude, this.longitude),
                16
        );

        googleMap.addMarker(new MarkerOptions().position(new LatLng(this.latitude, this.longitude)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)).title("Fatec RP"));
        googleMap.moveCamera(cam);
        googleMap.animateCamera(cam);
    }
}