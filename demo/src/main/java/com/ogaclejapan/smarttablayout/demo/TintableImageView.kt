package com.ogaclejapan.smarttablayout.demo

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

/**
 * https://gist.github.com/tylerchesley/5d15d859be4f3ce31213
 */
class TintableImageView : AppCompatImageView {

    private var tint: ColorStateList? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs, defStyle)
    }

    private fun init(context: Context, attrs: AttributeSet, defStyle: Int) {
        val a = context.obtainStyledAttributes(
                attrs, R.styleable.TintableImageView, defStyle, 0)
        tint = a.getColorStateList(
                R.styleable.TintableImageView_tint)
        a.recycle()
    }

    override fun drawableStateChanged() {
        super.drawableStateChanged()
        if (tint != null && tint!!.isStateful) {
            updateTintColor()
        }
    }

    fun setColorFilter(tint: ColorStateList) {
        this.tint = tint
        super.setColorFilter(tint.getColorForState(drawableState, 0))
    }

    private fun updateTintColor() {
        val color = tint!!.getColorForState(drawableState, 0)
        setColorFilter(color)
    }

}
