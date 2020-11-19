package com.decimalab.minutehelp.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.decimalab.minutehelp.R;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class CustomTextInputLayout extends TextInputLayout {

    private String[] fonts = {"Roboto-Regular.ttf", "Roboto-Medium.ttf", "Roboto-Bold.ttf", "Roboto-Italic.ttf"};

    private Object collapsingTextHelper;
    private Rect bounds;
    private Method recalculateMethod;
    private Method setCollapsedTypefaceMethod;
    private Method setExpandedTypefaceMethod;

    public CustomTextInputLayout(Context context) {
        this(context, null);
        if (!isInEditMode()) {
            init(null);
        }
    }

    public CustomTextInputLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        if (!isInEditMode()) {
            init(attrs);
        }
    }

    public CustomTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        adjustBounds();
    }

    private void init(AttributeSet attrs) {

        try {
            Field cthField = TextInputLayout.class.getDeclaredField("collapsingTextHelper");
            cthField.setAccessible(true);
            collapsingTextHelper = cthField.get(this);

            assert collapsingTextHelper != null;
            Field boundsField = collapsingTextHelper.getClass().getDeclaredField("collapsedBounds");
            boundsField.setAccessible(true);
            bounds = (Rect) boundsField.get(collapsingTextHelper);

            recalculateMethod = collapsingTextHelper.getClass().getDeclaredMethod("recalculate");

            setCollapsedTypefaceMethod = collapsingTextHelper.getClass().getDeclaredMethod("setCollapsedTypeface", Typeface.class);
            setCollapsedTypefaceMethod.setAccessible(true);

            setExpandedTypefaceMethod = collapsingTextHelper.getClass().getDeclaredMethod("setExpandedTypeface", Typeface.class);
            setExpandedTypefaceMethod.setAccessible(true);

            if (attrs != null) {
                TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomTextInputLayout);
                if (a.getString(R.styleable.CustomTextInputLayout_font_type) != null) {
                    String fontName = fonts[Integer.parseInt(a.getString(R.styleable.CustomTextInputLayout_font_type))];

                    if (fontName != null) {
                        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontName);
                        //setTypeface(myTypeface);
                        try {
                            setCollapsedTypefaceMethod.invoke(collapsingTextHelper, myTypeface);
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                    a.recycle();
                }
            }

        } catch (NoSuchMethodException e) {
            collapsingTextHelper = null;
            bounds = null;
            recalculateMethod = null;
            setCollapsedTypefaceMethod = null;
            setExpandedTypefaceMethod = null;
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            collapsingTextHelper = null;
            bounds = null;
            recalculateMethod = null;
            setCollapsedTypefaceMethod = null;
            setExpandedTypefaceMethod = null;
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            collapsingTextHelper = null;
            bounds = null;
            recalculateMethod = null;
            setCollapsedTypefaceMethod = null;
            setExpandedTypefaceMethod = null;
            e.printStackTrace();
        }
    }

    public void setCollapsedTypeface(Typeface typeface) {
        if (collapsingTextHelper == null) {
            return;
        }

        try {
            setCollapsedTypefaceMethod.invoke(collapsingTextHelper, typeface);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void setExpandedTypeface(Typeface typeface) {
        if (collapsingTextHelper == null) {
            return;
        }

        try {
            setExpandedTypefaceMethod.invoke(collapsingTextHelper, typeface);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void adjustBounds() {
        if (collapsingTextHelper == null) {
            return;
        }

        try {
            assert getEditText() != null;
            bounds.left = getEditText().getLeft() + getEditText().getPaddingLeft();
            recalculateMethod.invoke(collapsingTextHelper);
        } catch (InvocationTargetException | IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
