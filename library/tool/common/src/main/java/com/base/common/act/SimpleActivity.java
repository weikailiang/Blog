package com.base.common.act;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.common.R;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by weikailiang on 2020/5/9.
 */

public abstract class SimpleActivity extends SupportActivity{
    private ImageView mImg;
    protected Toolbar mToolbar;
    private TextView mTitle;

    protected abstract void initEventAndData();

    protected void initTitleBar(){
        mTitle = findViewById(R.id.title);
        mToolbar = findViewById(R.id.bar);
        mImg = findViewById(R.id.img_right);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }
    protected void setBarTitle(String tittle)
    {
        mTitle.setText(tittle);

    }
    protected void setRigImg(int mResource, View.OnClickListener v){
        if (mImg != null){
            mImg.setBackgroundResource(mResource);
            mImg.setVisibility(View.VISIBLE);
            mImg.setOnClickListener(v);
        }
    }

    protected void setBackNavigation()
    {
        mToolbar.setNavigationIcon(R.mipmap.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
