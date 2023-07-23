package com.libraryManagemet.libraryManagemet.entity

import javax.persistence.*

@Entity
data class User (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int=0,

    var fname : String="",

    var lname: String="",

    var email: String="",

    var password : String="",

    var status : Boolean = false

)