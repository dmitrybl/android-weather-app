package ru.dmitry.belyaev.app.weather.presenters

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.dmitry.belyaev.app.weather.views.BaseView

abstract class BasePresenter<V: BaseView> {

    protected lateinit var view: V

    private val compositeDisposable = CompositeDisposable()

    fun subscribe(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun attach(view: V) {
        this.view = view
    }

    fun detach() {
        compositeDisposable.clear()
    }

}