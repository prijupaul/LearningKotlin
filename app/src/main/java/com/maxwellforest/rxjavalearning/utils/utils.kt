package com.maxwellforest.rxjavalearning.utils

import com.maxwellforest.rxjavalearning.model.ApiUser
import com.maxwellforest.rxjavalearning.model.User
import java.util.*

/**
 * Created by priju on 25/11/17.
 */
object utils {

    fun getApiUserList(): List<ApiUser> {

        val apiUserList = ArrayList<ApiUser>()

        val apiUserOne = ApiUser()
        apiUserOne.firstname = "Amit"
        apiUserOne.lastname = "Shekhar"
        apiUserList.add(apiUserOne)

        val apiUserTwo = ApiUser()
        apiUserTwo.firstname = "Manish"
        apiUserTwo.lastname = "Kumar"
        apiUserList.add(apiUserTwo)

        val apiUserThree = ApiUser()
        apiUserThree.firstname = "Sumit"
        apiUserThree.lastname = "Kumar"
        apiUserList.add(apiUserThree)

        return apiUserList
    }

    fun convertApiUserListToUserList(apiUserList: List<ApiUser>): List<User> {

        val userList = ArrayList<User>()

        for (apiUser in apiUserList) {
            val user = User()
            user.firstname = apiUser.firstname
            user.lastname = apiUser.lastname
            userList.add(user)
        }

        return userList
    }
}