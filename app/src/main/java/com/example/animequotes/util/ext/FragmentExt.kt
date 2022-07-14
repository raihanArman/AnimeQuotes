package com.example.animequotes.util.ext

import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */

fun Fragment.toast(message: String?){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}