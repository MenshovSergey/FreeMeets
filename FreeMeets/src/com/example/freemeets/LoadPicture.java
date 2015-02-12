package com.example.freemeets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoadPicture extends Activity {
	public final int CAMERA_RESULT = 0;
	private String folderToSave = Environment.getExternalStorageDirectory()
			.toString();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.load_photo);
		Button load = (Button)findViewById(R.id.button1);
		Button takePhoto = (Button)findViewById(R.id.button2);
		load.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent cameraIntent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(cameraIntent, CAMERA_RESULT);
			}

		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA_RESULT) {
			Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
			//writeImageFile(thumbnail);
			System.out.println("end write");
			//ivCamera.setImageBitmap(thumbnail);

		}
	}

	private void writeImageFile(Bitmap bitmap) {
		FileOutputStream fOut = null;
		try {

			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
					.format(new Date());
			File file = new File(folderToSave, timeStamp + ".jpg");
			fOut = new FileOutputStream(file);			
			bitmap.compress(CompressFormat.JPEG, 85, fOut);
			fOut.flush();
			fOut.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
