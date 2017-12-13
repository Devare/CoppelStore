
package mx.com.solutions.devare.coppelstore.data.db.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Producto extends RealmObject implements Parcelable {

    @SerializedName("idProducto")
    @Expose
    private String idProducto;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("Descripcion")
    @Expose
    private String descripcion;
    @SerializedName("PrecioVenta")
    @Expose
    private String precioVenta;
    @SerializedName("PrecioCompra")
    @Expose
    private String precioCompra;
    @SerializedName("StockInicial")
    @Expose
    private String stockInicial;
    @SerializedName("StockFinal")
    @Expose
    private String stockFinal;
    @SerializedName("Url_Imagen")
    @Expose
    private String urlImagen;
    @SerializedName("Fecha_Creacion_Modificacion")
    @Expose
    private String fechaCreacionModificacion;
    @SerializedName("statusOferta")
    @Expose
    private String statusOferta;
    @SerializedName("idCategoria")
    @Expose
    private String idCategoria;
    @SerializedName("idProveedor")
    @Expose
    private String idProveedor;

    public Producto() {
    }


    public Producto(String idProducto, String nombre, String descripcion, String precioVenta, String precioCompra, String stockInicial, String stockFinal, String urlImagen, String fechaCreacionModificacion, String statusOferta, String idCategoria, String idProveedor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.stockInicial = stockInicial;
        this.stockFinal = stockFinal;
        this.urlImagen = urlImagen;
        this.fechaCreacionModificacion = fechaCreacionModificacion;
        this.statusOferta = statusOferta;
        this.idCategoria = idCategoria;
        this.idProveedor = idProveedor;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(String precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(String precioCompra) {
        this.precioCompra = precioCompra;
    }

    public String getStockInicial() {
        return stockInicial;
    }

    public void setStockInicial(String stockInicial) {
        this.stockInicial = stockInicial;
    }

    public String getStockFinal() {
        return stockFinal;
    }

    public void setStockFinal(String stockFinal) {
        this.stockFinal = stockFinal;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getFechaCreacionModificacion() {
        return fechaCreacionModificacion;
    }

    public void setFechaCreacionModificacion(String fechaCreacionModificacion) {
        this.fechaCreacionModificacion = fechaCreacionModificacion;
    }

    public String getStatusOferta() {
        return statusOferta;
    }

    public void setStatusOferta(String statusOferta) {
        this.statusOferta = statusOferta;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.idProducto);
        parcel.writeString(this.nombre);
        parcel.writeString(this.descripcion);
        parcel.writeString(this.precioVenta);
        parcel.writeString(this.precioCompra);
        parcel.writeString(this.stockInicial);
        parcel.writeString(this.stockFinal);
        parcel.writeString(this.urlImagen);
        parcel.writeString(this.fechaCreacionModificacion);
        parcel.writeString(this.statusOferta);
        parcel.writeString(this.idCategoria);
        parcel.writeString(this.idProveedor);
    }

    protected Producto(Parcel in) {
        this.idProducto = in.readString();
        this.nombre = in.readString();
        this.descripcion = in.readString();
        this.precioVenta = in.readString();
        this.precioCompra = in.readString();
        this.stockInicial = in.readString();
        this.stockFinal = in.readString();
        this.urlImagen = in.readString();
        this.fechaCreacionModificacion= in.readString();
        this.statusOferta= in.readString();
        this.idCategoria= in.readString();
        this.idProveedor= in.readString();
    }

    public static final Parcelable.Creator<Producto> CREATOR = new Parcelable.Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel source) {
            return new Producto(source);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };


}
