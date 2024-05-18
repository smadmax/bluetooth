package com.example.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
public class MainActivity extends AppCompatActivity {
    private BluetoothAdapter mBluetoothAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize BluetoothAdapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        // Check if Bluetooth is supported on this device
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            Toast.makeText(this, "Bluetooth is not supported on this device", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity
            return;
        }
        Button b1 = findViewById(R.id.button);
        TextView t1 = findViewById(R.id.text1);
        b1.setOnClickListener(view -> {
            if (mBluetoothAdapter.isEnabled()) {
                // Bluetooth is enabled, disable it
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mBluetoothAdapter.disable();
                t1.setText("Bluetooth is OFF");
            } else {
                // Bluetooth is disabled, enable it
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
                t1.setText("Bluetooth is ON");
            }
        });
    }
}
