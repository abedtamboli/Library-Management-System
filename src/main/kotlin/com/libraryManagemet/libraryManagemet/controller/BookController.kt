package com.libraryManagemet.libraryManagemet.controller

import com.libraryManagemet.libraryManagemet.entity.Book
import com.libraryManagemet.libraryManagemet.services.BookServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/book")
class BookController(

    @Autowired
    val bookServices : BookServices

) {


    @PostMapping("/")
    fun addBook(@RequestBody book: Book): ResponseEntity<Book> {
        return try {
            val newBook = bookServices.addBook(book)
            ResponseEntity.of(Optional.of(newBook))
        }
        catch (e : Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @GetMapping("/{id}")
    fun getBook(@PathVariable id : Int): ResponseEntity<Book> {
        return try {
            val book = bookServices.getBook(id)
            if(book == null)
                ResponseEntity.status(HttpStatus.NOT_FOUND).build()
            else
                ResponseEntity.of(Optional.of(book))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @GetMapping("/")
    fun getBook(): ResponseEntity<List<Book>> {
        return try {
            val books = bookServices.getBook()
            println(books)
            if(books.isEmpty())
                ResponseEntity.status(HttpStatus.NOT_FOUND).build()
            else
                ResponseEntity.of(Optional.of(books))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }


    @GetMapping("/page/{num}/{active}")
    fun getBook(@PathVariable num: Int, @PathVariable active : Boolean): ResponseEntity<Page<Book>> {
        return try {
            val page = bookServices.getBook(active, PageRequest.of(num, 7))
            ResponseEntity.of(Optional.of(page))
        }
        catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }


    @PutMapping("/count/{id}/{cnt}")
    fun updateCount(@PathVariable id: Int, @PathVariable cnt: Int) : ResponseEntity<Book> {
        return  try{
            val book = bookServices.getBook(id)
            if(book == null)
                ResponseEntity.status(HttpStatus.NOT_FOUND).build()
            else if(book.total < cnt || cnt < 0 || !book.active)
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
            else {
                book.available = cnt
                ResponseEntity.of(Optional.of(bookServices.addBook(book)))
            }
        }
        catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }


    @PutMapping("/info/{id}")
    fun updateInfo(@PathVariable id: Int, @RequestBody book: Book) : ResponseEntity<Book> {
        return  try{
            val dbook = bookServices.getBook(id)
            if(dbook == null)
                ResponseEntity.status(HttpStatus.NOT_FOUND).build()
            else {
                if(!book.name.isEmpty())
                    dbook.name = book.name
                if(!book.author.isEmpty())
                    dbook.author = book.author
                if(!book.type.isEmpty())
                    dbook.type = book.type
                ResponseEntity.of(Optional.of(bookServices.addBook(dbook)))
            }
        }
        catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }
}