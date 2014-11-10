package com.example.quiz;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import android.app.ListActivity;
import android.content.res.Configuration;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.file.Question;
import com.example.file.QuestionsDispatcher;

@EActivity(R.layout.activity_question)
public class QuestionActivity extends ListActivity {
	private final String START = "Start The Quiz !";
	private final String NEXT_QUESTION = "Next Question";
	private final String QUESTION_NUMBER = "Question Number: ";

	@ViewById(R.id.questionNumber)
	TextView questionNumber;

	@ViewById(R.id.questionContent)
	TextView questionContent;

	@ViewById(R.id.nextQuestion)
	Button nextQuestionButton;
	
	@AfterViews
	void setButtonText() {
		nextQuestionButton.setText(START);
	}

	@Click(R.id.nextQuestion)
	void getNextQuestion() {
		String number = Integer
				.toString(QuestionsDispatcher.getQuestionIndex());
		questionNumber.setText(QUESTION_NUMBER + " " + number);
		nextQuestionButton.setText(START);
		Question question = QuestionsDispatcher.getNextQuestion();
		questionContent.setText(question.getContent());
		nextQuestionButton.setText(NEXT_QUESTION);

		List<String> answersContent = new ArrayList<String>();

		for (int i = 0; i < question.getAnswers().size(); i++) {
			answersContent.add(question.getAnswers().get(i).getContent());
		}
		ArrayAdapter<String> answersList = new ArrayAdapter<String>(this,
				R.layout.row, answersContent);
		setListAdapter(answersList);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
	
	@ItemClick
	void listItemClicked(int position) {
	}

}
