package me.apqx.demo.widget.recycler.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import me.apqx.demo.LogUtil;
import me.apqx.demo.R;


/**
 * 可以设置多个ItemDecoration，每一个都会执行
 */
public class ItemDecoration extends RecyclerView.ItemDecoration {

    private final OutAdapter adapter;
    private final Context context;
    private TextView view;

    private String dataStr;

    private Bitmap bitmap;

    private int lastX, lastY;
    private int lastWidth, lastHeight;

    public ItemDecoration(Context context, OutAdapter adapter) {
        super();
        this.adapter = adapter;
        this.context = context;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        // 当滑动屏幕，View移动时执行，每移动一点点，就会执行一次，执行的非常频繁
        // onDraw和onOverDraw针对的是RecyclerView本身
        // getItemOffsets针对的是每一个itemView

        // 在itemView下层绘制
        LogUtil.INSTANCE.d("onDraw");

        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        // 当前可见的第一个Child
        View firstChild = parent.getChildAt(0);

        LogUtil.INSTANCE.d(firstChild.getTop() + ":" + firstChild.getBottom());

//        int currentX = left;
//        int currentY = (int) (ViewCompat.getTranslationY(firstChild));
//
//        if (bitmap != null) {
//            lastX = currentX;
//            lastY = currentY;
//
//            LogUtil.INSTANCE.d("lastXY = " + lastX + " : " + lastY);
//            c.drawBitmap(bitmap, lastX, lastY, null);
//        }
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        c.drawCircle(0, 0, 500, paint);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        // 当滑动屏幕，View移动时执行，每移动一点点，就会执行一次，执行的非常频繁
        // 在ItemView上层绘制
        LogUtil.INSTANCE.d("onDrawOver");
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        // 当有一个新的View滑入可见范围时执行
        // 此View在Adapter的List里的位置
        int position = parent.getChildAdapterPosition(view);
        LogUtil.INSTANCE.d("getItemOffsets psoition = " + position);
        int listCount = parent.getAdapter().getItemCount();
        if (position == 0 || position == 5) {
            // 给指定位置的元素，设置上边距
            outRect.set(0, 300, 0, 0);
        }
    }

    public void setData(String dataStr) {
        this.dataStr = dataStr;
        view = (TextView) LayoutInflater.from(context).inflate(R.layout.item_color, null, false);

        view.setText(dataStr);

        view.setDrawingCacheEnabled(true);
        view.measure(View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.AT_MOST)
                , View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.AT_MOST));
        lastWidth = view.getMeasuredWidth();
        lastHeight = view.getMeasuredHeight();

        view.layout(0, 0, lastWidth, lastHeight);
        bitmap = view.getDrawingCache();

        adapter.notifyDataSetChanged();
    }

    public boolean isInArea(float x, float y) {
        if (bitmap == null) return false;
        Rect rect = new Rect(lastX, lastY, lastX + lastWidth, lastY + lastHeight);
        return rect.contains((int)x, (int)y);
    }
}
