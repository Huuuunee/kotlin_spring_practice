package com.example.helloworld.domain.email.presentation.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class EmailSendRequestDto(
    @field:NotBlank
    @field:NotNull
    val email: String
)