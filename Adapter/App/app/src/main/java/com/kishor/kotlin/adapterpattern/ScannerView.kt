package com.kishor.kotlin.adapterpattern

import android.app.Application
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScannerView(appContext: Application, private val qrCodeScanResult: QRCodeScanResult) : ZXingScannerView.ResultHandler {
    private val mScannerView by lazy { ZXingScannerView(appContext) }



    override fun handleResult(p0: com.google.zxing.Result?) {
        qrCodeScanResult.onScanResult(p0!!.text)
    }
}

interface QRCodeScanResult {
    fun onScanResult(result: String)
}