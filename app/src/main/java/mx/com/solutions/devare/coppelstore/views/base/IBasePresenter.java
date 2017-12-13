package mx.com.solutions.devare.coppelstore.views.base;

public interface IBasePresenter<V extends IBaseView,I extends IBaseInteractor> {

    void onView(V mView);
    V getView();
    I getInteractor();
}
