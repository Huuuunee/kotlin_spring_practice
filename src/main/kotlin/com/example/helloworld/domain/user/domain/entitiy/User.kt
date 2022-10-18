package com.example.helloworld.domain.user.domain.entitiy

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User (
    val email: String,

    val password : String,

    val name : String,
){
    @Id
    @GeneratedValue
    val id = 0;
}