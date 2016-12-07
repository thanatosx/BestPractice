package net.thanatosx.bestpractice.fragment;

import android.content.Intent;
import android.view.Gravity;
import android.widget.Toast;

import net.thanatosx.bestpractice.R;
import net.thanatosx.bestpractice.SimplexToast;
import net.thanatosx.bestpractice.ui.OpenActivity;
import net.thanatosx.bestpractice.ui.SoftInputActivity;

import butterknife.OnClick;

/**
 * Created by thanatosx on 2016/11/15.
 */

public class ToastFragment extends BaseFragment {

    @Override
    protected int getContentView() {
        return R.layout.fragment_toast;
    }

    @OnClick(R.id.btn_toast)
    void onClickButton() {
//        SimplexToast.show(getContext(), "ToastFragment");
        startActivity(new Intent(getContext(), SoftInputActivity.class));
    }

    @OnClick(R.id.btn_open) void onClickOpen(){
        Intent intent = new Intent(getContext(), OpenActivity.class);
        startActivity(intent);
    }

}
