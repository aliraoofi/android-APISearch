package ir.solam.apisearch.koin

import ir.solam.apisearch.model.AppDatabase
import ir.solam.apisearch.model.ItemDao
import ir.solam.apisearch.model.ItemRepositoryImpl
import ir.solam.apisearch.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.getDatabase(androidContext()) }
    fun provideItemDao(db: AppDatabase): ItemDao = db.itemDao()
    single { provideItemDao(get()) }
    single { ItemRepositoryImpl(get()) }
    viewModel { MainViewModel(get()) }
}