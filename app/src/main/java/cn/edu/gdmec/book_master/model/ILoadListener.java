package cn.edu.gdmec.book_master.model;

import cn.edu.gdmec.book_master.BookBean;

public interface ILoadListener {
    void onSuccess(BookBean bean);

    void onFailure(Exception e);
}