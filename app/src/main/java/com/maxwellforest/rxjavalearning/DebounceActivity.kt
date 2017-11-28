package com.maxwellforest.rxjavalearning

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.maxwellforest.rxjavalearning.constants.AppConstants
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.concurrent.TimeUnit

/**
 * Created by priju on 25/11/17.
 */
class DebounceActivity : AppCompatActivity() {

    val TAG = DebounceActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        btn.setOnClickListener { doSomeWorkWithRange() }
    }

    private fun doSomeWork() {
        getObservable()
                .debounce (500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver())
    }

    private fun getObserver() : Observer<Int> {
        return object : Observer<Int> {
            override fun onNext(it : Int) {
                textView.append(" onNext: $it")
                textView.append(AppConstants.LINE_SEPARATOR)
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed)
            }

            override fun onError(e: Throwable) {
                textView.append(" onError : " + e.message)
                textView.append(AppConstants.LINE_SEPARATOR)
                Log.d(TAG, " onError : " + e.message)
            }

            override fun onComplete() {
                textView.append(" onComplete")
                textView.append(AppConstants.LINE_SEPARATOR)
                Log.d(TAG, " onComplete")
            }
        }
    }

    private fun getObservable(): Observable<Int> {
        return Observable.create<Int> {
            it ->
            it.onNext(1)
            Thread.sleep(100)
            it.onNext(2)
            Thread.sleep(305)
            it.onNext(3)
            Thread.sleep(100)
            it.onNext(4)
            Thread.sleep(605)
            it.onNext(5)
            Thread.sleep(510)
            it.onComplete()
        }
    }

    private fun doSomeWorkWithArray() {

        getObservableEmitFirst()
                .subscribeOn(Schedulers.io())
                .flatMapIterable { it }
                .zipWith(Observable.interval(3,TimeUnit.SECONDS),
                        BiFunction<String, Long, String> { t1, _ -> t1})
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserverEmitFirst())
    }

    private fun doSomeWorkWithRange() {
        getObservableRange()
                .subscribeOn(Schedulers.io())
                  .zipWith(Observable.interval(5, TimeUnit.SECONDS),
                        BiFunction<Int,Long,Int> { t1, t2 -> t1 })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserverRange())

    }

    private fun getObserverRange(): Observer<Int> {
        return object : Observer<Int> {
            override fun onError(e: Throwable) {
                textView.append(" onError")
                textView.append(AppConstants.LINE_SEPARATOR)
            }

            override fun onComplete() {
                textView.append(" onComplete")
                textView.append(AppConstants.LINE_SEPARATOR)
            }

            override fun onSubscribe(d: Disposable) {
                textView.append(" onSubscribe")
                textView.append(AppConstants.LINE_SEPARATOR)
            }

            override fun onNext(t: Int) {
                textView.append(" onNext: $t")
                textView.append(AppConstants.LINE_SEPARATOR)
            }
        }
    }

    fun getStringItem( a : String) : Observable<String>{
        return  Observable.just(a)
    }

    private fun getObservableEmitFirst(): Observable<List<String>> {
        return Observable.just(listOf("one", "two", "three", "four", "five", "six"))
    }

    private fun getObservableRange() : Observable<Int> {
        return Observable.range(0,5)
    }

    private fun getObserverEmitFirst() : Observer<String> {
        return object : Observer<String> {
            override fun onNext(it : String) {
                textView.append(" onNext: $it")
                textView.append(AppConstants.LINE_SEPARATOR)
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed)
            }

            override fun onError(e: Throwable) {
                textView.append(" onError : " + e.message)
                textView.append(AppConstants.LINE_SEPARATOR)
                Log.d(TAG, " onError : " + e.message)
            }

            override fun onComplete() {
                textView.append(" onComplete")
                textView.append(AppConstants.LINE_SEPARATOR)
                Log.d(TAG, " onComplete")
            }
        }
    }

}