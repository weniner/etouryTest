package com.weniner.etourtytest.ui.activity;

import android.print.PrinterId;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.weniner.etourtytest.R;
import com.weniner.etourtytest.ui.fragment.TimeFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_start;
    private static TimeFragment timeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        initView();
        initClick();
    }

    private void initClick() {
        btn_start.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        btn_start = findViewById(R.id.btn_main_start);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_start:
                openFm();
                break;
            default:
                break;
        }
    }

    private void openFm() {
        timeFragment = new TimeFragment();
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.rl_main, timeFragment);
        ft.addToBackStack(null);
        ft.commit();
        btn_start.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(com.weniner.etourtytest.api.EventBus eventBus) {
        btn_start.setText(eventBus.getClose());
        btn_start.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
