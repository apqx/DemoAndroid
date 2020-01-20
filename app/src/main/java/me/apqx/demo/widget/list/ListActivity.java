package me.apqx.demo.widget.list;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;

import me.apqx.demo.old.tools.LogUtil;
import me.apqx.demo.R;

public class ListActivity extends Activity {
    private ListView listView;
    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private List<Student> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = findViewById(R.id.lv_top);
        recyclerView = findViewById(R.id.rv_bottom);

        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Random random = new Random(System.currentTimeMillis());
            list.add(new Student(random.nextInt(100), String.valueOf(i)));
        }

        listAdapter = new ListAdapter(this, R.layout.item_list, list);
        listView.setAdapter(listAdapter);

        listAdapter.notifyDataSetChanged();


        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(R.layout.item_list);
        recyclerAdapter.setData(list);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        RecyclerView.LayoutManager layoutManager = new FlexboxLayoutManager(this, FlexDirection.ROW);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

        recyclerAdapter.setOnItemClickListener(student -> {
            // item被点击时回调
        });

        // 通知所有数据刷新
        recyclerAdapter.notifyDataSetChanged();
        // 通知指定item数据刷新
        recyclerAdapter.notifyItemChanged(10);
        // 通知指定范围的item数据刷新
        recyclerAdapter.notifyItemRangeChanged(0, 2);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN
                | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT,
                ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                if (toPosition == 0 || fromPosition == 0) {
                    return false;
                }
                if (fromPosition < toPosition) {
                    //分别把中间所有的item的位置重新交换
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(list, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(list, i, i - 1);
                    }
                }
                recyclerAdapter.notifyItemMoved(fromPosition, toPosition);
                Log.d("apqx", "onMoved = " + list);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Log.d("apqx", "onSwiped = " + list);
                int position = viewHolder.getAdapterPosition();
                list.remove(position);
                recyclerAdapter.notifyItemRemoved(position);
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    //滑动时改变Item的透明度
                    final float alpha = 1 - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
                    viewHolder.itemView.setAlpha(alpha);
                    viewHolder.itemView.setTranslationX(dX);
                }
            }

            @Override
            public boolean isItemViewSwipeEnabled() {
                return false;
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_list_refresh:
                LogUtil.INSTANCE.d("click list refresh");
                if (!list.isEmpty())
                    list.remove(list.size() - 1);
                listAdapter.notifyDataSetChanged();
                break;
        }
    }
}
