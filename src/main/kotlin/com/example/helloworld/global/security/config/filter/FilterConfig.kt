package com.example.helloworld.global.security.config.filter

import com.example.helloworld.global.security.filter.ExceptionFilter
import com.example.helloworld.global.security.filter.JwtTokenFilter
import com.example.helloworld.global.security.jwt.JwtTokenProvider
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class FilterConfig(
        private val jwtTokenProvider: JwtTokenProvider,
        private val objectMapper: ObjectMapper
): SecurityConfigurerAdapter<DefaultSecurityFilterChain?, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        val jwtTokenFilter = JwtTokenFilter(jwtTokenProvider)
        val exceptionFilter = ExceptionFilter(objectMapper)
        builder.addFilterBefore(jwtTokenFilter,UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(exceptionFilter,UsernamePasswordAuthenticationFilter::class.java)
    }
}