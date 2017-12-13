package mx.com.solutions.devare.coppelstore.data.network;

import io.reactivex.Observable;
import mx.com.solutions.devare.coppelstore.data.db.modelo.RequestLogin;
import mx.com.solutions.devare.coppelstore.data.db.modelo.RequestUsuario;
import mx.com.solutions.devare.coppelstore.data.db.modelo.ResponseLogin;
import mx.com.solutions.devare.coppelstore.data.db.modelo.ResponseProductos;
import mx.com.solutions.devare.coppelstore.data.db.modelo.ResponseSucess;
import mx.com.solutions.devare.coppelstore.utils.Constantes;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    @GET(Constantes.GET_ALL_PRODUCTOS)
    Observable<ResponseProductos> getProductos(@Header("Authorization") String authorization);

    @POST(Constantes.LOGIN_USUARIO)
    Observable<ResponseLogin> login(@Body RequestLogin mLogin);

    @POST(Constantes.REGISTRO_USUARIO)
    Observable<ResponseSucess> registrarUsuario(@Body RequestUsuario mUsuario);

}
