package com.habzy.preview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

public class PreviewActivity extends Activity implements OnClickListener
{
    /** Called when the activity is first created. */
    
    private Button mShowOrNotBt;
    
    private Button mChangeBackBt;
    
    private Button mMoveBt;
    
    private RelativeLayout mCameraLayout;
    
    private MySurface mPreviewBack;
    
    private RelativeLayout mImageLayout;
    
    private boolean mIsShownPre = false;
    
    private boolean mIsFocused = false;
    
    private boolean mIsMoved = false;
    
    private AlertDialog.Builder builder;
    
    private AlertDialog alertDialog;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mShowOrNotBt = (Button) findViewById(R.id.showOrNot);
        
        mChangeBackBt = (Button) findViewById(R.id.changeBck);
        
        mMoveBt = (Button) findViewById(R.id.move);
        
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mCameraLayout = (RelativeLayout) inflater.inflate(R.layout.preview_layout,
                null);
        
        // (RelativeLayout) findViewById(R.id.cameraLayout);
        
        mPreviewBack = (MySurface) mCameraLayout.findViewById(R.id.Surface);
        builder = new AlertDialog.Builder(this);
        builder.setView(mCameraLayout);
        alertDialog = builder.create();
        alertDialog.setOwnerActivity(this);
        
        mImageLayout = (RelativeLayout) findViewById(R.id.imageLayout);
        
        mShowOrNotBt.setOnClickListener(this);
        mChangeBackBt.setOnClickListener(this);
        mMoveBt.setOnClickListener(this);
        
    }
    
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
                // mCameraLayout.setPadding(0, 100, 0, 0);
                if (!mIsMoved)
                {
                    // mCameraLayout.removeView(mPreviewBack);
                    
                    alertDialog.show();
                }
                else
                {
                    // mCameraLayout.addView(mPreviewBack);
                    
                    alertDialog.dismiss();
                }
                mIsMoved = !mIsMoved;
                break;
            }
            default:
                break;
        }
        
    }
}