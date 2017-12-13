package mx.com.solutions.devare.coppelstore.views.main;


import mx.com.solutions.devare.coppelstore.data.db.modelo.Producto;
import mx.com.solutions.devare.coppelstore.views.base.IBaseView;

public interface IMainView  extends IBaseView{
    void initAdaptadorRV();
    void generarLinearLayoutVertical();
}
