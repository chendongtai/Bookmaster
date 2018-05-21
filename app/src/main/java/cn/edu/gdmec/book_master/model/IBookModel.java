package cn.edu.gdmec.book_master.model;





public interface IBookModel {
    void loadBook(String url, ILoadListener loadListener);
}