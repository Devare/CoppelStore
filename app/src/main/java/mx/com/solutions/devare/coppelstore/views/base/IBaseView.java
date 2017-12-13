package mx.com.solutions.devare.coppelstore.views.base;

import android.support.annotation.StringRes;

public interface IBaseView {

    void showProgressLoading();

    void hideProgressLoading();

    void onError(@StringRes int resId);

    void onError(String msj);

    void onSucess(@StringRes int resId);

    void onSucess(String msj);

    void showMessage(@StringRes int resId);

    void showMessage(String msj);

    boolean isNetworkConnected();

    void hideKeyboard();
}
