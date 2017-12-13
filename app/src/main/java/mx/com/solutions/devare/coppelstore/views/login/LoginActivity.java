package mx.com.solutions.devare.coppelstore.views.login;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.facebook.CallbackManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import mx.com.solutions.devare.coppelstore.R;
import mx.com.solutions.devare.coppelstore.custom_views.CustomToast;
import mx.com.solutions.devare.coppelstore.views.base.BaseActivity;
import mx.com.solutions.devare.coppelstore.views.main.MainActivity;
import mx.com.solutions.devare.coppelstore.views.registro_usuario.RegistroUsuarioActivity;

public class LoginActivity extends BaseActivity implements ILoginView {

    @BindView(R.id.til_login_usuario)
    TextInputLayout til_login_usuario;
    @BindView(R.id.til_login_password)
    TextInputLayout til_login_password;


    private CallbackManager callbackManager;
    public static final int REQUEST_ONE = 1;
    public static final int REQUEST_TWO = 2;

    @Inject
    ILoginPresenter<ILoginView, ILoginInteractor> mPresenter;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        mPresenter.onView(LoginActivity.this);
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
        addChangedListenerTil(til_login_usuario);
        addChangedListenerTil(til_login_password);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case REQUEST_ONE:
                if (data != null) {
                    String usuRegistro = data.getStringExtra(RegistroUsuarioActivity.KEY_USUARIO);
                    String pasRegistro = data.getStringExtra(RegistroUsuarioActivity.KEY_PASSWORD);
                    getEditextTil(til_login_usuario).setText(usuRegistro);
                    getEditextTil(til_login_password).setText(pasRegistro);
                }

                break;

            case REQUEST_TWO:
                // the code for request two
                break;

            default:
                callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void addChangedListenerTil(TextInputLayout mTextInputLayout) {
        getEditextTil(mTextInputLayout).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                switch (mTextInputLayout.getId()) {

                    case R.id.til_login_usuario:
                        mPresenter.validaUsuarioPresenter(charSequence);
                        break;

                    case R.id.til_login_password:
                        mPresenter.validaPasswordPresenter(charSequence);
                        break;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /***********************************
     * Acciones de los botones de la UI
     ***********************************/
    @OnClick(R.id.btn_login_ingresar_facebook)
    public void loginFacebook() {
        if (isNetworkConnected())
            mPresenter.onLoginFacebookPresenter();
        else onError(R.string.connection_error);
    }

    @OnClick(R.id.btn_login_ingresar_coppel)
    public void loginCoppel() {
        if (isNetworkConnected())
            mPresenter.onLoginCoppelPresenter(getUsuario(), getPassword());
        else onError(R.string.connection_error);
    }

    @OnClick(R.id.btn_login_registrate)
    public void initRegistro() {
        mPresenter.initRegistroPresenter();
    }

    @Override
    public void setErrorUsuario(String msj) {
        til_login_usuario.setError(msj);
    }

    @Override
    public void setErrorPassword(String msj) {
        til_login_password.setError(msj);
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void navigateToRegistro() {
        startActivityForResult(new Intent(LoginActivity.this, RegistroUsuarioActivity.class), REQUEST_ONE);
    }

    @Override
    public void finishActivity() {
        LoginActivity.this.finish();
    }

    @Override
    public void mostrarMsjError(String msj) {
        onError(msj);
    }

    /***********************************************
     * GETTERS PARA OBTENER EL TEXTO DE LOS EDITEXT
     **********************************************/
    private String getUsuario() {
        return getToString(getEditextTil(til_login_usuario));
    }

    private String getPassword() {
        return getToString(getEditextTil(til_login_password));
    }

    private EditText getEditextTil(TextInputLayout textInputLayout) {
        return textInputLayout.getEditText();
    }

    private String getToString(EditText mEditText) {
        return mEditText.getText().toString().trim();
    }
}
