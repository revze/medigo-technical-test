package id.revan.medigotest.di.modules

import dagger.Module
import dagger.Provides
import id.revan.medigotest.ui.base.BaseViewModelFactory
import id.revan.medigotest.ui.main.MainViewModel
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Singleton
    @Provides
    fun provideLoginViewModel(viewModel: MainViewModel): BaseViewModelFactory<MainViewModel> {
        return BaseViewModelFactory { viewModel }
    }
}