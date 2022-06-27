package com.example.androidcoursework.domain.api

class ApiProvider(private val client: RetrofitClient) {
    private val baseUrl = "https://finalspaceapi.com/api/v0/"

    fun getApi(): ApiService =
        client.getClient(baseUrl)
            .create(ApiService::class.java)
}