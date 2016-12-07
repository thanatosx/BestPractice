package net.thanatosx.bestpractice.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.thanatosx.bestpractice.R;
import net.thanatosx.bestpractice.bean.Article;

import butterknife.Bind;

/**
 * Created by thanatos on 16/9/22.
 */

public class SceneDetailActivity extends BaseBackActivity{

    public static String BUNDLE_KEY_ARTICLE = "BUNDLE_KEY_ARTICLE";

    @Bind(R.id.tv_title) TextView mViewTitle;
    @Bind(R.id.tv_content) TextView mViewContent;
    @Bind(R.id.iv_banner) ImageView mViewBanner;
    @Bind(R.id.layout_appbar) AppBarLayout mLayoutAppBar;

    private Article article;

    public static void show(Context context, Article article){
        if (article == null || article.getId() <= 0) return;
        Intent intent = new Intent(context, SceneDetailActivity.class);
        intent.putExtra(BUNDLE_KEY_ARTICLE, article);
        context.startActivity(intent);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_scene_detail;
    }

    @Override
    protected boolean initBundle(Bundle bundle) {
        article = (Article) bundle.getSerializable(BUNDLE_KEY_ARTICLE);
        return true;
    }

    @Override
    protected void initWidgets() {
        super.initWidgets();
        mViewTitle.setText(article.getTitle());
        mViewContent.setText(article.getContent());
        Glide.with(this)
                .load(article.getBannerLink())
                .into(mViewBanner);
    }

    @Override
    public void onBackPressed() {
        mToolbar.setVisibility(View.GONE);
        super.onBackPressed();
    }
}
