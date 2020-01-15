package com.kishor.kotlin.adapterpattern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

interface ClientInterface {
    fun startScanner()
    fun stopScanner()
}

class ScanView(): ZXingScannerView.ResultHandler {
    private val mScannerView by lazy { ZXingScannerView(appContext) }
    override fun handleResult(p0: Result?) {

    }
}
