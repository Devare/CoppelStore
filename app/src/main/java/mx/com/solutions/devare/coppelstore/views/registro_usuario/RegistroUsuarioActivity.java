package mx.com.solutions.devare.coppelstore.views.registro_usuario;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mx.com.solutions.devare.coppelstore.R;
import mx.com.solutions.devare.coppelstore.custom_views.CustomToast;
import mx.com.solutions.devare.coppelstore.presentadores.RegistroUsuarioPresenter_Impl;
import mx.com.solutions.devare.coppelstore.views.base.BaseActivity;
import mx.com.solutions.devare.coppelstore.views.dialog_fragment.DatePickerFragment;
import mx.com.solutions.devare.coppelstore.views.login.LoginActivity;

public class RegistroUsuarioActivity extends BaseActivity implements IRegistroUsuarioView, DatePickerFragment.OnDateChangeListenerInterface {

    @BindView(R.id.til_registro_correo)
    TextInputLayout til_registro_correo;
    @BindView(R.id.til_registro_password)
    TextInputLayout til_registro_password;
    @BindView(R.id.til_confirmar_password)
    TextInputLayout til_confirmar_password;
    @BindView(R.id.til_registro_nombre)
    TextInputLayout til_registro_nombre;
    @BindView(R.id.til_registro_ap_paterno)
    TextInputLayout til_registro_ap_paterno;
    @BindView(R.id.til_registro_ap_materno)
    TextInputLayout til_registro_ap_materno;
    @BindView(R.id.til_fecha_nacimiento)
    TextInputLayout til_fecha_nacimiento;
    @BindView(R.id.til_registro_telefono)
    TextInputLayout til_registro_telefono;
    @BindView(R.id.btn_agregar_fecha)
    Button btn_agregar_fecha;

    @BindView(R.id.rb_hombre)
    AppCompatRadioButton rb_hombre;
    @BindView(R.id.rb_mujer)
    AppCompatRadioButton rb_mujer;

    @BindView(R.id.autocomplete_registro_nombre)
    AppCompatAutoCompleteTextView autocomplete_registro_nombre;
    @BindView(R.id.autocomplete_registro_ap_paterno)
    AppCompatAutoCompleteTextView autocomplete_registro_ap_paterno;
    @BindView(R.id.autocomplete_registro_ap_materno)
    AppCompatAutoCompleteTextView autocomplete_registro_ap_materno;


    public static final String KEY_USUARIO = "USU_REGISTRO_KEY";
    public static final String KEY_PASSWORD = "PAS_REGISTRO_KEY";

    @Inject
    IRegistroUsuarioPresenter<IRegistroUsuarioView, IRegistroUsuarioInteractor> mPresenter;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }


    @Override
    protected int getContentView() {
        return R.layout.activity_registro_usuario;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        mPresenter.onView(RegistroUsuarioActivity.this);
        mPresenter.initAdatadoresAutoCompletePresenter();
    }

    @Override
    protected void resolveDaggerDependency() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initListenerDataControlesUI();
    }

    private void initListenerDataControlesUI() {
        addChangedListenerTil(til_registro_correo);
        addChangedListenerTil(til_registro_password);
        addChangedListenerTil(til_confirmar_password);
        addChangedListenerTil(til_registro_nombre);
        addChangedListenerTil(til_registro_ap_paterno);
        addChangedListenerTil(til_registro_ap_materno);
        addChangedListenerTil(til_fecha_nacimiento);
        addChangedListenerTil(til_registro_telefono);
    }

    private void addChangedListenerTil(TextInputLayout mTextInputLayout) {
        getEditextTil(mTextInputLayout).addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                switch (mTextInputLayout.getId()) {

                    case R.id.til_registro_correo:
                        mPresenter.validaEmailPresenter(charSequence);
                        break;

                    case R.id.til_registro_password:
                        mPresenter.validaPasswordPresenter(charSequence);
                        break;

                    case R.id.til_login_password:
                        mPresenter.validaPasswordPresenter(charSequence);
                        break;

                    case R.id.til_confirmar_password:
                        mPresenter.validaConfirmarPresenter(charSequence);
                        break;

                    case R.id.til_registro_nombre:
                        mPresenter.validaNombrePresenter(charSequence);
                        break;

                    case R.id.til_registro_ap_paterno:
                        mPresenter.validaApaternoPresenter(charSequence);
                        break;

                    case R.id.til_registro_ap_materno:
                        mPresenter.validaAmaternoPresenter(charSequence);
                        break;

                    case R.id.til_fecha_nacimiento:
                        mPresenter.validaFechaNacimientoPresenter(charSequence);
                        break;

                    case R.id.til_registro_telefono:
                        mPresenter.validaTelefonoPresenter(charSequence);
                        break;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    @Override
    public void initAdatadoresAutoComplete() {
        String[] nombres = getResources().getStringArray(R.array.nombre_array);
        String[] apellidos = getResources().getStringArray(R.array.apellidos_array);
        ArrayAdapter<String> adapterNombres = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nombres);
        ArrayAdapter<String> adapterApellidos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, apellidos);

        autocomplete_registro_nombre.setAdapter(adapterNombres);
        autocomplete_registro_ap_paterno.setAdapter(adapterApellidos);
        autocomplete_registro_ap_materno.setAdapter(adapterApellidos);
    }

    @Override
    public void mostrarDatePickerFecha() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void enviarCredencialesALogin(String usuario, String password) {
        Intent intent = new Intent();
        intent.putExtra(KEY_USUARIO, usuario);
        intent.putExtra(KEY_PASSWORD, password);
        setResult(LoginActivity.REQUEST_ONE, intent);
    }

    @OnClick(R.id.btn_agregar_fecha)
    public void seleccionarFecha() {
        mPresenter.seleccionarFechaPresenter();
    }

    @OnClick(R.id.btn_registrar_usuario)
    public void registrarUsuario() {
        if (isNetworkConnected())
            mPresenter.registrarUsuarioPresenter(getCorreo(), getPassword(), getConfirmarPassword(), getNombre(), getApaterno(), getAmaterno(), getFecha(), getTelefono(), getGenero());
        else
            onError(R.string.connection_error);
    }

    @Override
    public void setErrorEmail(String msj) {
        til_registro_correo.setError(msj);
    }

    @Override
    public void setErrorPassword(String msj) {
        til_registro_password.setError(msj);
    }

    @Override
    public void setErrorConfirmarPassword(String msj) {
        til_confirmar_password.setError(msj);
    }

    @Override
    public void setErrorNombre(String msj) {
        til_registro_nombre.setError(msj);
    }

    @Override
    public void setErrorApaterno(String msj) {
        til_registro_ap_paterno.setError(msj);
    }

    @Override
    public void setErrorAmaterno(String msj) {
        til_registro_ap_materno.setError(msj);
    }

    @Override
    public void setErrorFechaNacimiento(String msj) {
        til_fecha_nacimiento.setError(msj);
    }

    @Override
    public void setErrorTelefono(String msj) {
        til_registro_telefono.setError(msj);
    }

    @Override
    public void setErrorSexo(String msj) {
        showMessage(msj);
    }

    @Override
    public void finalizarActividad() {
        RegistroUsuarioActivity.this.finish();
    }

    @Override
    public void mostrarMensajeServidor(String msj) {
        onError(msj);
    }

    /***********
     * Getters
     ***********/
    private String getCorreo() {
        return getToString(getEditextTil(til_registro_correo));
    }

    private String getPassword() {
        return getToString(getEditextTil(til_registro_password));
    }

    private String getConfirmarPassword() {
        return getToString(getEditextTil(til_confirmar_password));
    }

    private String getNombre() {
        return getToString(getEditextTil(til_registro_nombre));
    }

    private String getApaterno() {
        return getToString(getEditextTil(til_registro_ap_paterno));
    }

    private String getAmaterno() {
        return getToString(getEditextTil(til_registro_ap_materno));
    }

    private String getFecha() {
        return getToString(getEditextTil(til_fecha_nacimiento));
    }

    private String getTelefono() {
        return getToString(getEditextTil(til_registro_telefono));
    }


    private String getGenero() {
        String genero = null;
        if (rb_hombre.isChecked()) genero = getString(R.string.genero_hombre);
        else if (rb_mujer.isChecked()) genero = getString(R.string.genero_mujer);
        return genero;
    }

    private EditText getEditextTil(TextInputLayout textInputLayout) {
        return textInputLayout.getEditText();
    }

    private String getToString(EditText mEditText) {
        return mEditText.getText().toString().trim();
    }

    @Override
    public void onDateChangeListener(String fecha) {
        getEditextTil(til_fecha_nacimiento).setText(fecha);
    }
}
