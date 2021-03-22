package com.cakrasuryainti.panther.di

import android.app.Application
import androidx.room.Room
import com.cakrasuryainti.panther.db.PanelReportDao
import com.cakrasuryainti.panther.db.PantherDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    /**
     * Provides DB instance.
     */
    @Provides
    fun provideDb(app: Application): PantherDB = Room
        .databaseBuilder(app, PantherDB::class.java, "caniuse.db")
//        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun providePanelReportDao(db: PantherDB): PanelReportDao = db.panelReportDao()
}