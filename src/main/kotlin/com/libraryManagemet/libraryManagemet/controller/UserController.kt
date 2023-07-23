package com.libraryManagemet.libraryManagemet.controller

import com.libraryManagemet.libraryManagemet.entity.User
import com.libraryManagemet.libraryManagemet.services.UserServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/user")
class UserController (
    @Autowired
    val userServices: UserServices
){

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Int): ResponseEntity<User> {
        return try {
            val user = userServices.getUser(id)
            if(user == null || !user.status)
                ResponseEntity.status(HttpStatus.NOT_FOUND).build()
            else
                ResponseEntity.of(Optional.of(user))
        } catch (e : Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @GetMapping("/list/{status}")
    fun getUserList(@PathVariable status: Boolean): ResponseEntity<List<User>> {
        return try {
            val users = userServices.getUserList(status)
            ResponseEntity.of(Optional.of(users))
        } catch (e : Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @PostMapping("/")
    fun addUser(@RequestBody  user: User): ResponseEntity<User>{
        return try {
            val saved = userServices.addUser(user)
            ResponseEntity.of(Optional.of(saved))
        } catch (e : Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @GetMapping("/{num}/{status}")
    fun getUserPage(@PathVariable status: Boolean, @PathVariable num : Int) : ResponseEntity<Page<User>>{
        return try{
            val page = userServices.getUserPage( status, num)
            ResponseEntity.of(Optional.of(page))
        }
        catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }
}