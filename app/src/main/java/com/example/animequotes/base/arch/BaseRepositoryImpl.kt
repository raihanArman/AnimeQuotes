package com.example.animequotes.base.arch

import android.util.Log
import com.example.animequotes.BuildConfig
import com.example.animequotes.base.exception.ApiErrorException
import com.example.animequotes.base.exception.NoInternetConnectionException
import com.example.animequotes.base.exception.UnexpectedApiErrorException
import com.example.animequotes.base.wrapper.DataResource
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */
open class BaseRepositoryImpl: BaseContract.BaseRepository {
    override fun logResponse(msg: String?) {
        if (BuildConfig.DEBUG) {
            Log.d(BaseRepositoryImpl::class.java.simpleName, msg.orEmpty())
        }
    }

    suspend fun <T> safeNetwrokCall(apiCall: suspend () -> Response<T>): DataResource<T>{
        return try {
            val call = apiCall.invoke()
            if(call.isSuccessful && call.body() != null){
                DataResource.Success(call.body()!!)
            }else{
                DataResource.Error(ApiErrorException(call.errorBody().toString(), 0))
            }
        }catch (throwable: Throwable){
            when(throwable){
                is IOException -> DataResource.Error(NoInternetConnectionException())
                is HttpException -> {
                    when(val code = throwable.code()){
                        in 300..308 ->{
                            DataResource.Error(ApiErrorException("Redirect", code))
                        }
                        in 400..451 ->{
                            DataResource.Error(ApiErrorException("Client Error", code))
                        }
                        in 500..501 ->{
                            DataResource.Error(ApiErrorException("Server Error", code))
                        }
                        else ->{
                            DataResource.Error(ApiErrorException(httpCode = code))
                        }
                    }
                }
                else ->{
                    DataResource.Error(UnexpectedApiErrorException())
                }
            }
        }
    }

    suspend fun <T> proceed(coroutine: suspend () -> T): DataResource<T>{
        return try {
            DataResource.Success(coroutine.invoke())
        }catch (excetion: Exception){
            DataResource.Error(excetion)
        }
    }

}