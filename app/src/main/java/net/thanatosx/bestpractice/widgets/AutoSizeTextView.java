package net.thanatosx.bestpractice.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;


/**
 *
 */
public class AutoSizeTextView extends TextView {
    private int mContentWidth;
    private float mDefaultTextSize;
    private float mMinSize;

    public AutoSizeTextView(Context context) {
        this(context, null);
    }

    public AutoSizeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoSizeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        mDefaultTextSize = getTextSize();
        mMinSize = 1;
        mContentWidth = getWidth() - getPaddingLeft() - getPaddingRight();
    }

    private void resize(CharSequence charSequence) {
        String text = charSequence.toString();
        if (TextUtils.isEmpty(text) || mContentWidth <= 0) return;
        TextPaint paint = new TextPaint(getPaint());
        paint.setTextSize(mDefaultTextSize);
        float ts = mDefaultTextSize, mw = paint.measureText(text);
        for (; ts > mMinSize && mw > mContentWidth; ) {
            paint.setTextSize(--ts);
            mw = paint.measureText(text);
        }
        setTextSize(TypedValue.COMPLEX_UNIT_PX, ts);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mContentWidth = w - getPaddingLeft() - getPaddingRight();
        resize(getText());
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int before, int after) {
        super.onTextChanged(text, start, before, after);
        resize(text);
    }
}
