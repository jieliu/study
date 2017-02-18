package com.tianma.spring.rest.book;

import com.tianma.spring.rest.domain.Book;

/**
 * Created by fiboliu on 16-11-10.
 */
public interface BookRestController {
    Book getBookByName(String name);
}
