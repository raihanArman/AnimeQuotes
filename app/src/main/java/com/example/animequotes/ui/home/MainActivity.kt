package com.example.animequotes.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.animequotes.R
import com.example.animequotes.base.arch.BaseActivity
import com.example.animequotes.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, HomeViewModel>(
    ActivityMainBinding::inflate
) {

    override val viewModel: HomeViewModel
        get() = TODO("Not yet implemented")

    override fun initView() {
        TODO("Not yet implemented")
    }
}