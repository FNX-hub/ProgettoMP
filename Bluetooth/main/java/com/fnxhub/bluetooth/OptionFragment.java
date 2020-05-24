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

public class OptionFragment extends Fragment {

    private Context context;
//    private ArrayList<Device> device;

    OptionFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIstanceState){
        View root = inflater.inflate(R.layout.layout_option, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.rvDevices);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        RecycleViewInflater recycleInflater = new RecycleViewInflater(context);
        ArrayList<Device> device = new ArrayList<>();
        for (int i= 0; i < 10; i++) {
            device.add(new Device(getResources().getString(R.string.device_name), getResources().getString(R.string.statusActive)));
        }
        recycleInflater.inflate(recyclerView, device);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return root;
    }
}
