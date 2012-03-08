/*
 . * File name: PreviewActivity.java
 . * Description:
 . * Author: Habzy Huang 
 . * Change date: 2012-03-08
 . * Change content: 
 */
package com.habzy.preview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class PreviewActivity extends Activity implements OnClickListener
{
    /** Called when the activity is first created. */
    
    private Button mShowOrNotBt;
    
    private Button mChangeBackBt;
    
    private Button mMoveBt;
    
    private RelativeLayout mPreviewLayout;
    
    private RelativeLayout mCameraLayout;
    
    private MySurface mPreviewBack;
    
    private RelativeLayout mImageLayout;
    
    private LayoutParams layoutParams;
    
    private int mStatusBarHeight;
    
    private boolean mIsShownPre = false;
    
    private boolean mIsFocused = false;
    
    private AlertDialog.Builder builder;
    
    private AlertDialog alertDialog;
    
    private OnTouchListener mTouchToMovePreviewListener = new OnTouchListener()
    {
        float[] distance = new float[] { 0f, 0f };
        
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                {
                    distance[0] = event.getX() - layoutParams.leftMargin;
                    distance[1] = event.getY() - layoutParams.topMargin;
                    break;
                }
                case MotionEvent.ACTION_MOVE:
                {
                    moveView((int) (event.getRawX() - distance[0]),
                            (int) (event.getRawY() - distance[1]));
                    break;
                }
            }
            return true;
        }
    };
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mShowOrNotBt = (Button) findViewById(R.id.showOrNot);
        mChangeBackBt = (Button) findViewById(R.id.changeBck);
        mMoveBt = (Button) findViewById(R.id.move);
        mShowOrNotBt.setOnClickListener(this);
        mChangeBackBt.setOnClickListener(this);
        mMoveBt.setOnClickListener(this);
        
        mImageLayout = (RelativeLayout) findViewById(R.id.imageLayout);
        
        mCameraLayout = (RelativeLayout) findViewById(R.id.cameraLayout);
        mCameraLayout.setOnTouchListener(mTouchToMovePreviewListener);
        addPreviewFromXml();
        
        
        buildDialog(false);
    }
    
    /**
     * @param parentLayout
     *            the parent layout
     * 
     */
    private void addPreviewFromXml()
    {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPreviewLayout = (RelativeLayout) inflater.inflate(R.layout.preview_layout,
                null);
        mPreviewBack = (MySurface) mPreviewLayout.findViewById(R.id.Surface);
        
        layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = 1;
        layoutParams.topMargin = 3;
        
        mCameraLayout.addView(mPreviewLayout, layoutParams);
        
    }
    
    /**
     * 
     * @param x
     * @param y
     */
    public void moveView(int x, int y)
    {
        if (mStatusBarHeight == 0)
        {
            View rootView = mPreviewLayout.getRootView();
            Rect r = new Rect();
            rootView.getWindowVisibleDisplayFrame(r);
            mStatusBarHeight = r.top;
        }
        
        layoutParams.leftMargin = x;
        layoutParams.topMargin = y - mStatusBarHeight;// STATUS_HEIGHT;
        mCameraLayout.updateViewLayout(mPreviewLayout, layoutParams);
        
        // mPreviewLayout.layout(x, x, x, x);
    }
    
    /**
     * @deprecated It dosen't work. For what I want.
     * @param bool
     *            Just backup the test code.
     */
    private void buildDialog(boolean bool)
    {
        if (!bool)
        {
            return;
        }
        builder = new AlertDialog.Builder(this);
        builder.setView(mPreviewLayout);
        alertDialog = builder.create();
        alertDialog.setOwnerActivity(this);
        alertDialog.show();
    }
    
    int i = 20;
    
    @Override
    public void onClick(View v)
    {
        
        switch (v.getId())
        {
            case R.id.showOrNot:
            {
                if (mIsShownPre)
                {
                    mCameraLayout.setVisibility(View.GONE);
                    mImageLayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    mCameraLayout.setVisibility(View.VISIBLE);
                    mImageLayout.setVisibility(View.GONE);
                }
                mIsShownPre = !mIsShownPre;
                break;
            }
            case R.id.changeBck:
            {
                if (!mIsFocused)
                {
                    mPreviewBack.setBackgroundResource(R.drawable.preview_backgroud_green);
                }
                else
                {
                    mPreviewBack.setBackgroundResource(R.drawable.preview_backgroud);
                }
                mIsFocused = !mIsFocused;
                break;
            }
            case R.id.move:
            {
                
                // moveView(i, i);
                //
                // i = i + 10;
                //
                // // mCameraLayout.setPadding(0, 100, 0, 0);
                // if (!mIsMoved)
                // {
                // // mCameraLayout.removeView(mPreviewBack);
                //
                // }
                // else
                // {
                // // mCameraLayout.addView(mPreviewBack);
                // }
                // mIsMoved = !mIsMoved;
                break;
            }
            default:
                break;
        }
        
    }
}