package com.example.ownedville.modernartui;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.graphics.Color;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    SeekBar mSeekBar;
    View mLeftTop;
    View mLeftBottom;
    View mRightTop;
    View mRightMiddle;
    View mRightBottom;
    static private final String URL = "http://www.moma.org";
    static private final String CHOOSER_TEXT = "Load " + URL + " with:";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize variables

        mSeekBar = (SeekBar) findViewById(R.id.seekBar);

        mLeftTop = (View) findViewById(R.id.rectangle_left_top);
        mLeftBottom = (View) findViewById(R.id.rectangle_left_bottom);
        mRightTop = (View) findViewById(R.id.rectangle_right_top);
        mRightMiddle = (View) findViewById(R.id.rectangle_right_middle);
        mRightBottom = (View) findViewById(R.id.rectangle_right_bottom);

        final int mLeftTopBg = ((ColorDrawable) mLeftTop.getBackground()).getColor();
        final int mLeftBottomBg = ((ColorDrawable) mLeftBottom.getBackground()).getColor();
        final int mRightTopBg = ((ColorDrawable) mRightTop.getBackground()).getColor();
        final int mRightMiddleBg = ((ColorDrawable) mRightMiddle.getBackground()).getColor();
        final int mRightBottomBg = ((ColorDrawable) mRightBottom.getBackground()).getColor();

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //Colors
            int barValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("UI",String.valueOf(i));
                changeColor(mLeftTop,mLeftTopBg,i);
                changeColor(mLeftBottom,mLeftBottomBg,i);
                changeColor(mRightTop,mRightTopBg,i);
                changeColor(mRightMiddle,mRightMiddleBg,i);
                changeColor(mRightBottom,mRightBottomBg,i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        showAlertDialog();
        return true;
    }

    private void showAlertDialog(){

        String message = "Inspired by the works of artists such as Piet Mondrian and Ben Nicholson\n\nClick below to learn more!";
        final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setMessage(message);
        alertbox.setNegativeButton("Not now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alertbox.setPositiveButton("Visit MOMA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(URL));
                Intent chooserIntent = Intent.createChooser(intent,CHOOSER_TEXT);
                startActivity(chooserIntent);
            }
        });
        alertbox.show();
    }

    private void changeColor(View view, int color,int progress) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[0] = hsv[0] + progress;
        hsv[0] = hsv[0] % 360;
        view.setBackgroundColor(Color.HSVToColor(Color.alpha(color), hsv));
    }
}
