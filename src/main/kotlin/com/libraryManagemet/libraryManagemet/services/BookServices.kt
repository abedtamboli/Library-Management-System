package com.libraryManagemet.libraryManagemet.services

import com.libraryManagemet.libraryManagemet.entity.Book
import com.libraryManagemet.libraryManagemet.repo.BookRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


@Service
class BookServices (
    @Autowired
    val bookRepo: BookRepo
){
    fun getBook(id: Int) : Book? = bookRepo.findById(id).get()

    fun getBook() : List<Book> = bookRepo.findByActive(true)

    fun getBook(active: Boolean, pageable: Pageable) : Page<Book> = bookRepo.findByActive(active, pageable)

    fun addBook(book: Book) : Book = bookRepo.save(book)
}