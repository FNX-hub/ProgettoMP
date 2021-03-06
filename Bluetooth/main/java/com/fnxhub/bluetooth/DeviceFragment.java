package com.fnxhub.bluetooth;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


// Classe che dovrebbe prendere in input un array di device e il contesto
// Crea il layout della scermata device
// popola la recycleView con gli elemetnti dell'array
//

public class DeviceFragment extends Fragment {

    private final Context context;

    public DeviceFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIstanceState) {
        View root = inflater.inflate(R.layout.layout_connected, container, false);

//      procedura per l'inflate della recycle view
        RecyclerView recyclerView = root.findViewById(R.id.rvConnected);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        RecycleViewInflater recycleInflater = new RecycleViewInflater(context);
        ArrayList<Device> device = new ArrayList<>();
//  funzione dummy da eliminare quando implementato l'array
        for (int i = 0; i < 10; i++) {
            device.add(new Device(getResources().getString(R.string.device_name), getResources().getString(R.string.statusConnected)));
        }
//
        recycleInflater.inflate(recyclerView, device);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return root;
    }
}








