package agh.edu.pl.quiz.activities;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import agh.edu.pl.quiz.helpers.QuestionsDispatcher;
import android.app.Activity;
import android.widget.TextView;

import com.example.quiz.R;

@EActivity(R.layout.activity_finish)
public class FinishActivity extends Activity {
	private final String CORRECT = " Correct: ";
	private final String INCORRECT = " Incorrect: ";

	@ViewById(R.id.correct)
	TextView correct;
	
	@ViewById(R.id.incorrect)
	TextView incorrect;

	@AfterViews
	void showStatistics() {
		correct.setText(CORRECT + QuestionsDispatcher.getCorrectAnswers());
		incorrect.setText(INCORRECT + QuestionsDispatcher.getWrongAnswers());
	}
}