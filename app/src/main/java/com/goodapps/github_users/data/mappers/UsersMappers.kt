package com.goodapps.github_users.data.mappers

import com.goodapps.github_users.data.models.DetailedUserItemDto
import com.goodapps.github_users.data.models.UserItemDto
import com.goodapps.github_users.domain.models.User
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun UserItemDto.toDomain() = User(
    id = this.id,
    avatarUrl = this.avatarUrl,
    login = this.login
)

fun DetailedUserItemDto.toDomain() = User(
    id = this.id,
    avatarUrl = this.avatarUrl,
    login = this.login,
    name = this.name,
    email = this.email,
    followers = this.followers,
    following = this.following,
    createdAt = this.createdAt?.toDate()?.toddMMyyy()
)

fun String.toDate(): Date? {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    return try {
        dateFormat.parse(this)
    } catch (e: ParseException) {
        null
    }
}

fun Date.toddMMyyy() = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(this)