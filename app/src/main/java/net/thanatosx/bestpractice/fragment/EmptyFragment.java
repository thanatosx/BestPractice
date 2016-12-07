package net.thanatosx.bestpractice.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
        number = bundle.getString(BUNDLE_KEY, "0");
        return super.initBundle(bundle);
    }

    @Override
    protected void initWidgets() {
        super.initWidgets();
        Glide.with(this).load(R.mipmap.ic_launcher).into(mViewIcon);
        mViewNumber.setText(String.valueOf(number));
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_empty;
    }

}
