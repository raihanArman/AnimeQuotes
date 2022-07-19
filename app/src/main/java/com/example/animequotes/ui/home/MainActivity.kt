package com.example.animequotes.ui.home

import android.graphics.drawable.GradientDrawable
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.animequotes.base.arch.BaseActivity
import com.example.animequotes.databinding.ActivityMainBinding
import com.example.animequotes.domain.viewparams.Quote
import com.example.animequotes.util.ext.createRoundedBackground
import com.example.animequotes.util.ext.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG = "MainActivity"
class MainActivity : BaseActivity<ActivityMainBinding, HomeViewModel>(
    ActivityMainBinding::inflate
) {

    override val viewModel: HomeViewModel by viewModel()

    override fun initView() {
        setClickListeners()
        viewModel.getRandomQuote()
    }

    private fun setClickListeners() {
        binding.btnRefreshQuotes.setOnClickListener { viewModel.onRefreshData() }
    }

    override fun observeData() {
        viewModel.observeQuote.observe(this){
            handleData(it)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.homeEvent.collect{event ->
                when(event){
                    is HomeEvent.NavigateToFavorite ->{

                    }
                    is HomeEvent.AddFavoriteAction ->{

                    }
                    is HomeEvent.ShareQuoteAction ->{

                    }
                    is HomeEvent.RefreshData ->{
                        toast(event.message)
                    }
                }
            }
        }
    }

    private fun setBackgroundCardColor(hexColor: String) {
        binding.clQuoteContainer.background =
            GradientDrawable().createRoundedBackground(this, hexColor, 16)
    }

    override fun <T> showData(data: T) {
        if(data is Quote){
            viewModel.currentQuote = data
//            binding.btnFavoriteQuotes.setImageDrawable(
//                ContextCompat.getDrawable(
//                    this,
//                    if (data.isFavorite) R.drawable.ic_favorite_true else R.drawable.ic_favorite_false
//                )
//            )
            binding.tvQuotesText.text =data.quote
            binding.tvAuthor.text = data.anime
            setBackgroundCardColor(viewModel.cardColor)
        }
    }

}