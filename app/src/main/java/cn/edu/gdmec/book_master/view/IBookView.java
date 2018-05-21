package cn.edu.gdmec.book_master.view;


import cn.edu.gdmec.book_master.BookBean;

public interface IBookView {
    void showProgress();
    void hideProgress();
    void showBookData(BookBean bookBean);
    void showLoadFailMsg(Exception e);
}
