package cn.edu.gdmec.book_master.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.TimerTask;

import cn.edu.gdmec.book_master.BookBean;
import cn.edu.gdmec.book_master.R;
import cn.edu.gdmec.book_master.presenter.BookPresenter;


public class MainActivity extends Activity implements IBookView, View.OnClickListener{

    private TextView tvBook;
    private ProgressDialog progressDialog;
    private BookPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_book).setOnClickListener(this);
        tvBook = (TextView) findViewById(R.id.tv_book);
        presenter = new BookPresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_book:
                presenter.loadBook();
                break;
        }
    }

    @Override
    public void showProgress() {
        if (progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        progressDialog = ProgressDialog.show(MainActivity.this,"", "正在获取");
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }



    @Override
    public void showBookData(final BookBean bookBean) {
        runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                if(bookBean.getStart() == 304){
                    Toast.makeText(MainActivity.this,"失败了",Toast.LENGTH_SHORT).show();
                }else{
                    tvBook.setText("作者：" + bookBean.getBooks().get(0).getAuthor() + "\n图书标题: " + bookBean.getBooks().get(0).getSubtitle() );
                }
            }
        });
    }

    @Override
    public void showLoadFailMsg(final Exception e) {
        runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                tvBook.setText("加载数据失败：" + e.toString());
            }
        });
    }
}
