package mx.com.solutions.devare.coppelstore.views.terminos_condiciones;

import mx.com.solutions.devare.coppelstore.views.base.IBaseView;

public interface ITYCView extends IBaseView{
    void changeListenerScroll();
    void habilitarBotones(int isVisible);
    void initLogin();
    void finalizarActividad();
}
