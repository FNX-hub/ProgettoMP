package com.fnxhub.bluetooth;

import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

// listner per il comportamento degli switch
// gestisce il comportamento confrontando l'id dell'oggetto

public class SwitchClickListner implements Switch.OnCheckedChangeListener {

    private Context context;
    private Switch swtVisible;
    private LinearLayout time;
    private TextView timeDynamic;
    private View info;

    public SwitchClickListner(Context context, View view) {
        this.context = context;
        this.info = view.findViewById(R.id.cvInfo);
        this.time = view.findViewById(R.id.llTimer);
        this.timeDynamic = view.findViewById(R.id.tvtimerDynamuc);
        this.swtVisible = view.findViewById(R.id.swtBtVisible);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if (buttonView.getId() != swtVisible.getId()) {
                // Switch Bluetooth ON
                buttonView.setText(context.getResources().getString(R.string.on));
                info.setVisibility(View.GONE);
                swtVisible.setEnabled(true);
            } else {
//              Switch Visibility ON
                buttonView.setText(context.getResources().getString(R.string.visible));
                swtVisible.setEnabled(true);
                time.setVisibility(View.VISIBLE);
                timeDynamic.setVisibility(View.VISIBLE);
            }
        }
        else {
            if (buttonView.getId() != swtVisible.getId()) {
                //Switch bluetooth OFF
                buttonView.setText(context.getResources().getString(R.string.off));
                info.setVisibility(View.VISIBLE);
                swtVisible.setChecked(false);
                swtVisible.setEnabled(false);
                time.setVisibility(View.GONE);
            } else {
              //Switch Visibility OFF
              swtVisible.setText(context.getResources().getString(R.string.not_visible));
              time.setVisibility(View.GONE);
            }
        }
    }
}