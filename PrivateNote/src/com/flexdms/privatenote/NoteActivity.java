package com.flexdms.privatenote;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NoteActivity extends Activity {

	public void showHideFileName(boolean show) {
		View view = findViewById(R.id.filenamecontainer);
		if (show) {
			view.setVisibility(View.VISIBLE);
		} else {
			view.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.note, menu);
		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (file != null) {
			try {
				save();
				
			} catch (IOException e) {
				return false;
			}
			
		} 
		if(item.getItemId()==R.id.open)
		{
			file=null;
			
			currentCode=OPEN;
			((Button) findViewById(R.id.donebtn)).setText(R.string.open);
			((EditText) findViewById(R.id.filename)).setText(null);
			
			showHideFileName(true);
			return true;
		} 
		if (file!=null){
			return true;
		}
		currentCode=SAVE;
		((Button) findViewById(R.id.donebtn)).setText(R.string.save);
		
		showHideFileName(true);
		return true;
		
	}

	int currentCode=-1;
//	public void chooseFile(int code) {
//		currentCode=code;
//		showHideFileName(true);
//		
//		// Create the ACTION_GET_CONTENT Intent
//		/*if (code == OPEN) {
//			Intent getContentIntent = FileUtils.createGetContentIntent();
//
//			Intent intent = Intent.createChooser(getContentIntent,
//					"Select a file");
//			startActivityForResult(intent, code);
//		} else {
//			final Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
//			// The MIME data type filter
//			intent.setType("text/plain");
//			startActivityForResult(intent, code);
//		}*/
//
//	}

	File file = null;
	private static final int SAVE = 1;
	private static final int OPEN = 2;

//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		switch (requestCode) {
//		case SAVE:
//			if (resultCode == RESULT_OK) {
//
//				final Uri uri = data.getData();
//
//				// Get the File path from the Uri
//				String path = FileUtils.getPath(this, uri);
//
//				// Alternatively, use FileUtils.getFile(Context, Uri)
//				if (path != null && FileUtils.isLocal(path)) {
//					file = new File(path);
//					if (file.isDirectory()) {
//						showHideFileName(true);
//					}
//				}
//			}
//			break;
//		case OPEN:
//			if (resultCode == RESULT_OK) {
//
//				final Uri uri = data.getData();
//
//				// Get the File path from the Uri
//				String path = FileUtils.getPath(this, uri);
//
//				// Alternatively, use FileUtils.getFile(Context, Uri)
//				if (path != null && FileUtils.isLocal(path)) {
//					file = new File(path);
//				}
//			}
//			break;
//		}
//	}

	public void createAndSave(View view) throws IOException {
		String filename = ((EditText) findViewById(R.id.filename)).getText()
				.toString();
		File pfile=getFilesDir();
		if (pfile.exists()){
			pfile.mkdirs();
		}
		
		file = new File(pfile, filename);
		if (!file.exists()){
			file.createNewFile();
		}
		if (currentCode==SAVE){
			save();
		} else {
			open();
		}
		
		showHideFileName(false);
		currentCode=-1;
	}

	public void save() throws IOException {
		String text = ((EditText) findViewById(R.id.editText)).getText()
				.toString();
		Writer writer = new FileWriter(file);
		writer.write(text);
		writer.close();
	}

	public void open() throws IOException {
		if (file.isDirectory()) {
			return;
		}
		BufferedReader reader = new BufferedReader(new FileReader(file));
		StringBuilder builder = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}
		((EditText) findViewById(R.id.editText)).setText(builder.toString());
		reader.close();
	}

}
