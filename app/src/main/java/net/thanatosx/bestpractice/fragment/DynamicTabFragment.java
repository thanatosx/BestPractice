package net.thanatosx.bestpractice.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.thanatosx.bestpractice.R;
import net.thanatosx.bestpractice.adapter.FragmentPagerAdapter;
import net.thanatosx.bestpractice.bean.SubTab;
import net.thanatosx.bestpractice.ui.MainActivity;
import net.thanatosx.bestpractice.widgets.TabPickerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by thanatosx on 16/10/26.
 */

public class DynamicTabFragment extends BaseFragment {

    @Bind(R.id.layout_tab)
    TabLayout mLayoutTab;
    @Bind(R.id.view_tab_picker)
    TabPickerView mViewTabPicker;
    @Bind(R.id.iv_arrow_down)
    ImageView mViewArrowDown;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    List<SubTab> tabs;
    Fragment mCurFragment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity) context).addOnTurnBackListener(new MainActivity.OnTurnBackListener() {
            @Override
            public boolean onTurnBack() {
                return mViewTabPicker.onTurnBack();
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_dynamic_tab;
    }

    @Override
    protected void initWidgets(View view) {
        super.initWidgets(view);

        mViewTabPicker.setTabPickerManager(new TabPickerView.TabPickerDataManager() {
            @Override
            public List<SubTab> setupActiveDataSet() {
                try {
                    StringBuilder buffer = new StringBuilder();
                    byte[] bytes = new byte[1024];
                    int length;

                    File file = getContext().getFileStreamPath("sub_tab_active.json");
                    if (file.exists()) {
                        FileInputStream fis = getContext().openFileInput("sub_tab_active.json");
                        while ((length = fis.read(bytes)) != -1) {
                            buffer.append(new String(bytes, 0, length));
                        }
                    }

                    if (TextUtils.isEmpty(buffer.toString()) || buffer.toString().trim().equals("")) {
                        InputStream is = getResources().getAssets().open("sub_tab_active.json");
                        while ((length = is.read(bytes)) != -1) {
                            buffer.append(new String(bytes, 0, length));
                        }
                    }
                    return new Gson().<ArrayList<SubTab>>fromJson(buffer.toString(),
                            new TypeToken<ArrayList<SubTab>>() {
                            }.getType());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public List<SubTab> setupOriginalDataSet() {
                try {
                    InputStream is = getResources().getAssets().open("sub_tab_original.json");
                    StringBuilder buffer = new StringBuilder();
                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = is.read(bytes)) != -1) {
                        buffer.append(new String(bytes, 0, length));
                    }
                    return new Gson().<ArrayList<SubTab>>fromJson(buffer.toString(),
                            new TypeToken<ArrayList<SubTab>>() {
                            }.getType());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });

        mViewTabPicker.setOnTabPickingListener(new TabPickerView.OnTabPickingListener() {

            private boolean isChangeIndex = false;

            @Override
            @SuppressWarnings("all")
            public void onSelected(final int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onRemove(int position, SubTab tab) {
                isChangeIndex = true;
            }

            @Override
            public void onInsert(SubTab tab) {
                isChangeIndex = true;
            }

            @Override
            public void onMove(int op, int np) {
                isChangeIndex = true;
            }

            @Override
            public void onRestore(List<SubTab> activeTabs) {
                if (!isChangeIndex) return;
                String json = new Gson().toJson(activeTabs);
                try {
                    FileOutputStream fos = getContext().openFileOutput("sub_tab_active.json",
                            Context.MODE_PRIVATE);
                    fos.write(json.getBytes("UTF-8"));
                    fos.flush();
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                isChangeIndex = false;

                tabs.clear();
                tabs.addAll(activeTabs);
                mViewPager.getAdapter().notifyDataSetChanged();

            }
        });

        mViewTabPicker.setOnShowAnimation(new TabPickerView.Action1<ViewPropertyAnimator>() {
            @Override
            public void call(ViewPropertyAnimator animator) {
                mViewArrowDown.setEnabled(false);

                mViewArrowDown.animate().rotation(225).setDuration(380).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mViewArrowDown.setRotation(45);
                        mViewArrowDown.setEnabled(true);
                    }
                });
                /*mViewTabPicker.setVisibility(View.VISIBLE);
                mViewTabPicker.setTranslationY(mViewTabPicker.getHeight() * 0.2f);
                mViewTabPicker.setAlpha(0);
                animator.translationY(0)
                        .alpha(1)
                        .setDuration(380)
                        .setInterpolator(new DecelerateInterpolator())
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                mViewTabPicker.setVisibility(View.VISIBLE);
                                mViewTabPicker.setTranslationY(0);
                                mViewTabPicker.setAlpha(1);
                            }
                        });*/
            }
        });

        mViewTabPicker.setOnHideAnimator(new TabPickerView.Action1<ViewPropertyAnimator>() {
            @Override
            public void call(ViewPropertyAnimator animator) {
                /*animator.translationY(mViewTabPicker.getHeight() * 0.2f)
                        .setDuration(380)
                        .alpha(0)
                        .setInterpolator(new DecelerateInterpolator())
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                mViewTabPicker.setVisibility(View.GONE);
                                mViewArrowDown.setEnabled(true);
                            }
                        });*/
                mViewArrowDown.animate().rotation(-225).setDuration(380).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mViewArrowDown.setRotation(0);
                    }
                });
            }
        });

        tabs = new ArrayList<>();
        tabs.addAll(mViewTabPicker.getTabPickerManager().getActiveDataSet());
        for (SubTab tab : tabs) {
            mLayoutTab.addTab(mLayoutTab.newTab().setText(tab.getName()));
        }

        final FragmentPagerAdapter adapter;
        mViewPager.setAdapter(adapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                SubTab tab = tabs.get(position);
                return EmptyFragment.instantiate(tab.getName());
            }

            @Override
            public int getCount() {
                return tabs.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabs.get(position).getName();
            }

            //this is called when notifyDataSetChanged() is called
            @Override
            public int getItemPosition(Object object) {
                return PagerAdapter.POSITION_NONE;
            }
        });
        mLayoutTab.setupWithViewPager(mViewPager);
        mLayoutTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i("oschina", "onTabSelected");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                Log.i("oschina", "onPageSelected");
                adapter.commitUpdate();
            }
        });
    }

    @OnClick(R.id.iv_arrow_down)
    void onClickArrow() {
        if (mViewArrowDown.getRotation() != 0){
            mViewTabPicker.hide();
        }else {
            mViewTabPicker.show(mLayoutTab.getSelectedTabPosition());
        }
    }


}
