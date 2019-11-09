package id.revan.medigotest.di

import dagger.Component
import id.revan.medigotest.di.modules.ApiModule
import id.revan.medigotest.di.modules.RecyclerViewAdapterModule
import id.revan.medigotest.di.modules.RepositoryModule
import id.revan.medigotest.di.modules.ViewModelModule
import id.revan.medigotest.ui.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, RepositoryModule::class, ViewModelModule::class, RecyclerViewAdapterModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}