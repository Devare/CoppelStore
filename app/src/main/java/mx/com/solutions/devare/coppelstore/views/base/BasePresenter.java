package mx.com.solutions.devare.coppelstore.views.base;

import javax.inject.Inject;

public class BasePresenter<V extends IBaseView,I extends IBaseInteractor>
        implements IBasePresenter<V,I> {

    private V mView;
    private I mInteractor;

    @Inject
    public BasePresenter(I mInteractor) {
        this.mInteractor = mInteractor;
    }

    @Override
    public void onView(V mView) {
        this.mView = mView;
    }

    @Override
    public V getView() {
        return mView;
    }

    @Override
    public I getInteractor() {
        return mInteractor;
    }
}
