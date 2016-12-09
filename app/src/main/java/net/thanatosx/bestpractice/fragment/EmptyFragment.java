package net.thanatosx.bestpractice.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.thanatosx.bestpractice.R;

import butterknife.Bind;

/**
 * Created by thanatosx on 16/11/3.
 */

public class EmptyFragment extends BaseFragment {

    public static final String BUNDLE_KEY = "BUNDLE_KEY";

    @Bind(R.id.tv_number) TextView mViewNumber;
    @Bind(R.id.iv_icon) ImageView mViewIcon;

    private String number;

    public static Fragment instantiate(String number){
        Fragment fragment = new EmptyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY, number);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected boolean initBundle(Bundle bundle) {
        if (bundle == null) return true;
        number = bundle.getString(BUNDLE_KEY, "0");
        return super.initBundle(bundle);
    }

    @Override
    protected void initWidgets(View view) {
        super.initWidgets(view);
        Glide.with(this).load(R.mipmap.ic_launcher).into(mViewIcon);
        mViewNumber.setText(String.valueOf(number));
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_empty;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("oschina", "Fragment On Create");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("oschina", "Fragment On Create View");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
        Log.i("oschina", "Fragment On SaveInstance State");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("oschina", "Fragment On Destroy View");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("oschina", "Fragment On Destroy");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("oschina", "Fragment On Attach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("oschina", "Fragment On Detach");
    }
}
