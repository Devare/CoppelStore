package mx.com.solutions.devare.coppelstore.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import mx.com.solutions.devare.coppelstore.R;

public class CustomEdiText extends AppCompatEditText {

    public CustomEdiText(Context context) {
        super(context);
    }

    public CustomEdiText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    public CustomEdiText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    void initAttrs(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray attributeArray = context.obtainStyledAttributes(
                    attrs,
                    R.styleable.CustomEdiText);

            Drawable drawableStart = null;
            Drawable drawableEnd = null;
            Drawable drawableBottom = null;
            Drawable drawableTop = null;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawableStart = attributeArray.getDrawable(R.styleable.CustomEdiText_drawableStart);
                drawableEnd = attributeArray.getDrawable(R.styleable.CustomEdiText_drawableEnd);
                drawableBottom = attributeArray.getDrawable(R.styleable.CustomEdiText_drawableBottom);
                drawableTop = attributeArray.getDrawable(R.styleable.CustomEdiText_drawableTop);
            } else {
                final int drawableStartId = attributeArray.getResourceId(R.styleable.CustomEdiText_drawableStart, -1);
                final int drawableEndId = attributeArray.getResourceId(R.styleable.CustomEdiText_drawableEnd, -1);
                final int drawableBottomId = attributeArray.getResourceId(R.styleable.CustomEdiText_drawableBottom, -1);
                final int drawableTopId = attributeArray.getResourceId(R.styleable.CustomEdiText_drawableTop, -1);

                if (drawableStartId != -1)
                    drawableStart = AppCompatResources.getDrawable(context, drawableStartId);
                if (drawableEndId != -1)
                    drawableEnd = AppCompatResources.getDrawable(context, drawableEndId);
                if (drawableBottomId != -1)
                    drawableBottom = AppCompatResources.getDrawable(context, drawableBottomId);
                if (drawableTopId != -1)
                    drawableTop = AppCompatResources.getDrawable(context, drawableTopId);
            }
            setCompoundDrawablesWithIntrinsicBounds(drawableStart, drawableTop, drawableEnd, drawableBottom);
            attributeArray.recycle();
        }
    }
}
