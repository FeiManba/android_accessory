package com.mrzang.android_accessory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mrzang.accessorylibrary.AccessoryFileActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * accessory
     */
    private Button mBtnAccessory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtnAccessory = (Button) findViewById(R.id.btn_accessory);
        mBtnAccessory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_accessory:
                startActivity(new Intent(this, AccessoryFileActivity.class));
                break;
        }
    }
}
