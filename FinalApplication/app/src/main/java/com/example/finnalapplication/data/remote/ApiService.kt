package com.example.finnalapplication.data.remote

import com.example.finnalapplication.data.model.ApiResponse
import com.example.finnalapplication.data.model.Task
import com.example.finnalapplication.data.model.TaskListResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("tasks")
    suspend fun getTasks(): TaskListResponse

    @GET("task/{id}")
    suspend fun getTaskDetail(
        @Path("id") id: Int
    ): ApiResponse<Task>

    @DELETE("task/{id}")
    suspend fun deleteTask(
        @Path("id") id: Int
    ): ApiResponse<Unit>
}
