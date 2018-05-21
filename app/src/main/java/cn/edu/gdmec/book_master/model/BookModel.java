package cn.edu.gdmec.book_master.model;


import cn.edu.gdmec.book_master.BookBean;
import cn.edu.gdmec.book_master.utils.OkHttpUtils;

public class BookModel implements IBookModel {

    @Override
    public void loadBook(String url, final ILoadListener loadListener) {
        OkHttpUtils.ResultCallback resultCallback = new OkHttpUtils.ResultCallback() {
            @Override
            public void getBook(BookBean bookBean) {
                loadListener.onSuccess(bookBean);
            }

            @Override
            public void onFailure(Exception e) {
                loadListener.onFailure(e);
            }
        };
        OkHttpUtils.getResultCallback(url, resultCallback);

    }
}