package com.duongdt3.data.exception

import java.io.IOException

class ServerException(val serverErrorCode: Int, val serverErrorMsg: String?) : IOException("$serverErrorCode $serverErrorMsg")