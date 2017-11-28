package com.maxwellforest.rxjavalearning

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.maxwellforest.rxjavalearning.constants.AppConstants
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        btn.setOnClickListener { doSomeWork() }
    }

    private fun doSomeWork() {
        getObservable()
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver())
    }

    private fun getObserver(): Observer<String> {
        return object : Observer<String> {
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

            override fun onNext(t: String) {
                textView.append(" onNext : value : " + t)
                textView.append(AppConstants.LINE_SEPARATOR)
                Log.d(TAG, " onNext : value : " + t)
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed)
            }
        }
    }

    private fun getObservable(): Observable<String> =
            Observable.just("Priju", "Jacob", "Paul", "Mullasseril")
                    .doOnSubscribe { Log.d(TAG, "doOnSubscribe") }
                    .doOnComplete { Log.d(TAG, "doOnComplete") }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
