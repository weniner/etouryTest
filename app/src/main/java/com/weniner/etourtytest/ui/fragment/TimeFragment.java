package com.weniner.etourtytest.ui.fragment;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weniner.etourtytest.R;
import com.weniner.etourtytest.base.BaseFragment;
import com.weniner.etourtytest.ui.activity.MainActivity;

import org.greenrobot.eventbus.EventBus;


public class TimeFragment extends BaseFragment {


    private TextView tv_show;
    private Handler mHandler = new Handler();
    private int NUM = 5;
    private FragmentManager frg_mng = getFragmentManager();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        mHandler.postDelayed(mRunnable, 0);
    }

    private void initView(View view) {
        tv_show = view.findViewById(R.id.tv_time_show);
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            this.update();
            mHandler.postDelayed(this, 1000);

            if (NUM == 0) {
                mHandler.removeCallbacks(mRunnable); //停止刷新
                if (getActivity() != null) {
                    EventBus.getDefault().post(new com.weniner.etourtytest.api.EventBus("关闭"));
                    getActivity().onBackPressed();
                }
            }
            NUM--;
        }

        private void update() {
            tv_show.setText(NUM + "");
            startPropertyAnim();
        }
    };

    private void startPropertyAnim() {
        ObjectAnimator animAlpha = ObjectAnimator.ofFloat(tv_show, "alpha", 1f, 0.4f);
        ObjectAnimator animScaleY = ObjectAnimator.ofFloat(tv_show, "scaleY", 1f, 5f);
        ObjectAnimator animScaleX = ObjectAnimator.ofFloat(tv_show, "scaleX", 1f, 5f);
        animAlpha.setDuration(1000);// 动画持续时间
        animScaleY.setDuration(1000);
        animScaleX.setDuration(1000);
        animAlpha.start();
        animScaleY.start();
        animScaleX.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
