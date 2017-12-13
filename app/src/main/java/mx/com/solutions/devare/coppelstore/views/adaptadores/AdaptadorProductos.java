package mx.com.solutions.devare.coppelstore.views.adaptadores;


import android.content.res.Resources;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mx.com.solutions.devare.coppelstore.R;
import mx.com.solutions.devare.coppelstore.data.db.modelo.Producto;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ViewHolderProducto> {

    private List<Producto> mProductoList;

    public AdaptadorProductos(List<Producto> mProductoList, OnSetOnclickListener mOnSetOncliclListener) {
        this.mProductoList = mProductoList;
        this.mOnSetOncliclListener = mOnSetOncliclListener;
    }

    @Override
    public ViewHolderProducto onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lists_producto, parent, false);
        return new ViewHolderProducto(mView);
    }

    @Override
    public void onBindViewHolder(ViewHolderProducto holder, int position) {
        Producto mProducto = mProductoList.get(position);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_sin_imagen);
        requestOptions.fitCenter();

        Glide.with(holder.itemView.getContext())
                .applyDefaultRequestOptions(requestOptions)
                .load(mProducto.getUrlImagen())
                .into(holder.iv_item_producto);
        holder.tv_item_nombre_producto.setText(mProducto.getNombre());
        holder.tv_item_precio_producto.setText(String.format("$%s", mProducto.getPrecioVenta()));
        holder.tv_item_pago_quincenal.setText("Desde $196.00 MXN quincenal");
        holder.tv_item_stock_producto.setText(String.format("%s Pzas", mProducto.getStockInicial()));
        /*Drawable leftDrawable = AppCompatResources.getDrawable(holder.itemView.getContext(), R.drawable.ic_add_shopping_selected);
        holder.tv_test_vector.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, null, null);*/
    }

    @Override
    public int getItemCount() {
        if (mProductoList != null && !mProductoList.isEmpty()) return mProductoList.size();
        else return 0;
    }

    public Producto obtenerProducto(int posicion) {
        if (posicion != RecyclerView.NO_POSITION) {
            return mProductoList.get(posicion);
        } else {
            return null;
        }
    }

    public class ViewHolderProducto extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_producto)
        ImageView iv_item_producto;
        @BindView(R.id.tv_item_precio_producto)
        TextView tv_item_precio_producto;
        @BindView(R.id.tv_item_nombre_producto)
        TextView tv_item_nombre_producto;
        @BindView(R.id.tv_item_stock_producto)
        TextView tv_item_stock_producto;
        @BindView(R.id.tv_item_pago_quincenal)
        TextView tv_item_pago_quincenal;
        @BindView(R.id.iv_test)
        AppCompatImageView iv_test;

        public ViewHolderProducto(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.btn_accion_mostrar_detalle)
        public void mostrarDetalle() {
            mOnSetOncliclListener.onClick(this, obtenerProducto(getAdapterPosition()));
        }
    }

    public interface OnSetOnclickListener {
        public void onClick(ViewHolderProducto holderProducto, Producto mProducto);
    }

    public OnSetOnclickListener mOnSetOncliclListener;
}
