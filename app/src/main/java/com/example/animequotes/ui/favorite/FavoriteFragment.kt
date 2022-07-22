package com.example.animequotes.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animequotes.R
import com.example.animequotes.base.arch.BaseFragment
import com.example.animequotes.base.wrapper.ViewResource
import com.example.animequotes.databinding.FragmentFavoriteBinding
import com.example.animequotes.domain.viewparams.Quote
import com.example.animequotes.util.EventObserver
import com.example.animequotes.util.RemoveItemTouchHelper
import com.example.animequotes.util.ext.toast
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG = "FavoriteFragment"
class FavoriteFragment: BaseFragment<FragmentFavoriteBinding>(
    FragmentFavoriteBinding::inflate
) {
    private val favoriteAdapter: FavoriteAdapter by lazy {
        FavoriteAdapter{
            onClickItem(it)
        }
    }

    private val viewModel: FavoriteViewModel by lazy {
        requireActivity().getViewModel<FavoriteViewModel>()
    }
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
                this.addItemDecoration(
                    DividerItemDecoration(
                        requireActivity(),
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
        }
        ItemTouchHelper(RemoveItemTouchHelper(requireActivity()) { position ->
            //on Item Removed
            val item = favoriteAdapter.currentList[position]
            viewModel.deleteFavorite(item, position)
            favoriteAdapter.removeItem(position)
        }).apply {
            attachToRecyclerView(binding.rvFavoriteQuotes)
        }
    }

    override fun observeData() {
        super.observeData()
        viewModel.observeQuotes.observe(viewLifecycleOwner, EventObserver{
            handleData(it)
            Log.d(TAG, "observeData: ")
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteQuotes()
    }

    override fun <T> showData(data: T) {
        super.showData(data)
        if (data is MutableList<*>) {
            Log.d(TAG, "showData: size -> ${data.size}")
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