package com.photoconverter;

import java.io.File;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class MainActivity extends ActionBarActivity {
	
	private static final int IMAGE_REQUEST_CODE = 100;
	private static final int SHARE_REQUEST = 100;
	private Uri curi;
	Bitmap cimg, gimg;
	File file;
	String PATH = "/sdcard/QuickShare/img_color.jpeg";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		captureImage();
	}

	protected void captureImage() {
		Intent capIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		file = new File(PATH);
		curi = Uri.fromFile(file);
		capIntent.putExtra(MediaStore.EXTRA_OUTPUT, curi);
		startActivityForResult(capIntent, IMAGE_REQUEST_CODE);
	}
	
	protected void shareImage(){
		if(file.exists()){
			Intent shareIntent = new Intent(Intent.ACTION_SEND);
			shareIntent.setType("image/jpeg");
			curi = Uri.fromFile(file);
			shareIntent.putExtra(Intent.EXTRA_STREAM, curi);
			startActivityForResult(Intent.createChooser(shareIntent, 
					"Share image with..."), SHARE_REQUEST);
		}else{
			Toast.makeText(getApplicationContext(), "No such file!",
        			Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == IMAGE_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
	        	Toast.makeText(getApplicationContext(), "Image saved as file!",
	        			Toast.LENGTH_SHORT).show();
	        	shareImage();
	        }
		}
	}
}