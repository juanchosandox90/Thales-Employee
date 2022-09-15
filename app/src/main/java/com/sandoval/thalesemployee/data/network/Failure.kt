package com.sandoval.thalesemployee.data.network

sealed class Failure {

    object NetworkConnection : Failure()
    object DataError : Failure()
    object ServerError : Failure()

    //This class can be extended to be able to get specific errors
    data class FeatureFailure(
        val errorBody: String
    ) : Failure()
}