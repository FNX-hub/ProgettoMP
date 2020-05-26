package it.tranigrillo.bluetoothproject;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicBoolean;

public class SwitchClickListener extends Activity implements Switch.OnCheckedChangeListener {

    private Context context;
    private Switch swtVisible;
    private Switch swtOnOff;
    private LinearLayout time; //timer discoverability/visibilità
    private TextView timeDynamic;
    private View info; //Messaggio informativo "attiva bluetooth"
    private Toast toastBTnotifier;
    private BluetoothManager BTManager;
    private CountDownTimer countDownDiscoverability;

    final static int DISCOVERABILITY_TIME = 300; //tempo in secondi di discoverability


    public SwitchClickListener(Context context, View view) {
        this.context = context;
        this.info = view.findViewById(R.id.cvInfo);
        this.time = view.findViewById(R.id.llTimer);
        this.timeDynamic = view.findViewById(R.id.tvtimerDynamuc);
        this.swtVisible = view.findViewById(R.id.swtBtVisible);
        this.swtOnOff = view.findViewById(R.id.swtBtOnOff);
        this.BTManager = new BluetoothManager(context);
        this.toastBTnotifier = new Toast(context);
    }

    @Override
    //button view è l'oggetto che viene cliccato
    //se viene cliccato isChecked diventa true
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Integer errCode;
        //Se lo switch OnOff è stato attivato
        if (isChecked) {
            //hai cliccato lo switch ON OFF
            if (buttonView.getId() == swtOnOff.getId()) {
                Log.d("debugTEMP", "HAI CLICCATO SWT ON OFF");
                if (BTManager.isSupported()) {
                    Log.d("debugTEMP", "SUPPORTA IL BLUETOOTH");
                    //Questo dispositivo supporta il bluetooth
                    errCode = BTManager.enableBluetooth();
                    Log.d("debugTEMP", "PUO ATTIVARE IL BLUETOOTH , ERRCODE=" + errCode.toString());
                    switch (errCode) {
                        case 0:
                            Toast.makeText(context, R.string.ToastTextBluetoothOn, Toast.LENGTH_SHORT).show();;
                            buttonView.setText(R.string.on);
                            info.setVisibility(View.GONE); //fai sparire il messaggio informativo "attiva bluetooth"
                            swtVisible.setEnabled(true); //rendi cliccabile lo switch della discoverability
                            break;
                        case 1:
                            Toast.makeText(context, R.string.ToastTextBluetoothAlreadyOn, Toast.LENGTH_SHORT).show();;
                            break;
                        default:
                            Toast.makeText(context, R.string.ToastTextBluetoothErr, Toast.LENGTH_SHORT).show();;
                            break;
                    } //chiusura switch errcode abilita bluetooth
                }
                else{
                    //Questo dispositivo NON supporta il bluetooth
                    Log.d("errCodeEnableBT", "NON SUPPORTA BT");
                    Toast.makeText(context, R.string.ToastTextNoBluetooth, Toast.LENGTH_SHORT).show();
                    //disabilita lo switch OnOff
                    swtOnOff.setChecked(false);
                    info.setVisibility(View.VISIBLE); //fai sparire il messaggio informativo "attiva bluetooth"
                    buttonView.setText(R.string.off);
                    swtVisible.setChecked(false);
                }
            }

            if (buttonView.getId() == swtVisible.getId() ){
                Log.d("debugTEMP", "HAI CLICCATO SWT DISCOVER");
                //MODIFICA L'INTERFACCIA
                buttonView.setText(R.string.visible);
//                swtVisible.setEnabled(true);
                time.setVisibility(View.VISIBLE); //rendi visibile la parte statica della stringa del timer

                //RENDITI VISIBILE E AVVIA IL TIMER
                BTManager.enableDiscoverability(DISCOVERABILITY_TIME);
                Integer counter = DISCOVERABILITY_TIME;


                countDownDiscoverability = new CountDownTimer(DISCOVERABILITY_TIME * 1000,1000) {
                    Integer counter = DISCOVERABILITY_TIME;
                    Integer counterSec;
                    Integer counterMin;
                    @Override
                    public void onTick(long millisUntilFinished) {
                        Log.d("debugTEMP","tic tac");

                        //convertitore rapido
                        counterMin = counter / 60;
                        counterSec = counter % 60;

                        timeDynamic.setText(counterMin.toString() + ":" + counterSec.toString());
                        counter--;
                    }

                    @Override
                    public void onFinish() {
                        swtVisible.setChecked(false);
                        time.setVisibility(View.GONE);
                    }
                }.start();


                Toast.makeText(context, R.string.ToastTextDiscoverabilityOn, Toast.LENGTH_SHORT).show();
                Log.d("debugTEMP","Sono dopo il timer");
            } //chiusURA hai premuto SWITCH VISIBLE
        } //CHIUDI is checked


        //CHECKED = FALSE
        else{
            if (buttonView.getId() == swtOnOff.getId()){

                Log.d("debugTEMP", " HAI messo SWT ON OFF su off");

                buttonView.setText(R.string.off);
                info.setVisibility(View.VISIBLE); //fai ricomparire messaggio informativo "attiva bluetooth"
                swtVisible.setEnabled(false); //rendi NON cliccabile lo switch della discoverability

                //Da attivare a prescindere se hai attivato o meno lo switch della discoverability
                swtVisible.setChecked(false);
                time.setVisibility(View.GONE); //fai sparire il timer della discoverability

                countDownDiscoverability.cancel(); //distruggi il timer
                Toast.makeText(context, R.string.ToastTextBluetoothOff, Toast.LENGTH_SHORT).show();
            }
            if(buttonView.getId() == swtVisible.getId() ) {

                Log.d("debugTEMP", " HAI messo SWT discover su off");

                countDownDiscoverability.cancel(); //distruggi il timer
                time.setVisibility(View.GONE);
                //timeDynamic.setVisibility(View.GONE);
            }
        }
    }  //chiudi onCheckedChanged
} //chiudi classe