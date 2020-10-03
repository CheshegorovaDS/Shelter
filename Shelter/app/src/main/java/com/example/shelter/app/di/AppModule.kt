package com.example.shelter.app.di

import android.content.Context
import android.content.SharedPreferences
import com.example.shelter.presentation.storage.LoggedUserManager
import com.example.shelter.presentation.storage.LoggedUserProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Module
    companion object {
        const val PREFS_NAME = "SHELTER_MANAGER_PREFS"

        @Provides
        @JvmStatic
        @NotNull
        @Singleton
        fun getSharedPreferences(context: Context): SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    @Binds
    abstract fun provideLoggedManager(manager: LoggedUserManager): LoggedUserProvider
}
