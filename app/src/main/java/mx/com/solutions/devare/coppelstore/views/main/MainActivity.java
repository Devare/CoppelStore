package mx.com.solutions.devare.coppelstore.views.main;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.solutions.devare.coppelstore.R;
import mx.com.solutions.devare.coppelstore.data.db.modelo.Producto;
import mx.com.solutions.devare.coppelstore.presentadores.MainPresenter_Impl;
import mx.com.solutions.devare.coppelstore.views.adaptadores.AdaptadorProductos;
import mx.com.solutions.devare.coppelstore.views.base.BaseActivity;

public class MainActivity extends BaseActivity implements IMainView,AdaptadorProductos.OnSetOnclickListener{

    @BindView(R.id.recyclerView_productos)  RecyclerView mrecyclerView;
    @BindView(R.id.toolbar)Toolbar toolbar;

    @Inject
    IMainPresenter<IMainView,IMainInteractor> mPresenter;

    private boolean mTwoPane;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        mPresenter.onView(MainActivity.this);
        setToolbar();
        getAllPromociones(savedInstanceState);
    }

    @Override
    protected void resolveDaggerDependency() {
        getActivityComponent().inject(this);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
    }

    private void getAllPromociones(Bundle savedInstanceState) {
        mPresenter.retrieveGetAllProductosPresenter(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("KEY_PRODUCTOS", (ArrayList<? extends Parcelable>) mPresenter.getProductos());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void initAdaptadorRV() {
        AdaptadorProductos mAdaptadorProductos = new AdaptadorProductos(mPresenter.getProductos(), this);
        mrecyclerView.setAdapter(mAdaptadorProductos);
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mrecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(AdaptadorProductos.ViewHolderProducto holderProducto, Producto mProducto) {
        Toast.makeText(this, mProducto.getNombre(), Toast.LENGTH_SHORT).show();
    }
}
