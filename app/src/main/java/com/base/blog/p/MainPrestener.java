package com.base.blog.p;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.base.blog.R;
import com.base.blog.adapter.ItemMenuHomeLeftAdapter;
import com.base.blog.c.MainContract;
import com.base.comm_model.event.MainTitleEvent;
import com.base.common.rxbus.RxBus2;
import com.base.common.utils.ToastUtil;
import com.base.refresh.recy.BasicRecyViewHolder;

import com.base.bolg_model.entity.MenuHomeLeftEntity;

import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by weikailiang on 2020/5/11.
 */

public class MainPrestener extends MainContract.Prestener {
    private ItemMenuHomeLeftAdapter mAdapter;
    @Override
    public void onStart() {
        super.onStart();
        Disposable disposable = RxBus2.getInstance().doSubscribe(MainTitleEvent.class, new Consumer<MainTitleEvent>() {
            @Override
            public void accept(MainTitleEvent titleEvent) throws Exception {
                mView.settMainToolBar(titleEvent.getTitle());
            }
        });
        RxBus2.getInstance().addSubscription(this, disposable);

    }


    @Override
    public void initLeftMenu() {
        if (mAdapter == null){
            mAdapter = new ItemMenuHomeLeftAdapter(R.layout.item_menu_home_left);
            mView.geMaintRecyclerView().setLayoutManager(new LinearLayoutManager(mContext));
            mView.geMaintRecyclerView().setAdapter(mAdapter);
        }
        mAdapter.refreshDatas(mModel.getMenuHomeLeftDatas(mContext));
        mAdapter.setItemClickListener(new BasicRecyViewHolder.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int adapterPosition) {
                MenuHomeLeftEntity entity = mAdapter.getData(mAdapter.getPositon(adapterPosition));
                if (!TextUtils.isEmpty(entity.getmClassName())){
                    ARouter.getInstance().build(entity.getmClassName()).navigation(mContext, new NavCallback() {
                        @Override
                        public void onArrival(Postcard postcard) {
                            if (mView.geMaintDrawerLayout().isDrawerOpen(Gravity.LEFT)) {
                                mView.geMaintDrawerLayout().closeDrawer(Gravity.LEFT);
                            } else {
                                mView.geMaintDrawerLayout().openDrawer(Gravity.LEFT);
                            }
                        }
                    });
                }else {
                    ToastUtil.show(entity.getmName());
                }
            }
        });
    }
}
