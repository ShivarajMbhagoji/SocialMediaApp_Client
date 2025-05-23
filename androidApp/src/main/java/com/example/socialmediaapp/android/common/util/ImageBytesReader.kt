package com.example.socialmediaapp.android.common.util

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException
import com.example.socialmediaapp.common.util.Result

class ImageBytesReader(
    private val appContext: Context,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun readImageBytes(uri: Uri): Result<ByteArray> {
        return withContext(ioDispatcher) {
            try {
                val bytes = appContext.contentResolver.openInputStream(uri)?.use {
                    it.readBytes()
                }
                bytes?.let {
                    Result.Success(it)
                } ?: Result.Error(message = Constants.READING_IMAGE_BYTES_FAILURE_MESSAGE)
            } catch (cancellationException: CancellationException) {
                throw cancellationException
            } catch (ioException: IOException) {
                Result.Error(message = ioException.message ?: Constants.UNEXPECTED_ERROR_MESSAGE)
            }catch (exc: Exception){
                Result.Error(message = exc.message ?: Constants.UNEXPECTED_ERROR_MESSAGE)
            }
        }
    }
}