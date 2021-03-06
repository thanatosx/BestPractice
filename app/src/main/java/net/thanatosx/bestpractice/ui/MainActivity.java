package net.thanatosx.bestpractice.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import net.thanatosx.bestpractice.R;
import net.thanatosx.bestpractice.fragment.DynamicTabFragment;
import net.thanatosx.bestpractice.fragment.EmptyFragment;
import net.thanatosx.bestpractice.fragment.HuaWeiCropFragment;
import net.thanatosx.bestpractice.fragment.RippleFragment;
import net.thanatosx.bestpractice.fragment.SceneTransitionFragment;
import net.thanatosx.bestpractice.fragment.ToastFragment;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.layout_drawer) DrawerLayout mDrawerLayout;
    @Bind(R.id.view_nav) NavigationView mDrawerNavView;
    @Bind(R.id.layout_coordinator) CoordinatorLayout mLayoutCoordinator;

    private MenuItem mCurItem;
    private List<OnTurnBackListener> mTurnBackListeners = new ArrayList<>();

    public interface OnTurnBackListener{
        boolean onTurnBack();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidgets() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.transparent));
        }
        mToolbar.setTitle("Best Practice");
        setSupportActionBar(mToolbar);

        mDrawerNavView.setItemIconTintList(null);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.open_drawer, R.string.close_drawer);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mDrawerNavView.setNavigationItemSelectedListener(this);
        setupFragment(EmptyFragment.class);
        mDrawerNavView.setCheckedItem(R.id.menu_make_scene);

    }

    /*
     * FragmentTransaction.add(int, Fragment) 这样操作的Fragment会在commit后立刻走生命周期
     * 显示出来，而FragmentTransaction.show(Fragment)和FragmentTransaction.hide(Fragment)这
     * 两个方法只是隐藏和显示Fragment.mView而已
     */

    private void setupFragment(Class<? extends Fragment> fc) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(fc.getName());
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (fragment == null) {
            fragment = Fragment.instantiate(this, fc.getName());
            ft.add(R.id.frame_container, fragment, fc.getName());
        }
//        ft.hide(fragment);
//        ft.show(fragment);
//        ft.replace(R.id.frame_container, fragment);
        ft.commit();
    }

    @Override
    protected void initData() {
        super.initData();
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(EmptyFragment.class.getName());
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.detach(fragment);
                ft.commit();
            }
        }, 3000);*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                for (Fragment fragment : getSupportFragmentManager().getFragments()){
                    ft.remove(fragment);
                }
                ft.commit();
            }
        }, 6000);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (mCurItem != null && mCurItem.getItemId() == item.getItemId()) return true;
        int iid = item.getItemId();
        switch (iid) {
            case R.id.menu_make_scene:
                setupFragment(SceneTransitionFragment.class);
                break;
            case R.id.menu_dynamic_tab:
                setupFragment(DynamicTabFragment.class);
        }
        mDrawerNavView.setCheckedItem(iid);
        mCurItem = item;
        mDrawerLayout.closeDrawer(mDrawerNavView);
        return false;
    }

    public void addOnTurnBackListener(OnTurnBackListener l){
        this.mTurnBackListeners.add(l);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        for (OnTurnBackListener l : mTurnBackListeners){
            l.onTurnBack();
        }
    }
}
