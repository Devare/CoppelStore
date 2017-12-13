package mx.com.solutions.devare.coppelstore.views.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import mx.com.solutions.devare.coppelstore.R;
import mx.com.solutions.devare.coppelstore.custom_views.CustomToast;
import mx.com.solutions.devare.coppelstore.di.components.ActivityComponent;
import mx.com.solutions.devare.coppelstore.di.components.DaggerActivityComponent;
import mx.com.solutions.devare.coppelstore.di.modules.ActivityModule;
import mx.com.solutions.devare.coppelstore.utils.CommonUtils;
import mx.com.solutions.devare.coppelstore.utils.NetworkUtils;
import mx.com.solutions.devare.coppelstore.views.application.CoppelApplication;


public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    private ActivityComponent activityComponent;
    private ProgressDialog mProgressDialog;

    @Inject
    CustomToast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());

        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((CoppelApplication)getApplication()).getComponente())
                .build();

        ButterKnife.bind(this);
        onViewReady(savedInstanceState, getIntent());
    }

    protected abstract int getContentView();

    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        resolveDaggerDependency();
    }

    protected void resolveDaggerDependency() {
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    @Override
    public void showProgressLoading() {
        hideProgressLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideProgressLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onError(int resId) {
        onError(getString(resId));
    }

    @Override
    public void onError(String msj) {
        if (msj != null) {
            showSnackBar(msj);
        } else {
            showSnackBar(getString(R.string.some_error));
        }
    }

    @Override
    public void onSucess(int resId) {
        onSucess(getString(resId));
    }

    @Override
    public void onSucess(String msj) {
        if (msj != null) {
            showSnackBar(msj);
        } else {
            showSnackBar(getString(R.string.some_error));
        }
    }

    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) sbView.getLayoutParams();
        params.gravity = Gravity.BOTTOM;

        sbView.setLayoutParams(params);
        sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));

        TextView mainTextView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        mainTextView.setTextColor(ContextCompat.getColor(this, R.color.colorBlanco));

        //TextView actionTextView = sbView.findViewById(android.support.design.R.id.snackbar_action);
        // actionTextView.setTextColor(ContextCompat.getColor(this, R.color.white));

        snackbar.show();

    }

    @Override
    public void showMessage(int resId) {
        showMessage(getString(resId));
    }

    @Override
    public void showMessage(String msj) {
        if (msj != null) {
            mToast.toastPersonalizado(msj, R.drawable.ic_logo, Gravity.TOP | Gravity.START, 0, 0);
        } else {
            mToast.toastPersonalizado(getString(R.string.some_error), R.drawable.ic_logo, Gravity.TOP | Gravity.START, 0, 0);
        }
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    @Override
    public void hideKeyboard() {

    }
}
