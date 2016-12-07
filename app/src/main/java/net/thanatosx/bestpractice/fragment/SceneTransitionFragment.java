package net.thanatosx.bestpractice.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.thanatosx.bestpractice.R;
import net.thanatosx.bestpractice.bean.Article;
import net.thanatosx.bestpractice.ui.SceneDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static net.thanatosx.bestpractice.ui.SceneDetailActivity.BUNDLE_KEY_ARTICLE;

/**
 * practise {@link android.support.v4.app.ActivityOptionsCompat} makeSceneTransitionAnimation()
 * Created by thanatos on 16/9/22.
 */

public class SceneTransitionFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.layout_swipe) SwipeRefreshLayout mLayoutSwipe;
    @Bind(R.id.view_recycler) RecyclerView mRecycler;

    private List<Article> articles;

    @Override
    protected int getContentView() {
        return R.layout.fragment_scene_transition;
    }

    @Override
    protected void initWidgets(View view) {
        mLayoutSwipe.setOnRefreshListener(this);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        articles = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            Article a = new Article();
            a.setId(i);
            a.setTitle("全世界最好听的纯音乐（经典不朽）");
            a.setContent("世上的音乐，大部分人听到的都是带有歌词的，其实，没有歌词的音乐对情感的表达更为淋漓尽致，人们听惯了热歌榜，或许就认为好听的音乐也就是如此了。其实，音乐的世界很广，最震撼人心的音乐，可能在你从未触及过的深处。");
            a.setBannerLink("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1474538161&di=7ad7d49f435d04df2450ab502e3fa831&src=http://scimg.jb51.net/allimg/160823/103-160R3115233V9.jpg");
            articles.add(a);
        }

        mRecycler.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ArticleViewHolder(LayoutInflater.from(
                        getContext()).inflate(R.layout.list_item_universal, parent, false));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder h, int position) {
                final Article item = articles.get(position);
                final ArticleViewHolder holder = (ArticleViewHolder) h;
                holder.mViewTitle.setText(item.getTitle());
                holder.mViewContent.setText(item.getContent());
                if (TextUtils.isEmpty(item.getBannerLink())){
                    holder.mViewBanner.setVisibility(View.GONE);
                }else {
                    holder.mViewBanner.setVisibility(View.VISIBLE);
                    Glide.with(getContext())
                            .load(item.getBannerLink())
                            .into(holder.mViewBanner);
                }
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), SceneDetailActivity.class);
                        intent.putExtra(BUNDLE_KEY_ARTICLE, item);

                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                                new Pair<View, String>(holder.mViewTitle, ViewCompat.getTransitionName(holder.mViewTitle)),
                                new Pair<View, String>(holder.mViewBanner, ViewCompat.getTransitionName(holder.mViewBanner)),
                                new Pair<View, String>(holder.mViewContent, ViewCompat.getTransitionName(holder.mViewContent)));
                        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
                    }
                });
            }

            @Override
            public int getItemCount() {
                return articles.size();
            }
        });
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mLayoutSwipe.setRefreshing(false);
            }
        }, 3000);
    }

    protected static class ArticleViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.tv_title) TextView mViewTitle;
        @Bind(R.id.tv_content) TextView mViewContent;
        @Bind(R.id.iv_banner) ImageView mViewBanner;

        public ArticleViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
