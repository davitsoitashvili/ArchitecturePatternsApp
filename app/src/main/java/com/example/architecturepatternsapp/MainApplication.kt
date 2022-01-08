package com.example.architecturepatternsapp

import android.app.Application
import com.example.model.apiservice.comment.CommentApiService
import com.example.model.datasources.CommentDataSource
import com.example.model.datasources.CommentDataSourceImpl
import com.example.model.network.RetrofitClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by Davit Soitashvili on 06.01.22
 **/

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            loadKoinModules(getAppModule())
        }
    }

    private fun getAppModule() = module {
        single { RetrofitClient().getApiClient() }
        single { get<Retrofit>().create(CommentApiService::class.java) }
        single<CommentDataSource> { CommentDataSourceImpl(get()) }
    }
}