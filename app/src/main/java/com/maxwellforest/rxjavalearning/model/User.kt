package com.maxwellforest.rxjavalearning.model

/**
 * Created by priju on 25/11/17.
 */

class User {
    var id: Long = 0
    lateinit var firstname: String
    lateinit var lastname: String
    var isFollowing: Boolean = false

    fun User(apiUser: ApiUser){
        this.id = apiUser.id
        this.firstname = apiUser.firstname
        this.lastname = apiUser.lastname
    }

    override fun toString(): String {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", isFollowing=" + isFollowing +
                '}'
    }
}