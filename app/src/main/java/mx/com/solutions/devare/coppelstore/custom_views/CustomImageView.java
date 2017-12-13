package mx.com.solutions.devare.coppelstore.custom_views;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.IntRange;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import mx.com.solutions.devare.coppelstore.R;

public class CustomImageView extends AppCompatImageView {
    private Drawable mNormalDrawable;

    private Drawable mPressedDrawable;

    private Drawable mUnableDrawable;

    private int mDuration = 0;

    private int[][] states;

    private StateListDrawable mStateBackground;

    public  CustomImageView(Context context) {
        this(context, null);
    }

    public  CustomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public  CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        states = new int[4][];
        states[0] = new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled };
        states[1] = new int[] { android.R.attr.state_enabled, android.R.attr.state_focused };
        states[3] = new int[] { -android.R.attr.state_enabled };
        states[2] = new int[] { android.R.attr.state_enabled };

        Drawable drawable = getBackground();
        if(drawable != null && drawable instanceof StateListDrawable){
            mStateBackground = (StateListDrawable) drawable;
        }else{
            mStateBackground = new StateListDrawable();
        }

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomImageView);

        final int drawableSelected =a.getResourceId(R.styleable.CustomImageView_selectedDrawable, -1);
        final int drawableUnSelected = a.getResourceId(R.styleable.CustomImageView_unselectedDrawable, -1);
        final int drawableNormal = a.getResourceId(R.styleable.CustomImageView_normalDrawable, -1);

        if (drawableSelected != -1)
            mPressedDrawable = AppCompatResources.getDrawable(context, drawableSelected);
        if (drawableUnSelected != -1)
            mUnableDrawable = AppCompatResources.getDrawable(context, drawableUnSelected);
        if (drawableNormal != -1)
            mNormalDrawable = AppCompatResources.getDrawable(context, drawableNormal);

        setStateBackground(mNormalDrawable, mPressedDrawable, mUnableDrawable);

        mDuration = a.getInteger(R.styleable.CustomImageView_animationDrawable, mDuration);
        setAnimationDuration(mDuration);
        a.recycle();
    }

    /**
     * @param normal
     * @param pressed
     * @param unable
     */
    public  void setStateBackground(Drawable normal, Drawable pressed, Drawable unable){
        this.mNormalDrawable = normal;
        this.mPressedDrawable = pressed;
        this.mUnableDrawable = unable;

        //set background
        if(mPressedDrawable != null) {
            mStateBackground.addState(states[0], mPressedDrawable);
            mStateBackground.addState(states[1], mPressedDrawable);
        }

        if(mUnableDrawable != null) {
            mStateBackground.addState(states[3], mUnableDrawable);
        }

        if(mNormalDrawable != null) {
            mStateBackground.addState(states[2], mNormalDrawable);
        }
        //setBackgroundDrawable(mStateBackground);
        setImageDrawable(mStateBackground);
    }

    /**
     * @param duration
     */
    public  void setAnimationDuration(@IntRange(from = 0)int duration){
        this.mDuration = duration;
        mStateBackground.setEnterFadeDuration(mDuration);
        mStateBackground.setExitFadeDuration(mDuration);
    }
}