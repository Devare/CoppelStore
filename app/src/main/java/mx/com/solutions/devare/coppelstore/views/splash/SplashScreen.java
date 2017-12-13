package mx.com.solutions.devare.coppelstore.views.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import mx.com.solutions.devare.coppelstore.R;
import mx.com.solutions.devare.coppelstore.presentadores.SplashScreenPresenter_Impl;
import mx.com.solutions.devare.coppelstore.views.base.BaseActivity;
import mx.com.solutions.devare.coppelstore.views.terminos_condiciones.TerminosCondicionesActivity;
import mx.com.solutions.devare.coppelstore.views.login.LoginActivity;
import mx.com.solutions.devare.coppelstore.views.main.MainActivity;

public class SplashScreen extends BaseActivity implements ISplashScreenView {

    @BindView(R.id.iv_splash_screen)
    ImageView iv_splash_screen;
    @BindView(R.id.tv_nombre_splash_screen)
    TextView tv_nombre_splash_screen;
    @BindView(R.id.tv_slogan_splash_screen)
    TextView tv_slogan_splash_screen;

    Animation mAnimation;

    @Inject
    ISplashScreenPresenter<ISplashScreenView,ISplashScreenInteractor> mPresenter;

    @Override
    protected int getContentView() {
        return R.layout.activity_splash_screen;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        mPresenter.onView(this);
    }

    @Override
    protected void resolveDaggerDependency() {
        getActivityComponent().inject(this);
    }

    @Override
    public void initIntroAnimacion() {
        new Handler().post(() -> {
            initAnimTraslateRight(tv_nombre_splash_screen);
            initAnimTraslateLeft(tv_slogan_splash_screen);
            initAnimTraslateTop(iv_splash_screen);
        });
    }

    private void initAnimTraslateRight(View mView) {
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.traslate_right);
        mView.startAnimation(mAnimation);
    }

    private void initAnimTraslateLeft(View mView) {
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.traslate_left);
        mView.startAnimation(mAnimation);
    }

    private void initAnimTraslateTop(View mView) {
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.traslate_top);
        mView.startAnimation(mAnimation);
    }

    @Override
    public void initTerminosYCondiciones() {
        startActivity(new Intent(SplashScreen.this, TerminosCondicionesActivity.class));
    }

    @Override
    public void initLogin() {
        startActivity(new Intent(SplashScreen.this, LoginActivity.class));
    }

    @Override
    public void initMain() {
        startActivity(new Intent(SplashScreen.this, MainActivity.class));
    }

    @Override
    public void finalizarActividad() {
        SplashScreen.this.finish();
    }
}
