package com.libraryManagemet.libraryManagemet.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Book (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int,

    var name: String,

    var type : String,

    var author : String,

    var total: Int,

    var available: Int,

    var active: Boolean

)