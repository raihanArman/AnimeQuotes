package com.example.animequotes.base.exception

import java.lang.Exception

class ApiErrorException(override val message: String?= null, val httpCode: Int?= null): Exception() {

}