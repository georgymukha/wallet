package kz.mukha.wallet.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.csrf.CookieCsrfTokenRepository

@Configuration
@EnableWebSecurity
class SecurityConfig() {
    private val userDetailsService: CustomUserDetailsService = CustomUserDetailsService()

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf {
                it.disable()
            }
            .cors {
                it.disable()
            }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/").permitAll() // Разрешаем доступ к корневому URL
                    .requestMatchers("/login").permitAll() // Разрешаем доступ к URL для авторизации
                    .requestMatchers("/register").permitAll() // Разрешаем доступ к URL для регистрации
                    .anyRequest().authenticated() // Требуем аутентификацию для остальных запросов
            }
            .formLogin {
                // Включаем форму входа (по умолчанию на /login)
            }
            .logout {
                // Включаем логаут (по умолчанию на /logout)
            }
        return http.build()
    }

    fun configure(http: HttpSecurity) {
        http
            .csrf {
                it.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // Используем Cookie для CSRF токена
            }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/").permitAll() // Разрешаем доступ к корневому URL
                    .requestMatchers("/login").permitAll() // Разрешаем доступ к URL для авторизации
                    .requestMatchers("/register").permitAll() // Разрешаем доступ к URL для регистрации
                    .anyRequest().authenticated() // Требуем аутентификацию для остальных запросов
            }
            .formLogin {
                // Включаем форму входа (по умолчанию на /login)
            }
            .logout {
                // Включаем логаут (по умолчанию на /logout)
            }
    }
}