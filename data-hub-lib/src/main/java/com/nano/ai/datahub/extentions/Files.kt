package com.nano.ai.datahub.extentions

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

fun File.copyFile(src: File, dst: File) {
    FileInputStream(src).use { input ->
        FileOutputStream(dst).use { output ->
            input.copyTo(output)
        }
    }
}