package com.anfly.anflyshop.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class MsgTextView extends androidx.appcompat.widget.AppCompatTextView {

    private Paint mPaint, textPaint;

    private int msgCount;

    //原点偏移量，半径
    private float x, y, f = 10, r = 30;

    //是否已经初始化过
    private boolean init = false;

    public MsgTextView(Context context) {

        super(context);

        initMsgTextView();

    }

    public MsgTextView(Context context, AttributeSet attrs) {

        super(context, attrs);

        initMsgTextView();

    }

    public MsgTextView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

        initMsgTextView();

    }

    private void initMsgTextView() {

        setPadding(0, (int) (r - f), 0, 0);

        initPaint();

    }

    public void setMsg(int count) {

        msgCount = count;

        postInvalidate();

    }

    private void initPaint() {

        if (mPaint == null) {

            mPaint = new Paint();

            mPaint.setAntiAlias(true);

            mPaint.setColor(Color.parseColor("#FE0264"));

            textPaint = new Paint();

            textPaint.setColor(Color.parseColor("#FFFFFF"));

            textPaint.setTextAlign(Paint.Align.CENTER);

            textPaint.setTextSize(r);

            textPaint.setAntiAlias(true);

        }

    }

    private void drawValue(Canvas canvas) {

        if (msgCount > 0) {

            canvas.drawCircle(x, y, r, mPaint);

            Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();

            int baseline = (int) (((r * 2) - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top);

            canvas.drawText(String.valueOf(msgCount), x, baseline, textPaint);

        }

    }

    @Override

    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        if (!init) {

            Drawable[] n = getCompoundDrawables();

            Drawable top = n[1];

            if (top != null) {

                int w = top.getBounds().width();

                x = (getWidth() / 2 + w / 2) - 10;

                y = getPaddingTop() + 10;

                init = true;

            }

        }

        drawValue(canvas);
    }
}
