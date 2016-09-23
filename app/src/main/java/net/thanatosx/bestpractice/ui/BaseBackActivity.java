package net.thanatosx.bestpractice.ui;

import android.support.v7.widget.Toolbar;
import android.view.View;

import net.thanatosx.bestpractice.R;

/**
 * Created by thanatos on 16/9/22.
 */

public abstract class BaseBackActivity extends BaseActivity {

    protected Toolbar mToolbar;

    @Override
    protected void initWidgets() {
        super.initWidgets();
        mToolbar = (Toolbar) findViewById(getToolbarView());
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supportFinishAfterTransition();
            }
        });
    }

    protected int getToolbarView(){
        return R.id.toolbar;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }
}
