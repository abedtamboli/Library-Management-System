package com.libraryManagemet.libraryManagemet.services

import com.libraryManagemet.libraryManagemet.entity.User
import com.libraryManagemet.libraryManagemet.repo.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class UserServices (

    @Autowired
    var userRepo : UserRepo
) {

    fun addUser(user: User): User = userRepo.save(user)

    fun getUserPage(status: Boolean,page: Int) : Page<User> = userRepo.findByStatus(status, PageRequest.of(page, 7))

    fun getUserList(status: Boolean) : List<User> = userRepo.findByStatus(status)

    fun getUser(id : Int) : User? = userRepo.findById(id).get()
}