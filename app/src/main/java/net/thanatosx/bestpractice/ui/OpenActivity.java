package net.thanatosx.bestpractice.ui;

import android.view.Gravity;
import android.widget.Toast;

import net.thanatosx.bestpractice.R;
import net.thanatosx.bestpractice.SimplexToast;

import butterknife.OnClick;

/**
 * Created by thanatosx on 2016/11/15.
 */

public class OpenActivity extends BaseActivity {

    @Override
    protected int getContentView() {
        return R.layout.fragment_toast;
    }

    @OnClick(R.id.btn_toast)
    void onClickButton() {
//        SimplexToast.show(this, "OpenActivity", Gravity.CENTER_HORIZONTAL);
        Toast.makeText(this, "Open", Toast.LENGTH_SHORT).show();
    }

}
