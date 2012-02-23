package com.habzy.preview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

public class PreviewActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */

	private Button button;

	private Button focusbutton;

	private RelativeLayout camera;
	
	private MySurface mPreviewBack;

	private RelativeLayout icon;

	private boolean show = false;

	private boolean focus = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		button = (Button) findViewById(R.id.cancel);

		focusbutton = (Button) findViewById(R.id.changeBck);

		camera = (RelativeLayout) findViewById(R.id.cameraLayout);
		
		mPreviewBack = (MySurface)findViewById(R.id.Surface);

		icon = (RelativeLayout) findViewById(R.id.imageLayout);

		button.setOnClickListener(this);
		focusbutton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.cancel) {
			if (show) {
				camera.setVisibility(View.GONE);
				icon.setVisibility(View.VISIBLE);
			} else {
				camera.setVisibility(View.VISIBLE);
				icon.setVisibility(View.GONE);
			}
			show = !show;
		} else if (v.getId() == R.id.changeBck) {
			if (!focus) {
				mPreviewBack.setBackgroundResource(R.drawable.preview_backgroud);
			} else {
				mPreviewBack.setBackgroundResource(R.drawable.preview_backgroud_green);
			}
			focus = !focus;
		}

	}
}