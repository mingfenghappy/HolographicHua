package com.wjf.holographichua.module.main.activity;

import android.os.Handler;

import com.wjf.holographichua.R;
import com.wjf.holographichua.base.BaseActivity;


/**
 * Created by wjf on 2017/3/24.
 */

public class SplashActivity extends BaseActivity {

//    @BindView(R.id.hyh_iv_splash)
//    ImageView mHyhIvSplash;

    @Override
    public int getLayoutId() {
        return R.layout.act_splash;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        //设置状态栏透明
        SetTranslanteBar();
//        ImageLoaderUtils.display(SplashActivity.this,mHyhIvSplash,R.mipmap.splash);
//        mHyhIvSplash.setImageBitmap(BitmapFactory.decodeResource(BaseApplication.getAppResources(),R.mipmap.splash));

        toMain();

    }

    private void toMain() {

        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(MainActivity.class);
                finish();
            }
        },1500);




    }


}
