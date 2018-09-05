package com.example.elidev.movieapp.models

import com.squareup.moshi.Json

/*object
page integer optional
result array[object]
{Movie List Result Object} optional
total_results Integer optional
total_pages integer optional*/

data class ResponseBodyWrapper<T>(
        @Json(name = "page") val page: Int,
        @Json(name = "results") val result: List<T>,
        @Json(name = "total_results") val totalResults: Int,
        @Json(name = "total_pages") val totalPages: Int
) {
}