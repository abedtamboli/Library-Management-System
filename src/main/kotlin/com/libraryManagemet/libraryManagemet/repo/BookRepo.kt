package com.libraryManagemet.libraryManagemet.repo

import com.libraryManagemet.libraryManagemet.entity.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepo: JpaRepository<Book, Int>  {

    fun findByActive(active: Boolean, pageable: Pageable) : Page<Book>

    fun findByActive(active:Boolean) : List<Book>
}