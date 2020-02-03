package com.yoyo.cinema.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.bumptech.glide.request.target.CustomTarget
import kotlinx.android.synthetic.main.item_movie.view.*

class PosterGlideTarget(
    val rootConstraintLayout: ConstraintLayout,
    val destination: ImageView
) : CustomTarget<Bitmap>() {

    private val constraintSet = ConstraintSet()

    override fun onResourceReady(
        resource: android.graphics.Bitmap,
        transition: com.bumptech.glide.request.transition.Transition<in android.graphics.Bitmap>?
    ) {
        val ratio = String.format("%d:%d", resource.width, resource.height)
        constraintSet.clone(rootConstraintLayout)
        constraintSet.setDimensionRatio(destination.id, ratio)
        constraintSet.applyTo(rootConstraintLayout)
        destination.posterImageView.setImageBitmap(resource)
    }

    override fun onLoadCleared(placeholder: Drawable?) {
    }
}