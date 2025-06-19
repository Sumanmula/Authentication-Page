package com.example.firstapplication.network

import com.example.firstapplication.model.Person
import retrofit2.http.GET

interface ApiService {
    @GET("/userList")
    suspend fun getPlayers() : List<Person>
}