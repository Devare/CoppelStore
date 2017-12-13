
package mx.com.solutions.devare.coppelstore.data.db.modelo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseProductos {

    @SerializedName("estado")
    @Expose
    private Integer estado;
    @SerializedName("productos")
    @Expose
    private List<Producto> productos = null;

    public ResponseProductos() {

    }

    public ResponseProductos(Integer estado, List<Producto> productos) {
        this.estado = estado;
        this.productos = productos;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

}
