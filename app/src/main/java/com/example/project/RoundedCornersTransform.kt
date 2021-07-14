package com.example.project

import android.graphics.*
import com.squareup.picasso.Transformation


class RoundedCornersTransform(recyclerAdapterChatList: RecyclerAdapterPhotosList) :
    Transformation {
    override fun transform(source: Bitmap?): Bitmap {
        val size = source!!.width.coerceAtMost(source.height)

        val x = (source.width - size) / 2
        val y = (source.height - size) / 2

        val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
        if (squaredBitmap != source) {
            source.recycle()
        }

        val bitmap = Bitmap.createBitmap(size, size, source.config)

        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.isAntiAlias = true

        val r = size / 8f
        canvas.drawRoundRect(
            RectF(0F, 0F, source.width.toFloat(), source.height.toFloat()),
            r,
            r,
            paint
        )
        squaredBitmap.recycle()
        return bitmap
    }

    override fun key(): String {
        return "rounded_corners";
    }

}
