package com.example.animequotes.ui.home

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.animequotes.R
import com.example.animequotes.base.arch.BaseFragment
import com.example.animequotes.base.wrapper.ViewResource
import com.example.animequotes.databinding.FragmentHomeBinding
import com.example.animequotes.domain.viewparams.Quote
import com.example.animequotes.util.ext.createRoundedBackground
import com.example.animequotes.util.ext.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    override val viewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeData()
    }

    override fun initView() {
        setClickListeners()
        viewModel.getRandomQuote()
    }

    private fun setClickListeners() {
        binding.btnRefreshQuotes.setOnClickListener { viewModel.onRefreshData() }
        binding.ivFavoriteList.setOnClickListener { viewModel.onNavigateToFavorite() }
        binding.btnFavoriteQuotes.setOnClickListener { viewModel.addFavoriteQuote() }

    }

    override fun observeData() {
        viewModel.observeQuote.observe(viewLifecycleOwner){
            handleData(it)
        }

        viewModel.observeAddFavoriteResult.observe(viewLifecycleOwner){
            when(it){
                is ViewResource.Success -> {
                    showData(it.data)
                }
                is ViewResource.Error -> {
                    toast(getString(R.string.text_error_add_favorite))
                }
                else -> {
                    return@observe
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.homeEvent.collect{event ->
                when(event){
                    is HomeEvent.NavigateToFavorite ->{
                        val action = HomeFragmentDirections.actionHomeFragmentToFavoriteFragment()
                        findNavController().navigate(action)
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
            GradientDrawable().createRoundedBackground(requireActivity(), hexColor, 16)
    }

    override fun <T> showData(data: T) {
        if(data is Quote){
            viewModel.currentQuote = data
            binding.btnFavoriteQuotes.setImageDrawable(
                ContextCompat.getDrawable(
                    requireActivity(),
                    if (data.isFavorite) R.drawable.ic_favorite_true else R.drawable.ic_favorite_false
                )
            )
            binding.tvQuotesText.text =data.quote
            binding.tvAuthor.text = data.anime
            setBackgroundCardColor(viewModel.cardColor)
        }
    }

}