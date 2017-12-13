package mx.com.solutions.devare.coppelstore.views.login;


import mx.com.solutions.devare.coppelstore.views.base.IBaseInteractor;

public interface ILoginInteractor extends IBaseInteractor{
    void setPresenter(ILoginPresenter mPresenter);
    void validaCamposIteractor(String mUsuario, String mPassword);
    void validaUsuarioIteractor(CharSequence mUsuario);
    void validaPasswordIteractor(CharSequence mPassword);
}
