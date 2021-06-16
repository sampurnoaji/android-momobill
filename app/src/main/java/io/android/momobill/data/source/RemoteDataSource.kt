package io.android.momobill.data.source

import io.android.momobill.vo.HttpResult
import io.android.momobill.vo.LoadResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class RemoteDataSource {

    companion object {
        private const val JSON_KEY_MESSAGE = "message"
        private const val JSON_KEY_STATUS = "status"
    }

    open suspend fun <T> call(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): LoadResult<T> {
        return withContext(dispatcher) {
            try {
                LoadResult.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        val result = when (throwable.code()) {
                            in 400..451 -> parseHttpError(throwable)
                            in 500..599 -> error(
                                HttpResult.SERVER_ERROR,
                                throwable.code(),
                                "Server error",
                                null
                            )
                            else -> error(
                                HttpResult.NOT_DEFINED,
                                throwable.code(),
                                "Undefined error",
                                null
                            )
                        }
                        result
                    }
                    is UnknownHostException -> error(
                        HttpResult.NO_CONNECTION,
                        null,
                        "No internet connection",
                        null
                    )
                    is SocketTimeoutException -> error(
                        HttpResult.TIMEOUT,
                        null,
                        "Slow connection",
                        null
                    )
                    is IOException -> error(HttpResult.BAD_RESPONSE, null, throwable.message, null)
                    else -> error(HttpResult.NOT_DEFINED, null, throwable.message, null)
                }
            }
        }
    }

    private fun error(
        cause: HttpResult,
        code: Int?,
        errorMessage: String?,
        errorStatus: String?
    ): LoadResult.Error {
        return LoadResult.Error(cause, code, errorMessage, errorStatus)
    }

    private fun parseHttpError(throwable: HttpException): LoadResult<Nothing> {
        return try {
            val errorResponse =
                throwable.response()?.errorBody()?.string() ?: "Unknown HTTP error body"
            val errorMessage = errorResponse.getOrNull(JSON_KEY_MESSAGE)
            val errorStatus = errorResponse.getOrNull(JSON_KEY_STATUS)
            error(HttpResult.CLIENT_ERROR, throwable.code(), errorMessage, errorStatus)
        } catch (exception: Exception) {
            error(HttpResult.CLIENT_ERROR, throwable.code(), exception.localizedMessage, null)
        }
    }

    private fun String.getOrNull(key: String): String? {
        return try {
            val json = JSONObject(this)
            json.getString(key)
        } catch (e: JSONException) {
            e.printStackTrace()
            null
        }
    }
}
