package com.maxwellforest.rxjavalearning

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.maxwellforest.rxjavalearning.constants.AppConstants
import com.maxwellforest.rxjavalearning.model.ApiUser
import com.maxwellforest.rxjavalearning.model.User
import com.maxwellforest.rxjavalearning.utils.utils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


/**
 * Created by priju on 25/11/17.
 */
class MapActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        btn.setOnClickListener { doSomeWork() }
    }

    fun doSomeWork() {
        getObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { Observable.fromIterable(it) }
                .filter { it -> it.firstname.contains("amit",true)}
                //.map { utils.convertApiUserListToUserList(it) }
//                .subscribe(getObserver())
                .subscribe { println(it)}
    }

    private fun getConsumer1(): Consumer<ApiUser> {
        return object : Consumer<ApiUser> {
            override fun accept(userList: ApiUser) {

                textView.append(" onNext")
                textView.append(AppConstants.LINE_SEPARATOR)

//                for (user in userList) {
                textView.append(" firstname : " + userList.firstname)
                textView.append(AppConstants.LINE_SEPARATOR)
            }
//                Log.d(TAG, " onNext : " + userList.size)
        }
    }

    private fun getConsumer(): Consumer<List<User>> {
        return object : Consumer<List<User>> {
            override fun accept(userList: List<User>) {

                textView.append(" onNext")
                textView.append(AppConstants.LINE_SEPARATOR)

                for (user in userList) {
                    textView.append(" firstname : " + user.firstname)
                    textView.append(AppConstants.LINE_SEPARATOR)
                }
                Log.d(TAG, " onNext : " + userList.size)
            }
        }
    }

    private fun getObserver(): Observer<List<User>> {

        return object : Observer<List<User>> {
            override fun onNext(userList: List<User>) {
                textView.append(" onNext")
                textView.append(AppConstants.LINE_SEPARATOR)
                for (user in userList) {
                    textView.append(" firstname : " + user.firstname)
                    textView.append(AppConstants.LINE_SEPARATOR)
                }
                Log.d(TAG, " onNext : " + userList.size)
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

    fun getObservable(): Observable<List<ApiUser>> {
        return Observable.create<List<ApiUser>> {
            if (!it.isDisposed) {
                it.onNext(utils.getApiUserList())
                it.onComplete()
            }
        }
    }


}