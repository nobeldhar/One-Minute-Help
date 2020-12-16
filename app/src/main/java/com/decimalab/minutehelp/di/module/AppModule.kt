package com.decimalab.minutehelp.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.NonNull
import androidx.room.Room
import com.decimalab.minutehelp.BuildConfig
import com.decimalab.minutehelp.data.local.OneMinuteHelpDatabase
import com.decimalab.minutehelp.data.local.daos.AddressDao
import com.decimalab.minutehelp.data.remote.RequestInterceptor
import com.decimalab.minutehelp.data.remote.services.AuthService
import com.decimalab.minutehelp.data.remote.services.DashboardService
import com.decimalab.minutehelp.data.remote.services.SettingsService
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {

    /*lateinit var database: FoodDatabase
    private val databaseCallback = object : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            CoroutineScope(Dispatchers.IO).launch {
                prePopulateDatabase()
            }
        }

    }*/

    @Provides
    @Singleton
    fun provideDatabase(@NonNull application: Application): OneMinuteHelpDatabase {
        return Room
            .databaseBuilder(application, OneMinuteHelpDatabase::class.java, "one_minute_help_database")
            .build()
    }

    @Provides
    @Singleton
    fun provideAddressDao(@NonNull database: OneMinuteHelpDatabase): AddressDao {
        return database.addressDao()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }


    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        requestInterceptor: RequestInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        with(builder) {
            addInterceptor(requestInterceptor)
            addInterceptor(loggingInterceptor)
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }
        return builder.build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(@NonNull gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
/*
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
*/
            .build()
    }


    @Provides
    @Singleton
    fun provideSharedPreference(@NonNull application: Application): SharedPreferences {
        return application.getSharedPreferences(BuildConfig.PREF_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun provideDashboardService(retrofit: Retrofit): DashboardService {
        return retrofit.create(DashboardService::class.java)
    }

    @Provides
    @Singleton
    fun provideSettingsService(retrofit: Retrofit): SettingsService {
        return retrofit.create(SettingsService::class.java)
    }

}