package id.revan.medigotest.di.modules

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import dagger.Module
import dagger.Provides

@Module
class RecyclerViewAdapterModule {
    @Provides
    fun provideGroupAdapter(): GroupAdapter<GroupieViewHolder> = GroupAdapter()
}