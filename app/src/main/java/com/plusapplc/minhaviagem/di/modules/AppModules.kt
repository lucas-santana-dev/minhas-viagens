package com.plusapplc.minhaviagem.di.modules

import android.content.Context
import androidx.room.Room
import com.plusapplc.minhaviagem.database.AppDatabase
import com.plusapplc.minhaviagem.viewmodels.HomeScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::HomeScreenViewModel)
}

val viewModelModule = module {
  viewModel { HomeScreenViewModel(get(),get()) }
}
val databaseModule = module {
  single {
    Room.databaseBuilder(
      get<Context>(),
      AppDatabase::class.java,
      "appdatabase.db"
    ).build()
  }

  single { get<AppDatabase>().viagemDao() }
  single { get<AppDatabase>().despesasDao() }
  single { get<AppDatabase>().hospedagemDao() }
  single { get<AppDatabase>().destinoDao() }
  single { get<AppDatabase>().atividadeDao() }
}