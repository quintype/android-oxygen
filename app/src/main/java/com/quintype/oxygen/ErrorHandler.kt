package com.quintype.oxygen

interface ErrorHandler {
    fun onAPIFailure()
    fun onAPISuccess()
}