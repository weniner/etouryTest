package com.weniner.etourtytest.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * @version 1.0
 * @auther Weniner
 * 2019/4/2
 **/
public abstract class BaseFragment extends Fragment {

    private Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    /**
     * 获取当前的Activity
     */
    public final Activity getAppActivity() {
        return mActivity;
    }


    /**
     * 关闭页面
     */
    public void finish() {
        if (mActivity != null) {
            mActivity.finish();
        }
    }
}
