package id.revan.medigotest.di

class Injector {
    companion object {
        fun getApp(): AppComponent {
            return DaggerAppComponent.builder().build()
        }
    }
}