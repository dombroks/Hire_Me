package com.dom_broks.hireme.di

import com.dom_broks.hireme.data.FirebaseSource
import com.dom_broks.hireme.data.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseSource() : FirebaseSource {
        return FirebaseSource()
    }

    @Singleton
    @Provides
    fun provideRepository(firebaseSource: FirebaseSource) : Repository {
        return Repository(firebaseSource)
    }

}