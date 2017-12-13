package mx.com.solutions.devare.coppelstore.custom_views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import mx.com.solutions.devare.coppelstore.R;
import mx.com.solutions.devare.coppelstore.di.scopes.ActivityContext;

public class CustomToast {
    Context context;

    @Inject
    public CustomToast(Context context) {
        this.context = context;
    }

    //                                         Gravity.RIGHT | Gravity.TOP, offsetX, offsetY
    public void toastPersonalizado(String mensaje, int drawable, int a, int offsetX , int offsetY){

        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert li != null;
        View v = li.inflate(R.layout.custom_toast, null);

        TextView txtMessage = (TextView) v.findViewById(R.id.cv_tv_toast);
        txtMessage.setText(mensaje);

        ImageView imageView=(ImageView) v.findViewById(R.id.cv_iv_toast);
        imageView.setImageResource(drawable);

        Toast toast = new Toast(context);
        toast.setGravity(a, offsetX, offsetY);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(v);
        toast.show();
    }

}
