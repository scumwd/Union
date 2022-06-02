package com.example.data.models

class UserCloudData {
    private var userId: String = ""
    private var email: String = ""
    private var firstName: String = ""
    private var lastName: String = ""

    fun UserCloudData() {}

    fun UserCloudData(userId: String, email: String, firstName: String, lastName: String) {
        this.userId = userId
        this.email = email
        this.firstName = firstName
        this.lastName = lastName
    }

    fun getUserId(): String {
        return userId;
    }

    fun getEmail(): String {
        return email;
    }

    fun getFirstName(): String {
        return firstName;
    }

    fun getLastName(): String {
        return lastName;
    }
}