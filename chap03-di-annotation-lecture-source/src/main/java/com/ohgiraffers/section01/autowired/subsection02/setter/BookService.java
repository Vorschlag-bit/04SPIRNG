package com.ohgiraffers.section01.autowired.subsection02.setter;

import com.ohgiraffers.section01.common.BookDAO;
import com.ohgiraffers.section01.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* 설명. 같은 component scan 범위 안에 같은 타입에 같은 이름으로 두 개 이상의 bean이 공존할 순 없다.
*  (다른 이름으로 저장) */
@Service("bookServiceSetter")
public class BookService {
    //    BookDAO bookDAO = new BookDAOImpl();

    private BookDAO bookDAO;

    public BookService() {
    }

    // 얘를 콩으로 주입받고 싶으면 이 setter를 반드시 주입받아야 한다.
    // 하지만 기본적으로 setter 자체도 private을 무시하는 것이기 때문에 안티패턴에 속한다.
    //
    @Autowired
    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public List<BookDTO> findAllBook() {
        return bookDAO.findAllBook();
    }

    public BookDTO searchBookSeqence(int sequence) {
        return bookDAO.searchBookBySequence(sequence);
    }
}