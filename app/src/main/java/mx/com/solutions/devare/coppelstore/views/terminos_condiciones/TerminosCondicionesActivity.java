package mx.com.solutions.devare.coppelstore.views.terminos_condiciones;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mx.com.solutions.devare.coppelstore.R;
import mx.com.solutions.devare.coppelstore.presentadores.TYCPresenter_Impl;
import mx.com.solutions.devare.coppelstore.views.base.BaseActivity;
import mx.com.solutions.devare.coppelstore.views.login.LoginActivity;

public class TerminosCondicionesActivity extends BaseActivity implements ITYCView{

    //Views
    @BindView(R.id.nested_scroll) NestedScrollView nested_scroll;
    @BindView(R.id.ll_tyc_botones) LinearLayout ll_tyc_botones;

    @Inject
    ITYCPresenter<ITYCView, ITYCInteractor> mPresenter;


    @Override
    protected int getContentView() {
        return R.layout.activity_terminos_condiciones;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        mPresenter.onView(TerminosCondicionesActivity.this);
        mPresenter.escucharEventoScrollPresenter();
    }

    @Override
    protected void resolveDaggerDependency() {
        getActivityComponent().inject(this);
    }

    @OnClick(R.id.btn_terminos_aceptar)
    public void aceptarTYC(){
        mPresenter.guardarPrefsTYCPresenter();
    }

    @OnClick(R.id.btn_terminos_rechazar)
    public void rechazarTYC(){
        mPresenter.finalizarActividadPresenter();
    }

    @Override
    public void changeListenerScroll() {
        nested_scroll.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

            //Pregunta si llego hasta el fondo del terminos y condiciones
            if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                mPresenter .mostrarBotonesPresenter();
            }
        });
    }

    @Override
    public void habilitarBotones(int isVisible) {
        ll_tyc_botones.setVisibility(isVisible);
    }

    @Override
    public void initLogin() {
        startActivity(new Intent(this,LoginActivity.class));
    }

    @Override
    public void finalizarActividad() {
        TerminosCondicionesActivity.this.finish();
    }
}
