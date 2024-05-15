package kz.mukha.wallet.security

import kz.mukha.wallet.data.jooq.postgres.tables.Users.USERS
import kz.mukha.wallet.data.jooq.postgres.tables.records.UsersRecord
import kz.mukha.wallet.supabase.DatabaseConnector
import org.jooq.DSLContext
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService : UserDetailsService {
    private val context: DSLContext = DatabaseConnector.connect()

    override fun loadUserByUsername(username: String): UserDetails {
        val userRecord: UsersRecord = context
            .selectFrom(USERS)
            .where(USERS.USERNAME.eq(username))
            .fetchOne() ?: throw UsernameNotFoundException("User not found with username: $username")

        return User.withUsername(userRecord.username)
            .password("{noop}${userRecord.password}")
            .authorities("write", "read")
            .roles("ADMIN", "USER")
            .build()
    }

}