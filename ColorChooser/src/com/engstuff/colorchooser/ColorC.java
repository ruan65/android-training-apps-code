package com.engstuff.colorchooser;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ColorC extends Activity implements OnSeekBarChangeListener {

	private final String TAG = ColorC.class.getSimpleName();
	private Bitmap mBitmap;
	private Canvas mCanvas;
	private ImageView iv;
	private SeekBar sbAlfa, sbRed, sbGreen, sbBlue;
	private int alpha, r, g, b; // alpha, red, green, blue
	private int colorHex;
	private Paint tp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.color_c);

		iv = (ImageView) findViewById(R.id.colorView);
		
		sbAlfa = (SeekBar) findViewById(R.id.sbAlfa);
		sbRed = (SeekBar) findViewById(R.id.sbRed);
		sbGreen = (SeekBar) findViewById(R.id.sbGreen);
		sbBlue = (SeekBar) findViewById(R.id.sbBlue);
		
		sbAlfa.setOnSeekBarChangeListener(this);
		sbRed.setOnSeekBarChangeListener(this);
		sbGreen.setOnSeekBarChangeListener(this);
		sbBlue.setOnSeekBarChangeListener(this);
		
		alpha = getResources().getInteger(R.integer.sbMax);
		r = g = b = getResources().getInteger(R.integer.sbProgress);

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		int w = iv.getWidth();
		int h = iv.getHeight();

		mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
		tp = new Paint();

		changeColor();
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		switch (seekBar.getId()) {
		case R.id.sbAlfa:
			alpha = progress;
			break;
		case R.id.sbRed:
			r = progress;
			break;
		case R.id.sbGreen:
			g = progress;
			break;
		case R.id.sbBlue:
			b = progress;
			break;
		default:
			break;
		}
		changeColor();
	}

	@SuppressWarnings("deprecation")
	private void changeColor() {

		colorHex = HexColorFrom4parts.composeHex(alpha, r, g, b);
		mCanvas.drawRGB(r, g, b);

		if ((r > 160 && g > 160 && b > 160) || (r > 200 && g > 200))
			tp.setColor(Color.BLACK);
		else
			tp.setColor(Color.WHITE);
		
		tp.setTextSize(30);
		mCanvas.drawText("alpha: " + alpha, 10, 40, tp);
		mCanvas.drawText("red:" + r + " green:" + g + " blue:" + b, 10, 80, tp);
		mCanvas.drawText("#" + Integer.toHexString(colorHex), 10, 120, tp);

		iv.setImageBitmap(mBitmap);
		iv.setAlpha(alpha);
		Log.i(TAG, "fc change color alpha" + alpha + " r " + r + " g " + g
				+ " b " + b + "col hex " + Long.toHexString(colorHex));
		Log.i(TAG, Integer.toHexString(colorHex));

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
	}
}
