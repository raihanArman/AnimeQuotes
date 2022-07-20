package com.example.animequotes.data.repository

import com.example.animequotes.base.arch.BaseRepositoryImpl
import com.example.animequotes.base.exception.DatabaseExecutionFailedException
import com.example.animequotes.base.wrapper.DataResource
import com.example.animequotes.data.local.datasource.QuoteLocalDataSource
import com.example.animequotes.data.local.entity.QuoteEntity
import com.example.animequotes.data.network.datasource.QuoteNetworkDataSource
import com.example.animequotes.data.network.model.QuoteResponse
import com.example.animequotes.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */
class QuoteRepositoryImpl(
    private val networkDataSource: QuoteNetworkDataSource,
    private val localDataSource: QuoteLocalDataSource
): QuoteRepository, BaseRepositoryImpl() {
    override suspend fun getRandomQuote(): Flow<DataResource<QuoteResponse>> = flow {
        emit(safeNetwrokCall { networkDataSource.getRandomQuote() })
    }

    override suspend fun getFavorite(): Flow<DataResource<List<QuoteEntity>>> = flow {
        emit(proceed { localDataSource.getFavoriteQuotes() })
    }

    override suspend fun addFavorite(entity: QuoteEntity): Flow<DataResource<Long>> = flow {
        emit(
            try {
                val totalRowsAffected = localDataSource.addFavorite(entity)
                if(totalRowsAffected > 0){
                    DataResource.Success(totalRowsAffected)
                }else{
                    DataResource.Error(DatabaseExecutionFailedException())
                }
            }catch (exception: Exception){
                DataResource.Error(exception)
            }
        )
    }


    override suspend fun getFavoriteQuotesById(id: String?): Flow<DataResource<QuoteEntity?>> =
        flow {
            emit(proceed { localDataSource.getFavoriteQuotesById(id) })
        }

    override suspend fun deleteFavoriteQuote(entity: QuoteEntity): Flow<DataResource<Int>> =
        flow {
            emit(
                try {
                    val totalRowsAffected = localDataSource.deleteFavorite(entity)
                    if (totalRowsAffected > 0) {
                        DataResource.Success(totalRowsAffected)
                    } else {
                        DataResource.Error(DatabaseExecutionFailedException())
                    }
                } catch (exception: Exception) {
                    DataResource.Error(exception)
                }
            )
        }
}

