package net.thanatosx.bestpractice.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import net.thanatosx.bestpractice.R;
import net.thanatosx.bestpractice.fragment.SceneTransitionFragment;

import butterknife.Bind;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.layout_drawer) DrawerLayout mDrawerLayout;
    @Bind(R.id.view_nav) NavigationView mDrawerNavView;
    @Bind(R.id.layout_coordinator) CoordinatorLayout mLayoutCoordinator;

    private MenuItem mCurItem;

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
        setupFragment(SceneTransitionFragment.class);
        mDrawerNavView.setCheckedItem(R.id.menu_make_scene);
    }

    private void setupFragment(Class<? extends Fragment> fc) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(fc.getName());
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (fragment == null) {
            fragment = Fragment.instantiate(this, fc.getName());
            ft.add(fragment, fc.getName());
        }
        ft.replace(R.id.frame_container, fragment);
        ft.commit();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (mCurItem != null && mCurItem.getItemId() == item.getItemId()) return true;
        int iid = item.getItemId();
        switch (iid) {
            case R.id.menu_make_scene:
                setupFragment(SceneTransitionFragment.class);
                break;
        }
        mDrawerNavView.setCheckedItem(iid);
        mCurItem = item;
        mDrawerLayout.closeDrawer(mDrawerNavView);
        return false;
    }

}
