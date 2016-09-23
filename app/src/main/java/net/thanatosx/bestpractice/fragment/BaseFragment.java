package net.thanatosx.bestpractice.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Base Fragment
 * Created by thanatos on 16/9/22.
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBundle(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(getContentView(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initWidgets();
        initData();
    }

    protected abstract int getContentView();

    /**
     * Bundle数据
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
