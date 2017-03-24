package com.wjf.holographichua.module.main.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.wjf.holographichua.R;
import com.wjf.holographichua.base.BaseActivity;
import com.wjf.holographichua.baseapp.AppConstant;
import com.wjf.holographichua.bean.TabEntity;
import com.wjf.holographichua.module.main.fragment.HyhCategoryFragment;
import com.wjf.holographichua.module.main.fragment.HyhFoundFragment;
import com.wjf.holographichua.module.main.fragment.HyhMainFragment;
import com.wjf.holographichua.module.main.fragment.HyhPersonCenterFragment;
import com.wjf.holographichua.module.main.fragment.HyhShoppingcartFragment;
import com.wjf.holographichua.utils.LogUtils;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_fl_body)
    FrameLayout mMainFl;
    @BindView(R.id.main_tab_layout)
    CommonTabLayout mMainTabLayout;
    private static int tabMeasuredHeight;

    private String[] mTitles = {"首页", "分类","发现","购物车","我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.main_home_normal,R.mipmap.main_home_normal,R.mipmap.main_home_normal,R.mipmap.main_home_normal,R.mipmap.main_home_normal};
    private int[] mIconSelectIds = {
            R.mipmap.main_home_select,R.mipmap.main_home_select, R.mipmap.main_home_select,R.mipmap.main_home_select,R.mipmap.main_home_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private HyhMainFragment hyhMainFragment;
    private HyhCategoryFragment hyhCategoryFragment;
    private HyhFoundFragment hyhFoundFragment;
    private HyhShoppingcartFragment hyhShoppingcartFragment;
    private HyhPersonCenterFragment hyhPersonCenterFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initFragment(savedInstanceState);
        mMainTabLayout.measure(0,0);
        tabMeasuredHeight = mMainTabLayout.getMeasuredHeight();


    }

    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if(savedInstanceState != null){
            hyhMainFragment = (HyhMainFragment) getSupportFragmentManager().findFragmentByTag("hyhMainFragment");
            hyhCategoryFragment = (HyhCategoryFragment) getSupportFragmentManager().findFragmentByTag("hyhCategoryFragment");
            hyhFoundFragment = (HyhFoundFragment) getSupportFragmentManager().findFragmentByTag("hyhFoundFragment");
            hyhShoppingcartFragment = (HyhShoppingcartFragment) getSupportFragmentManager().findFragmentByTag("hyhShoppingcartFragment");
            hyhPersonCenterFragment = (HyhPersonCenterFragment) getSupportFragmentManager().findFragmentByTag("hyhPersonCenterFragment");
            currentTabPosition = savedInstanceState.getInt(AppConstant.HOME_CURRENT_TAB_POSITION);
        }else {
            hyhMainFragment = new HyhMainFragment();
            hyhCategoryFragment = new HyhCategoryFragment();
            hyhFoundFragment = new HyhFoundFragment();
            hyhShoppingcartFragment = new HyhShoppingcartFragment();
            hyhPersonCenterFragment = new HyhPersonCenterFragment();

            transaction.add(R.id.main_fl_body, hyhMainFragment, "hyhMainFragment");
            transaction.add(R.id.main_fl_body, hyhCategoryFragment, "hyhCategoryFragment");
            transaction.add(R.id.main_fl_body, hyhFoundFragment, "hyhFoundFragment");
            transaction.add(R.id.main_fl_body, hyhShoppingcartFragment, "hyhShoppingcartFragment");
            transaction.add(R.id.main_fl_body, hyhPersonCenterFragment, "hyhPersonCenterFragment");
        }
        transaction.commit();
        //跳转当前fragment
        switchTo(currentTabPosition);
        //跳转当前tab
        mMainTabLayout.setCurrentTab(currentTabPosition);
        
        
    }

    @Override
    public int getLayoutId() {
        return R.layout.act_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
            initTab();
    }

    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        mMainTabLayout.setTabData(mTabEntities);
        mMainTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchTo(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });


    }

    private void switchTo(int position) {
        LogUtils.logd("主页菜单position" + position);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (position){
            case 0:
                transaction.hide(hyhCategoryFragment);
                transaction.hide(hyhFoundFragment);
                transaction.hide(hyhShoppingcartFragment);
                transaction.hide(hyhPersonCenterFragment);
                transaction.show(hyhMainFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 1:
                transaction.show(hyhCategoryFragment);
                transaction.hide(hyhFoundFragment);
                transaction.hide(hyhShoppingcartFragment);
                transaction.hide(hyhPersonCenterFragment);
                transaction.hide(hyhMainFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 2:
                transaction.hide(hyhCategoryFragment);
                transaction.show(hyhFoundFragment);
                transaction.hide(hyhShoppingcartFragment);
                transaction.hide(hyhPersonCenterFragment);
                transaction.hide(hyhMainFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 3:
                transaction.hide(hyhCategoryFragment);
                transaction.hide(hyhFoundFragment);
                transaction.show(hyhShoppingcartFragment);
                transaction.hide(hyhPersonCenterFragment);
                transaction.hide(hyhMainFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 4:

                transaction.hide(hyhCategoryFragment);
                transaction.hide(hyhFoundFragment);
                transaction.hide(hyhShoppingcartFragment);
                transaction.show(hyhPersonCenterFragment);
                transaction.hide(hyhMainFragment);
                transaction.commitAllowingStateLoss();
                break;

        }



    }
}
