package net.thanatosx.bestpractice.fragment;

import android.content.Intent;

import net.thanatosx.bestpractice.R;
import net.thanatosx.bestpractice.ui.RippleActivity;

import butterknife.OnClick;

/**
 * Created by thanatosx on 2016/11/16.
 */

public class RippleFragment extends BaseFragment{

    @Override
    protected int getContentView() {
        return R.layout.fragment_toast;
    }

    @OnClick(R.id.btn_toast) void onClick(){
        Intent intent = new Intent(getContext(), RippleActivity.class);
        startActivity(intent);
    }
}
