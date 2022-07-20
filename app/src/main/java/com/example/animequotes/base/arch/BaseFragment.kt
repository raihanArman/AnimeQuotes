package com.example.animequotes.base.arch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.animequotes.R
import com.example.animequotes.base.exception.ApiErrorException
import com.example.animequotes.base.exception.NoInternetConnectionException
import com.example.animequotes.base.wrapper.ViewResource
import com.example.animequotes.util.ext.toast

/**
 * @author Raihan Arman
 * @date 20/07/2022
 */
abstract class BaseFragment<B: ViewBinding, VM: BaseViewModel>(
    val bindingFactory: (LayoutInflater) -> B
): Fragment(), BaseContract.BaseView {

    protected lateinit var binding: B
    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingFactory(layoutInflater)
        return binding.root
    }

    abstract fun initView()

    override fun observeData() {

    }

    override fun showEmptyData(isEmpty: Boolean) {

    }

    override fun showContent(isContentVisible: Boolean) {

    }

    override fun showLoading(isShowLoading: Boolean) {

    }

    override fun <T> showData(data: T) {

    }

    override fun showError(isErrorEnabled: Boolean, exception: Exception?) {
        if(isErrorEnabled){
            toast(getErrorMessageByException(exception))
        }
    }

    protected fun getErrorMessageByException(exception: Exception?): String {
        return when (exception) {
            is NoInternetConnectionException -> this.getString(R.string.no_internet_connection)
            is ApiErrorException -> exception.message.orEmpty()
            else -> this.getString(R.string.unknown_error)
        }
    }

    override fun <T : ViewResource<*>> handleData(viewResource: T) {
        viewResource.let {
            resetView()
            when(viewResource){
                is ViewResource.Success<*> -> {
                    showContent(true).also {
                        showData(viewResource.data)
                    }
                }
                is ViewResource.Empty<*> -> {
                    showEmptyData(true)
                }
                is ViewResource.Loading<*> -> {
                    showLoading(true)
                }
                is ViewResource.Error<*> -> {
                    showError(true, viewResource.exception)
                }
            }
        }
    }

    private fun resetView() {
        showContent(false)
        showLoading(false)
        showError(false)
        showEmptyData(false)
    }


}