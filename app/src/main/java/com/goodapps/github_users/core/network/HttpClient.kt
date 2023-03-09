package com.goodapps.github_users.core.network

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

const val BASE_URL = "https://api.github.com/"

internal fun createHttpClient(): HttpClient {
    return HttpClient() {
        expectSuccess = true
        defaultRequest {
            url(BASE_URL)
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
        install(ContentNegotiation) {
            json(
                Json {
                    coerceInputValues = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }
}
