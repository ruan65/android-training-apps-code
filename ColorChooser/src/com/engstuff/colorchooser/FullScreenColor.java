package com.engstuff.colorchooser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FullScreenColor extends Activity {

	private TextView tv;
	private RelativeLayout rl;
	private Button btnBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_full_screen_color);
		rl = (RelativeLayout) findViewById(R.id.layout_full_screen_color);
		Intent i = getIntent();
		String[] m = i.getStringArrayExtra(ColorC.EXTRA_MESSAGE_COLOR);

		int backColor = (int) Long.parseLong(m[2].substring(1), 16);
		rl.setBackgroundColor(backColor);

		tv = (TextView) findViewById(R.id.tv_color_params);
		tv.setText(m[0] + "\n" + m[1] + "\n" + m[2]);

		btnBack = (Button) findViewById(R.id.btnBack);
		btnBack.setBackgroundColor(backColor);

	}

	public void backClick(View v) {
		this.finish();
	}

}
