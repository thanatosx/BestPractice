package net.thanatosx.bestpractice.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import net.thanatosx.bestpractice.R;

import butterknife.ButterKnife;

/**
 * The Base Activity
 * Created by thanatos on 16/9/21.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initWindow();
        super.onCreate(savedInstanceState);

        if (!initBundle(getIntent().getExtras())) finish();

        setContentView(getContentView());
        ButterKnife.bind(this);
        initWidgets();
        initData();
    }

    /**
     * content view
     * @return layout view id
     */
    protected abstract int getContentView();

    /**
     * 在{@link #onCreate(Bundle)}之前执行的操作
     */
    protected void initWindow(){

    }

    /**
     * 出来Bundle数据
     * @return 如果是true, Bundle数据无误, 反之
     */
    protected boolean initBundle(Bundle bundle){
        return true;
    }

    /**
     * 初始化组件
     */
    protected void initWidgets(){

    }

    /**
     * 初始化数据
     */
    protected void initData(){

    }
}
