package com.example.shelter.app.di

import android.content.Context
import com.example.shelter.presentation.log_in_app.view.LogInAppActivity
import com.example.shelter.presentation.storage.LoggedUserProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: LogInAppActivity)

    fun build(): AppComponent

    fun getPreferences(): LoggedUserProvider

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(context: Context): Builder
        fun build(): AppComponent
    }
}