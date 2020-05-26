package it.tranigrillo.bluetoothproject;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BluetoothManager extends Activity {
    Context context;
    BluetoothAdapter adapter;
    final static int REQUEST_ENABLE_BT = 934;

    public BluetoothManager(Context context){
        this.context = context;
        this.adapter = BluetoothAdapter.getDefaultAdapter(); //ottieni un bluetooth adapter
    }

    public Boolean isSupported(){
//        if(this.adapter == null){
//            return false;
//        }
        Log.d("debugTEMP", ">>>>> sono dummy, ho un bluetooth che non esiste");
        return true;
    }


    public Integer enableBluetooth(){
        Log.d("debugTEMP", ">>>>> sono dummy e sto attivando il mio bluetoooth");
//        if (!adapter.isEnabled()) {
//            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
//            return 0; //attivazione effettuata con successo
//        }
//        return 1; //era gia' attivo
//        //return -1 //errore

        Log.d("debugTEMP", ">>>>> sono dummy e return sempre 0 perchè ho attivato il bt inesistente");
        return 0;
    }

    public void enableDiscoverability(int secDiscoverability){
//        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
//        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, secDiscoverability);
//        startActivity(discoverableIntent);

        Log.d("debugTEMP", ">>>>> SEI VISIBILE CORRETTAMENTE (non è vero, sono dummy)");
//        return 0; //sei visibile correttamente

        /*
        Log.d("debugTEMP", ">>>>> SEI VISIBILE CORRETTAMENTE");
        return -1; //NON sei visibile
        */


    }
}