package com.tianma.spring.rest.book;

import com.tianma.spring.rest.domain.Book;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fiboliu on 16-11-10.
 */
@RestController
public class BookRestControllerImpl implements BookRestController {

    @Override
    @RequestMapping(path = "/v1/books", method = RequestMethod.GET)
    @ResponseBody
    public Book getBookByName(@PathVariable String name) {
        Book book = new Book();
        book.setName(name);
        book.setNumbers(10);
        return book;
    }
}
