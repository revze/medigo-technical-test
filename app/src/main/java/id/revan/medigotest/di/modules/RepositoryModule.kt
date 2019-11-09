package id.revan.medigotest.di.modules

import dagger.Module
import dagger.Provides
import id.revan.medigotest.data.repository.WeatherRepository
import id.revan.medigotest.data.repository.WeatherRepositoryImpl
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideWeatherRepository(repository: WeatherRepositoryImpl): WeatherRepository = repository
}