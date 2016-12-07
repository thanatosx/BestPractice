package net.thanatosx.bestpractice.ui;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import net.thanatosx.bestpractice.R;

import butterknife.Bind;

/**
 * Created by thanatosx on 2016/11/28.
 */

public class SoftInputActivity extends BaseActivity {

    @Bind(R.id.view_recycler) ListView mViewRecycler;

    @Override
    protected int getContentView() {
        return R.layout.activity_software_input;
    }

    @Override
    protected void initWidgets() {
        super.initWidgets();
        mViewRecycler.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 20;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return createView(position);
            }
        });
    }

    private View createView(int position){
        EditText view = new EditText(this);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 30);
        view.setLayoutParams(params);
        view.setBackgroundColor(Color.WHITE);
        view.setHint(position + "");
        return view;
    }
}
