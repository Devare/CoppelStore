package mx.com.solutions.devare.coppelstore.presentadores;

import javax.inject.Inject;

import mx.com.solutions.devare.coppelstore.views.base.BasePresenter;
import mx.com.solutions.devare.coppelstore.views.login.ILoginInteractor;
import mx.com.solutions.devare.coppelstore.views.login.ILoginPresenter;
import mx.com.solutions.devare.coppelstore.views.login.ILoginView;

public class LoginPresenter_Impl<V extends ILoginView, I extends ILoginInteractor> extends BasePresenter<V,I> implements ILoginPresenter<V,I> {

    @Inject
    public LoginPresenter_Impl(I mInteractor) {
        super(mInteractor);
    }

    @Override
    public void onView(V mView) {
        super.onView(mView);
        getInteractor().setPresenter(this);
    }

    @Override
    public void onLoginCoppelPresenter(String usuario, String password) {
        getInteractor().validaCamposIteractor(usuario,password);
    }

    @Override
    public void onLoginFacebookPresenter() {

    }

    @Override
    public void initRegistroPresenter() {
        assert getView() !=null;
        getView().navigateToRegistro();
    }

    @Override
    public void setErrorUsuarioPresenter(String msj) {
        assert getView() != null;
        getView().setErrorUsuario(msj);
    }

    @Override
    public void setErrorPasswordPresenter(String msj) {
        assert getView() != null;
        getView().setErrorPassword(msj);
    }

    @Override
    public void validaUsuarioPresenter(CharSequence charSequence) {
        getInteractor().validaUsuarioIteractor(charSequence);
    }

    @Override
    public void validaPasswordPresenter(CharSequence charSequence) {
        getInteractor().validaPasswordIteractor(charSequence);
    }

    @Override
    public void navigateToHomePresenter() {
        assert getView() != null;
        getView().navigateToHome();
        getView().finishActivity();
    }

    @Override
    public void mostrarErrorServidorPresenter(String msj) {
        assert getView() != null;
        getView().mostrarMsjError(msj);
    }
}
