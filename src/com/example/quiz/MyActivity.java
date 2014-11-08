package com.example.quiz;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

@EActivity(R.layout.activity_main)
public class MyActivity extends Activity {
	
	@ViewById(R.id.chooseFileButton)
	Button chooseFileButton;
	
	@ViewById(R.id.addFile)
	TextView addFileText;
	
	@Click(R.id.chooseFileButton)
	void chooseFile() {
	}
	
}
