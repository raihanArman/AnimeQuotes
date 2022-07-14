package com.example.animequotes.util.ext

import android.app.Activity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */

fun AppCompatActivity.toast(message: String?){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}