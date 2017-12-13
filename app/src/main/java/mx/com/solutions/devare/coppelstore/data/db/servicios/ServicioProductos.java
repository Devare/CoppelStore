package mx.com.solutions.devare.coppelstore.data.db.servicios;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import mx.com.solutions.devare.coppelstore.data.db.modelo.Producto;

public class ServicioProductos {

    Realm realm;

    @Inject
    public ServicioProductos(Realm realm){
        this.realm = realm;
    }

    //Obtener Productos
    public Producto[] getAllProductos(){
        RealmResults<Producto> results = realm.where(Producto.class).findAll();
        return results.toArray(new Producto[results.size()]);
    }

    public Producto getProductoId(int idProducto){
        return realm.where(Producto.class).equalTo("idProducto",idProducto).findFirst();
    }

    //Actualizar Producto
    public void updateProducto(String idProducto, String nombre, String descripcion, String precioVenta, String precioCompra, String stockInicial, String stockFinal, String urlImagen, String fechaCreacionModificacion, String statusOferta, String idCategoria, String idProveedor){
        Producto  producto = realm.createObject(Producto.class, idProducto);
        realm.beginTransaction();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecioVenta(precioVenta);
        producto.setPrecioCompra(precioCompra);
        producto.setStockInicial(stockInicial);
        producto.setStockFinal(stockFinal);
        producto.setUrlImagen(urlImagen);
        producto.setFechaCreacionModificacion(fechaCreacionModificacion);
        producto.setStatusOferta(statusOferta);
        producto.setIdCategoria(idCategoria);
        producto.setIdProveedor(idProveedor);
        realm.commitTransaction();

    }

    //Crear Producto
    public void createProducto(String idProducto, String nombre, String descripcion, String precioVenta, String precioCompra, String stockInicial, String stockFinal, String urlImagen, String fechaCreacionModificacion, String statusOferta, String idCategoria, String idProveedor){
        realm.beginTransaction();
        Producto  mProducto = realm.createObject(Producto.class, idProducto);
        mProducto.setNombre(nombre);
        mProducto.setDescripcion(descripcion);
        mProducto.setPrecioVenta(precioVenta);
        mProducto.setPrecioCompra(precioCompra);
        mProducto.setStockInicial(stockInicial);
        mProducto.setStockFinal(stockFinal);
        mProducto.setUrlImagen(urlImagen);
        mProducto.setFechaCreacionModificacion(fechaCreacionModificacion);
        mProducto.setStatusOferta(statusOferta);
        mProducto.setIdCategoria(idCategoria);
        mProducto.setIdProveedor(idProveedor);
        realm.commitTransaction();
    }

    //Eliminar
    public void deleteProducto(int idProducto){
        Producto mProducto = getProductoId(idProducto);
        realm.beginTransaction();
        mProducto.deleteFromRealm();
        realm.commitTransaction();
    }
}
