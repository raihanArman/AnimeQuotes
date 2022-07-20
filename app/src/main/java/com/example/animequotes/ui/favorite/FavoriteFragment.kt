package com.example.animequotes.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animequotes.R
import com.example.animequotes.base.arch.BaseFragment
import com.example.animequotes.databinding.FragmentFavoriteBinding
import com.example.animequotes.domain.viewparams.Quote
import com.example.animequotes.util.ext.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment: BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>(
    FragmentFavoriteBinding::inflate
) {
    private val favoriteAdapter: FavoriteAdapter by lazy {
        FavoriteAdapter{
            onClickItem(it)
        }
    }

    override val viewModel: FavoriteViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeData()
    }

    override fun initView() {
        binding.run {
            rvFavoriteQuotes.apply {
                adapter = favoriteAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
        viewModel.getFavoriteQuotes()
    }

    override fun observeData() {
        super.observeData()
        viewModel.observeQuotes.observe(viewLifecycleOwner){
            handleData(it)
        }
    }

    override fun <T> showData(data: T) {
        super.showData(data)
        if (data is MutableList<*>) {
            favoriteAdapter.submitList(data as List<Quote>)
        }
    }

    private fun onClickItem(quote: Quote){

    }

    override fun showEmptyData(isEmpty: Boolean) {
        binding.tvErrorLayout.text = getString(R.string.text_empty_list_favorite)
        binding.tvErrorLayout.isVisible = isEmpty
    }

    override fun showLoading(isShowLoading: Boolean) {
        binding.pbLoading.isVisible = isShowLoading
    }

    override fun showContent(isContentVisible: Boolean) {
        binding.rvFavoriteQuotes.isVisible = isContentVisible
    }

    override fun showError(isErrorEnabled: Boolean, exception: Exception?) {
        binding.tvErrorLayout.isVisible = isErrorEnabled
        if (isErrorEnabled) {
            binding.tvErrorLayout.text = getErrorMessageByException(exception)
        }
    }

}