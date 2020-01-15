package com.kishor.kotlin.adapterpattern

interface Scanner {
    fun startScanning()
    fun stopScanning()
    fun scannedResult()
}


class ScannerAdapter()