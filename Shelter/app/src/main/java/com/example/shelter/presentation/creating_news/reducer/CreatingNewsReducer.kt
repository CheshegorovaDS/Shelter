package com.example.shelter.presentation.creating_news.reducer

import io.reactivex.disposables.CompositeDisposable

class CreatingNewsReducer : ICreatingNewsReducer {

    private val dispose = CompositeDisposable()

    override fun clearDispose() = dispose.clear()

}