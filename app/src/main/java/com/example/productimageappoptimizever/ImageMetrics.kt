package com.example.productimageappoptimizever

import android.graphics.drawable.BitmapDrawable
import android.os.Process
import android.os.SystemClock
import android.util.Log
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult

/**
 * Ghi log do hieu nang tai anh (giong het nhau o ca hai phien ban).
 * t  = ms tu luc process bat dau
 * ms = ms tu luc bind o danh sach den luc anh san sang (gom thoi gian cho trong hang doi)
 * src = MEMORY_CACHE / DISK / NETWORK
 * kb = dung luong bitmap sau giai ma
 */
class MetricListener(private val id: Int) : ImageRequest.Listener {
    private val t0 = SystemClock.elapsedRealtime()

    override fun onSuccess(request: ImageRequest, result: SuccessResult) {
        val now = SystemClock.elapsedRealtime()
        val bmp = (result.drawable as? BitmapDrawable)?.bitmap
        Log.i(
            TAG,
            "IMGM|id=$id|t=${now - Process.getStartElapsedRealtime()}|ms=${now - t0}" +
                "|src=${result.dataSource.name}|w=${bmp?.width ?: -1}|h=${bmp?.height ?: -1}" +
                "|kb=${(bmp?.allocationByteCount ?: 0) / 1024}|ok=1"
        )
    }

    override fun onError(request: ImageRequest, result: ErrorResult) {
        val now = SystemClock.elapsedRealtime()
        Log.i(
            TAG,
            "IMGM|id=$id|t=${now - Process.getStartElapsedRealtime()}|ms=${now - t0}" +
                "|ok=0|err=${result.throwable.javaClass.simpleName}"
        )
    }

    companion object {
        const val TAG = "ImgMetric"
    }
}
