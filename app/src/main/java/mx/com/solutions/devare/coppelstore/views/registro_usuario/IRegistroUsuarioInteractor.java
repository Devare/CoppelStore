package mx.com.solutions.devare.coppelstore.views.registro_usuario;


import mx.com.solutions.devare.coppelstore.views.base.IBaseInteractor;

public interface IRegistroUsuarioInteractor extends IBaseInteractor{
    void setPresenter(IRegistroUsuarioPresenter mPresenter);
    void registrarUsuarioInteractor(String correo, String password, String ConfPassword,String nombre, String apaterno, String amaterno, String fecha_nacimiento, String telefono, String genero);
    void validaEmailInteractor(CharSequence charSequence);
    void validaPasswordInteractor(CharSequence charSequence);
    void validaConfirmarInteractor(CharSequence charSequence);
    void validaNombreInteractor(CharSequence charSequence);
    void validaApaternoInteractor(CharSequence charSequence);
    void validaAmaternoInteractor(CharSequence charSequence);
    void validaFechaNacimientoInteractor(CharSequence charSequence);
    void validaTelefonoInteractor(CharSequence charSequence);
}
