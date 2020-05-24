package com.fnxhub.bluetooth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private List<Device> mDevices;
    CustomAdapter(List<Device> devices) {
        mDevices=devices;
    }

    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View deviceView = inflater.inflate(R.layout.cardview_device, parent, false);
        return new CustomViewHolder(deviceView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {
        Device device = mDevices.get(position);
        TextView tvDeviceName = holder.tvDeviceName;
        tvDeviceName.setText(device.getDeviceName());
        TextView tvStatus = holder.tvStatus;
        tvStatus.setText(device.getStatus());
    }

    @Override
    public int getItemCount() {
        return mDevices.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDeviceName;
        private TextView tvStatus;

        CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        this.tvDeviceName = itemView.findViewById(R.id.tvDeviceName);
        this.tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}

