package com.maxwellforest.rxjavalearning

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.maxwellforest.rxjavalearning.constants.AppConstants
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.concurrent.TimeUnit

/**
 * Created by priju on 26/11/17.
 */
class RepeatActivity : AppCompatActivity() {

    val TAG = RepeatActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        btn.setOnClickListener { doSomeWorkWithRepeat() }
    }

    private fun doSomeWorkWithRepeat() {
        val startTimeMillis = System.currentTimeMillis()

//        getObservable()
//                .repeatWhen { completed -> completed.delay(3, TimeUnit.SECONDS) }
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(getObserverEmitFirst())
        Observable.range(0,5)
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(3, TimeUnit.SECONDS)
                .subscribe(getObserverInt())


    }

    private fun getObserverInt(): Observer<Int> {
        return object : Observer<Int> {
            override fun onNext(it: Int) {
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


    private fun getObservable(): Observable<String> = Observable.just("one", "two", "three")


    private fun getObserverEmitFirst(): Observer<String> {
        return object : Observer<String> {
            override fun onNext(it: String) {
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