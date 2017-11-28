package com.maxwellforest.rxjavalearning

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_kotlin.*
import kotlinx.android.synthetic.main.content_kotlin.*

class KotlinActivity : AppCompatActivity() {

    val TAG = KotlinActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        btn_do_work.setOnClickListener {
            performWorkAssociateBy()
        }
    }

    private fun performWorkAssociateBy() {
        val set = setOf<String>("one","two","three","four","five")
        set.associateBy { it  }
                .any { Log.d(TAG,it.key)
                    false
                }
    }

    private fun perform() {
        val set = setOf<String>("one","two","three","four","five")
        set.asIterable()
    }


}
