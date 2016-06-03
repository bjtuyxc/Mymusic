package com.example.utils;

import com.example.tabfragment.R;
import com.example.tabfragment.R.styleable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class CycleImageView extends View {
	private int type;
	public static final int TYPE_CYCLE = 0;
	public static final int TYPE_ROUND = 1;

	private Bitmap mSrc;
	private int mRadius;
	private int mWidth;
	private int mHeight;

	public CycleImageView(Context context) {
		this(context, null);
	}

	public CycleImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public void setImageResource(int ResId) {
		mSrc = BitmapFactory.decodeResource(getResources(), ResId);
	}

	public void setDrawType(int type) {
		this.type = type;
	}

	public void setBorderRadius(int radius) {
		mRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				radius, getResources().getDisplayMetrics());
	}

	public CycleImageView(Context context, AttributeSet attrs, int deStyle) {
		super(context, attrs, deStyle);
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.CycleImageView, deStyle, 0);
		int n = a.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.CycleImageView_src:
				mSrc = BitmapFactory.decodeResource(getResources(),
						a.getResourceId(attr, 0));
				break;
			case R.styleable.CycleImageView_type:
				type = a.getInt(attr, 0);
				break;
			case R.styleable.CycleImageView_borderRadius:
				mRadius = a.getDimensionPixelSize(attr, (int) TypedValue
						.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f,
								getResources().getDisplayMetrics()));
				break;
			}
		}
		a.recycle();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);

		if (widthMode == MeasureSpec.EXACTLY) {
			mWidth = widthSize;
		} else {
			int measure = getPaddingLeft() + getPaddingRight()
					+ mSrc.getWidth();
			if (widthMode == MeasureSpec.AT_MOST) {
				mWidth = Math.min(widthSize, measure);
			}
		}

		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		if (heightMode == MeasureSpec.EXACTLY) {
			mHeight = heightSize;
		} else {
			int measure = getPaddingBottom() + getPaddingBottom()
					+ mSrc.getHeight();
			if (heightMode == MeasureSpec.AT_MOST) {
				mHeight = Math.min(heightSize, measure);
			}
		}
		setMeasuredDimension(mWidth, mHeight);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		switch (type) {
		case TYPE_CYCLE:
			int min = Math.min(mWidth, mHeight);
			mSrc = Bitmap.createScaledBitmap(mSrc, min, min, false);
			canvas.drawBitmap(createCircleImage(mSrc, min), 0, 0, null);
			break;
		case TYPE_ROUND:
			min = Math.min(mWidth, mHeight);
			mSrc = Bitmap.createScaledBitmap(mSrc, min, min, false);
			canvas.drawBitmap(createRoundImage(mSrc), 0, 0, null);
			break;
		}
	}

	private Bitmap createCircleImage(Bitmap src, int min) {
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		Bitmap target = Bitmap.createBitmap(min, min, Config.ARGB_8888);
		Canvas canvas = new Canvas(target);
		canvas.drawCircle(min / 2, min / 2, min / 2, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(src, 0, 0, paint);
		return target;

	}

	private Bitmap createRoundImage(Bitmap src) {
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		Bitmap target = Bitmap.createBitmap(mWidth, mHeight, Config.ARGB_8888);
		Canvas canvas = new Canvas(target);
		RectF rect = new RectF(0, 0, mWidth, mHeight);
		canvas.drawRoundRect(rect, mRadius, mRadius, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(src, 0, 0, paint);
		return target;
	}
}
