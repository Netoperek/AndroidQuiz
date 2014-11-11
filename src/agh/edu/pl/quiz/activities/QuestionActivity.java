package agh.edu.pl.quiz.activities;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import agh.edu.pl.quiz.helpers.Question;
import agh.edu.pl.quiz.helpers.QuestionsDispatcher;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.quiz.R;


@EActivity(R.layout.activity_question)
public class QuestionActivity extends ListActivity {
	private final String START = "Start The Quiz !";
	private final String NEXT_QUESTION = "Next Question";
	private final String QUESTION_NUMBER = "Question Number: ";
	private final String QUESTIONS_NUMBER = " Number of questions : ";
	private final int selectedColor = Color.BLUE;
	private final int unselectedColor = Color.CYAN;

	private List<Integer> selected = new ArrayList<Integer>();
	private Question question;

	@ViewById(R.id.questionNumber)
	TextView questionNumber;

	@ViewById(R.id.questionContent)
	TextView questionContent;

	@ViewById(R.id.nextQuestion)
	Button nextQuestionButton;
	
	@ViewById(R.id.questionsNumber)
	TextView questionsNumber;

	@AfterViews
	void setButtonText() {
		nextQuestionButton.setText(START);
		nextQuestionButton.setBackgroundColor(selectedColor);
		questionsNumber.setText(QUESTIONS_NUMBER + QuestionsDispatcher.getQuestionsNumber());
	}
	
	@Click(R.id.nextQuestion)
	void getNextQuestion() {
		question = QuestionsDispatcher.getNextQuestion();
		if(QuestionsDispatcher.questionsFinished()) {
			Intent intent = new Intent(this, FinishActivity_.class);
			startActivity(intent);
		} else {
			List<String> answersContent = new ArrayList<String>();
			String number = Integer
					.toString(QuestionsDispatcher.getQuestionIndex());
			questionsNumber.setText("");
			questionNumber.setText(QUESTION_NUMBER + " " + number);
			nextQuestionButton.setText(START);
			questionContent.setText(question.getContent());
			nextQuestionButton.setText(NEXT_QUESTION);
			
			for (int i = 0; i < question.getAnswers().size(); i++) {
				answersContent.add(question.getAnswers().get(i).getContent());
			}
			ArrayAdapter<String> answersList = new ArrayAdapter<String>(this,
					R.layout.answer, answersContent);
			setListAdapter(answersList);
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@ItemClick
	void listItemClicked(int position) {
		if (!selected.contains(position)) {
			selected.add(position);
			this.getListView().getChildAt(position)
					.setBackgroundColor(selectedColor);
			question.getAnswers().get(position).setMarkedCorrect(true);
			
		} else {
			selected.remove(Integer.valueOf(position));
			this.getListView().getChildAt(position)
					.setBackgroundColor(unselectedColor);
			question.getAnswers().get(position).setMarkedCorrect(false);
		}
	}

}
