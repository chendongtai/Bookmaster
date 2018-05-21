package cn.edu.gdmec.book_master.utils;


import com.google.gson.Gson;

import cn.edu.gdmec.book_master.BookBean;

public class JsonUtils {

    public static BookBean getBook(String res){
        Gson gson = new Gson();
        BookBean bookBean = gson.fromJson(res, BookBean.class);
        return bookBean;
    }
}
