package com.decimalab.minutehelp.ui.widget

import android.content.Context
import android.graphics.Rect
import android.graphics.Typeface
import android.util.AttributeSet
import com.decimalab.minutehelp.R
import com.google.android.material.textfield.TextInputLayout
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

class CustomTextViewInputLayout  : TextInputLayout
{
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        initAttrs(attrs)
    }
    constructor(context: Context):this(context = context, attrs = null){
        if (!isInEditMode)
            initAttrs(null)
    }
    constructor(context: Context, attrs: AttributeSet?):this(context = context, attrs = attrs, defStyleAttr = 0){
        attrs?.let {
            if (!isInEditMode){
                initAttrs(attrs)
            }
        }
    }

    private val fonts = arrayOf("Roboto-Regular.ttf", "Roboto-Medium.ttf", "Roboto-Bold.ttf", "Roboto-Italic.ttf")

    private var collapsingTextHelper: Any? = null
    private var bounds: Rect? = null
    private var recalculateMethod: Method? = null
    private var setCollapsedTypefaceMethod: Method? = null
    private var setExpandedTypefaceMethod: Method? = null


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        adjustBounds()
    }

    private fun initAttrs(attrs: AttributeSet?) {
        try {
            val cthField = TextInputLayout::class.java.getDeclaredField("collapsingTextHelper")
            cthField.isAccessible = true
            collapsingTextHelper = cthField[this]
            collapsingTextHelper?.let {
                val boundsField = collapsingTextHelper!!.javaClass.getDeclaredField("collapsedBounds")
                boundsField.isAccessible = true
                bounds = boundsField[collapsingTextHelper] as Rect
                recalculateMethod = collapsingTextHelper!!.javaClass.getDeclaredMethod("recalculate")
                setCollapsedTypefaceMethod = collapsingTextHelper!!.javaClass.getDeclaredMethod("setCollapsedTypeface", Typeface::class.java)
                setCollapsedTypefaceMethod?.let { it.isAccessible = true }
                setExpandedTypefaceMethod = collapsingTextHelper!!.javaClass.getDeclaredMethod("setExpandedTypeface", Typeface::class.java)
                setExpandedTypefaceMethod?.let { it.isAccessible = true }
                if (attrs != null) {
                    val a = context.obtainStyledAttributes(attrs, R.styleable.CustomTextInputLayout)
                    if (a.getString(R.styleable.CustomTextInputLayout_font_type) != null) {
                        val fontName = fonts[a.getString(R.styleable.CustomTextInputLayout_font_type)!!.toInt()]
                        if (fontName != null) {
                            val myTypeface = Typeface.createFromAsset(context.assets, "fonts/$fontName")
                            //setTypeface(myTypeface);
                            try {
                                setCollapsedTypefaceMethod?.let { it.invoke(collapsingTextHelper, myTypeface) }
                            } catch (e: IllegalAccessException) {
                                e.printStackTrace()
                            } catch (e: IllegalArgumentException) {
                                e.printStackTrace()
                            } catch (e: InvocationTargetException) {
                                e.printStackTrace()
                            }
                        }
                        a.recycle()
                    }
                }
            }

        } catch (e: NoSuchMethodException) {
            collapsingTextHelper = null
            bounds = null
            recalculateMethod = null
            setCollapsedTypefaceMethod = null
            setExpandedTypefaceMethod = null
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            collapsingTextHelper = null
            bounds = null
            recalculateMethod = null
            setCollapsedTypefaceMethod = null
            setExpandedTypefaceMethod = null
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            collapsingTextHelper = null
            bounds = null
            recalculateMethod = null
            setCollapsedTypefaceMethod = null
            setExpandedTypefaceMethod = null
            e.printStackTrace()
        }
    }

    fun setCollapsedTypeface(typeface: Typeface?) {
        if (collapsingTextHelper == null) {
            return
        }
        try {
            setCollapsedTypefaceMethod!!.invoke(collapsingTextHelper, typeface)
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
    }

    fun setExpandedTypeface(typeface: Typeface?) {
        if (collapsingTextHelper == null) {
            return
        }
        try {
            setExpandedTypefaceMethod!!.invoke(collapsingTextHelper, typeface)
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
    }

    private fun adjustBounds() {
        if (collapsingTextHelper == null) {
            return
        }
        try {
            assert(editText != null)
            bounds!!.left = editText!!.left + editText!!.paddingLeft
            recalculateMethod!!.invoke(collapsingTextHelper)
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }
}