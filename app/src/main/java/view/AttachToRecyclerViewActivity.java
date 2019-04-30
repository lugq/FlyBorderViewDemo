package view;

import android.app.Instrumentation;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.jasonchen.flyborderviewdemo.R;

import java.util.ArrayList;
import java.util.List;

import adapter.MenuAdapter;
import custom.FlyBorderView;
import custom.TvRecyclerView;
import model.Movie;

/**
 * Created by JasonChen on 2017/9/26.
 */

public class AttachToRecyclerViewActivity extends BaseActivity {
    private TvRecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private FlyBorderView flyBorder;
    private LinearLayout parentView;

    int i = 0;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_attachtorecyclerview);
        recyclerView =  findViewById(R.id.Main_RecyclerView);
        flyBorder =  findViewById(R.id.FlyBorder);
        parentView =  findViewById(R.id.ParentView);

        final Button btn_right = findViewById(R.id.btn_right);
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = i+1;
                recyclerView.getChildAt(i).setFocusable(true);
                recyclerView.getChildAt(i).setFocusableInTouchMode(true);
                recyclerView.getChildAt(i).requestFocus();
            }
        });
    }

    @Override
    protected void setData() {
        List<Movie> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new Movie("Sakura" + i, "ZZZZ"));
        }
        menuAdapter = new MenuAdapter(list);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));
        recyclerView.setAdapter(menuAdapter);

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                flyBorder.attachToRecyclerView(recyclerView);
                setFocusView(recyclerView.getChildAt(0));
            }
        });

    }

    private void setFocusView(View view) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    @Override
    protected void setListener() {

    }
}
