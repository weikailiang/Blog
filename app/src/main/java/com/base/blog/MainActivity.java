package com.base.blog;

import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.base.blog.c.MainContract;
import com.base.blog.databinding.ActivityMainBinding;
import com.base.blog.m.MainModel;
import com.base.blog.p.MainPrestener;
import com.base.chat.fag.WeChatMomentFag;
import com.base.common.act.BaseActivity;
import com.base.common.utils.ToastUtil;
import com.base.home.fag.HomeFag;
import com.base.mine.fag.MineFag;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;
import me.yokeyword.fragmentation.SupportFragment;

@Route(path = "/blog/main")
public class MainActivity extends BaseActivity<MainPrestener,MainModel> implements MainContract.View{
    protected boolean statusBarCompat = true;
    private static long DOUBLE_CLICK_TIME = 0L;
    private ActionBarDrawerToggle mDrawerToggle;
    private ActivityMainBinding mBinding;
    private List<SupportFragment> mFragments;
    private SupportFragment mCurrentFragment;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
        mPresenter.onStart();
    }

    @Override
    protected void initEventAndData() {
        mBinding = (ActivityMainBinding) mRootBinding;
        mBinding.userName.setText(mPresenter.mUser.getPhone());
        initTitleBar();
        setBarTitle(mContext.getString(R.string.home_tab));
        if (statusBarCompat) {
            StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary));
            transparent19and20();
        }
        initFragment();
        initTab();


        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mBinding.drowLayout, mToolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mBinding.drowLayout.addDrawerListener(mDrawerToggle);
        mPresenter.initLeftMenu();

    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFag());
        mFragments.add(new WeChatMomentFag());
        mFragments.add(new MineFag());
        mCurrentFragment = mFragments.get(0);
        //默认选中第一个
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.recycler, mFragments.get(0), mFragments.get(0).getClass().getName());
        transaction.commitAllowingStateLoss();
    }
    private void initTab(){
        final NavigationController navigationController = mBinding.pagerBottomTab.material()
                .addItem(R.mipmap.ic_home_tab, getString(R.string.home_tab))
                .addItem(R.mipmap.ic_wechat_tab, getString(R.string.wechat_tab))
                .addItem(R.mipmap.ic_mine_tab, getString(R.string.mine_tab))
                .setDefaultColor(ContextCompat.getColor(mContext, R.color.day_three_text_color))
                .build();
        //底部按钮的点击事件监听
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                if (manager.findFragmentByTag(mFragments.get(index).getClass().getName()) != null) {
                    transaction.show(mFragments.get(index));
                } else {
                    transaction.add(R.id.recycler, mFragments.get(index), mFragments.get(index).getClass().getName());
                }
                transaction.hide(mCurrentFragment);
                mCurrentFragment = mFragments.get(index);
                transaction.commitAllowingStateLoss();
            }

            @Override
            public void onRepeat(int index) {

            }
        });



    }




    //返回键监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_MENU && mBinding.drowLayout != null) {
            if (mBinding.drowLayout.isDrawerOpen(Gravity.LEFT)) {
                mBinding.drowLayout.closeDrawer(Gravity.LEFT);
            } else {
                mBinding.drowLayout.openDrawer(Gravity.LEFT);
            }
            return true;
        }  else if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (mBinding.drowLayout.isDrawerOpen(Gravity.LEFT)) {
                mBinding.drowLayout.closeDrawer(Gravity.LEFT);
            } else {
                if ((System.currentTimeMillis() - DOUBLE_CLICK_TIME) > 2000) {
                    ToastUtil.show("再按一次退出");
                    DOUBLE_CLICK_TIME = System.currentTimeMillis();
                } else {
                    finish();
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void transparent19and20() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void stopLoading() {

    }



    @Override
    public void settMainToolBar(String title) {
        setBarTitle(title);
    }

    @Override
    public RecyclerView geMaintRecyclerView() {
        return mBinding.lvLeftRecycler;
    }

    @Override
    public DrawerLayout geMaintDrawerLayout() {
        return mBinding.drowLayout;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
