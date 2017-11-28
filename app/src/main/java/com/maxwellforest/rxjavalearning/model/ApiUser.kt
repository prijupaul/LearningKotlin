package com.maxwellforest.rxjavalearning.model

/**
 * Created by priju on 25/11/17.
 */
class ApiUser {
    var id: Long = 0
    lateinit var firstname: String
    lateinit var lastname: String

    override fun toString(): String {
        return "ApiUser{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}'
    }
}


