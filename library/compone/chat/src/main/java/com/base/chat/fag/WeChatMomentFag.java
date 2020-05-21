package com.base.chat.fag;

import com.base.chat.R;
import com.base.chat.c.WeChatMomentContract;
import com.base.chat.databinding.FagWechatmomentBinding;
import com.base.chat.m.WeChatMomentModel;
import com.base.chat.p.WeChatMomentPresenter;
import com.base.comm_model.event.MainTitleEvent;
import com.base.common.act.BaseFragment;
import com.base.common.rxbus.RxBus2;
import com.base.wedget.banner.BannerEntity;
import com.base.wedget.banner.OnBannerImgSelectInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * 朋友圈
 * Created by weikailiang on 2020/5/11.
 */

public class WeChatMomentFag extends BaseFragment<WeChatMomentPresenter,WeChatMomentModel> implements WeChatMomentContract.View{
    private FagWechatmomentBinding mBinding;
    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fag_wechatmoment;
    }

    @Override
    protected void init() {
        mBinding = (FagWechatmomentBinding) mRootBinding;
        RxBus2.getInstance().post(new MainTitleEvent(mContext.getString(R.string.wechat_title)));

    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){
            RxBus2.getInstance().post(new MainTitleEvent(mContext.getString(R.string.wechat_title)));
        }
    }


    @Override
    protected void lazyLoad() {

        List<BannerEntity> list = new ArrayList<>();
        BannerEntity entity = null;
        entity = new BannerEntity();
        entity.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1589455087688&di=c7a937e857838819886b427886a70683&imgtype=0&src=http%3A%2F%2Fimg3.imgtn.bdimg.com%2Fit%2Fu%3D1100172608%2C3877538389%26fm%3D214%26gp%3D0.jpg");
        list.add(entity);
        entity = new BannerEntity();
        entity.setUrl("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3404221704,526751635&fm=26&gp=0.jpg");
        list.add(entity);
        entity = new BannerEntity();
        entity.setUrl("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3404221704,526751635&fm=26&gp=0.jpg");
        list.add(entity);
        entity = new BannerEntity();
        entity.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1589455087688&di=c7a937e857838819886b427886a70683&imgtype=0&src=http%3A%2F%2Fimg3.imgtn.bdimg.com%2Fit%2Fu%3D1100172608%2C3877538389%26fm%3D214%26gp%3D0.jpg");
        list.add(entity);
        mBinding.banner.setmData(list);
        mBinding.banner.setOnBannerImgSelectInterface(new OnBannerImgSelectInterface() {
            @Override
            public void selectImage(int index) {

            }

            @Override
            public void onItemClick(BannerEntity entity, int index) {

            }
        });
    }
}
