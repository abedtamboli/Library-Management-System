package com.libraryManagemet.libraryManagemet.repo

import com.libraryManagemet.libraryManagemet.entity.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepo: JpaRepository<User, Int> {

    fun findByStatus(status: Boolean, pageable: Pageable): Page<User>

    fun findByStatus(status: Boolean): List<User>
}