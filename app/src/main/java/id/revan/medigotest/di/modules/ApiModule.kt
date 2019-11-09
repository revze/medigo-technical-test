package id.revan.medigotest.di.modules

import dagger.Module
import dagger.Provides
import id.revan.medigotest.data.services.ApiService
import id.revan.medigotest.utils.Endpoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideApiInterface(): ApiService {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Endpoint.BASE_URL)
            .build().create(ApiService::class.java)
    }
}