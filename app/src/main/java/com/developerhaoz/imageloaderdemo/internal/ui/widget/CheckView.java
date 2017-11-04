package com.developerhaoz.imageloaderdemo.internal.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.developerhaoz.imageloaderdemo.R;

/**
 * @author Haoz
 * @date 2017/11/1.
 */

public class CheckView extends View {


    String uri = "content://media/external/file";
    public static final int UNCHECKED = Integer.MIN_VALUE;
    private static final float STROKE_WIDTH = 3.0f;
    private static final float SHADOW_WIDTH = 6.0f;
    private static final int SIZE = 48;
    private static final float STROKE_RADIUS = 11.5f;
    private static final float BG_RADIUS = 11.0f;
    private static final int CONTENT_SIZE = 16;
    private boolean mCountable;
    private boolean mChecked;
    private int mCheckNum;
    private Paint mStrokePaint;
    private Paint mBackgroundPaint;
    private TextPaint mTextPaint;
    private Paint mShadowPaint;
    private Drawable mCheckDrawable;
    private float mDensity;
    private Rect mCheckRect;
    private boolean mEnabled = true;


    public CheckView(Context context) {
        super(context);
        init(context);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int sizeSpec = MeasureSpec.makeMeasureSpec((int) (SIZE * mDensity), MeasureSpec.EXACTLY);
        super.onMeasure(sizeSpec, sizeSpec);
    }

    private void init(Context context){
        mDensity = context.getResources().getDisplayMetrics().density;

        mStrokePaint = new Paint();
        mStrokePaint.setAntiAlias(true);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        mStrokePaint.setStrokeWidth(STROKE_WIDTH * mDensity);
        TypedArray ta = getContext().getTheme().obtainStyledAttributes(new int[]{R.attr.item_checkCircle_borderColor});
        int defaultColor = ResourcesCompat.getColor(
                getResources(), R.color.zhihu_item_checkCircle_borderColor,
                getContext().getTheme());

        int color = ta.getColor(0, defaultColor);
        ta.recycle();
        mStrokePaint.setColor(color);

        mCheckDrawable = ResourcesCompat.getDrawable(context.getResources(),
                R.drawable.ic_check_white_18dp, context.getTheme());
    }

    public void setChecked(boolean checked){
        if(mCountable) {
            throw new IllegalStateException("CheckView is countable, call setCheckedNum() instead.");
        }
        mChecked = checked;
        invalidate();
    }

    public void setCountable(boolean countable){
        mCountable = countable;
    }

    public void setCheckedNum(int checkedNum){
        if(!mCountable){
            throw new IllegalStateException("CheckView is not countable, call setChecked() instead");
        }
        if(checkedNum != UNCHECKED && checkedNum <= 0){
            throw new IllegalArgumentException("checked num can't be negative");
        }

        mCheckNum = checkedNum;
        invalidate();
    }

    @Override
    public void setEnabled(boolean enabled) {
        if(mEnabled != enabled){
            mEnabled = enabled;
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}

















