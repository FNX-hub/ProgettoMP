package com.fnxhub.bluetooth;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleViewInflater {

    private Context context;

    RecycleViewInflater(Context context) {
        this.context = context;
    }
    void inflate(RecyclerView recyclerView, List<Device> device) {
        CustomAdapter adapter = new CustomAdapter(device);
        recyclerView.setAdapter(adapter);
    }

    public Context getContext() {
        return context;
    }
}

