package com.example.phoaching.domain.manager

import com.example.phoaching.domain.models.ProfileUI
import com.russhwolf.settings.Settings
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface AuthManager {
    var isAuthorized: Boolean
    var userId: String?
    var token: String?
    var profile: ProfileUI?

    fun exit()
    fun setUser(userId: String, token: String)


}

class AuthManagerImpl : AuthManager, KoinComponent {

    private val settings by inject<Settings>()
    private val json by inject<Json>()



    override var token: String?
        get() = if (settings.getString(TOKEN, "").isBlank()) null else settings.getString(TOKEN, "")
        set(value) {
            settings.putString(TOKEN, value.orEmpty())
        }

    override var profile: ProfileUI?
        get() = try {
            val temp = settings.getString(PROFILE, "")
            if (temp.isBlank()) null
            else json.decodeFromString(ProfileUI.serializer(), temp)
        } catch (e: Exception) {
            null
        }
        set(value) {
            if (value != null) {
                val profileJson = json.encodeToString(ProfileUI.serializer(), value)
                settings.putString(PROFILE, profileJson)
            } else {
                settings.remove(PROFILE)
            }
        }

    override var isAuthorized: Boolean
        get() = settings.getBoolean(IsAuthorized, false)
        set(value) {
            settings.putBoolean(IsAuthorized, value)
        }
    override var userId: String?
        get() = if (settings.getString(UserId, "").isBlank()) null else settings.getString(
            UserId,
            ""
        )
        set(value) {
            settings.putString(UserId, value.orEmpty())
        }


    override fun exit() {
        token = null
        isAuthorized = false
        profile = null
    }

    override fun setUser(
        userId: String,
        token: String,
    ) {
        this.userId = userId
        this.token = token
        this.isAuthorized = true
    }

    companion object {
        private const val PREFIX = "AuthManager"
        private const val IsAuthorized = PREFIX + "IsAuthorized"
        private const val PHONE = PREFIX + "PHONE"
        private const val TOKEN = PREFIX + "TOKEN"
        private const val UserId = PREFIX + "USERID"
        private const val PROFILE = PREFIX + "PROFILE"
        private const val USERNAME = PREFIX + "USERNAME"
    }

}