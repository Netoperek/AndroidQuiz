package com.example.quiz;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.file.QuestionParser;

@EActivity(R.layout.activity_file_exploring)
public class FileExploringActivity extends ListActivity {
	private List<String> item = null;
	private List<String> path = null;
	private final String ROOT = "/";
	private final String LOCATION = "Location";
	private final String TXT_EXTENSION = ".txt";
	private final String OK = "OK";
	private final String NOT_TXT_FILE = "Please choose txt file";
	private final String FOLDER_CANNOT_BE_READ = "folder can't be read!";

	@ViewById(R.id.path)
	TextView myPath;

	@AfterViews
	void getDirRoot() {
		String dirPath = Environment.getExternalStorageDirectory().getPath();
		getDir(dirPath);
	}

	void getDir(String dirPath) {
		myPath.setText(LOCATION + dirPath);
		item = new ArrayList<String>();
		path = new ArrayList<String>();
		File f = new File(dirPath);
		File[] files = f.listFiles();

		if (!dirPath.equals(ROOT)) {
			item.add(ROOT);
			path.add(ROOT);
			item.add("../");
			path.add(f.getParent());
		}

		for (int i = 0; i < files.length; i++) {
			File file = files[i];

			if (!file.isHidden() && file.canRead()) {
				path.add(file.getPath());
				if (file.isDirectory()) {
					item.add(file.getName() + "/");
				} else {
					item.add(file.getName());
				}
			}
		}
		ArrayAdapter<String> fileList = new ArrayAdapter<String>(this,
				R.layout.row, item);

		setListAdapter(fileList);
	}

	@ItemClick
	void listItemClicked(int position) {
		File file = new File(path.get(position));

		if (file.isDirectory()) {
			if (file.canRead()) {
				getDir(path.get(position));
			} else {
				new AlertDialog.Builder(this)
						.setIcon(R.drawable.ic_launcher)
						.setTitle(
								"[" + file.getName() + "] "
										+ FOLDER_CANNOT_BE_READ)
						.setPositiveButton(OK, null).show();
			}
		} else if (file.getName().contains(TXT_EXTENSION)) {
			Log.i("READER", "OK");
			QuestionParser.parseQuestions(file);

		} else {
			new AlertDialog.Builder(this).setIcon(R.drawable.ic_launcher)
					.setTitle(NOT_TXT_FILE).setPositiveButton(OK, null).show();
		}
	}
}
