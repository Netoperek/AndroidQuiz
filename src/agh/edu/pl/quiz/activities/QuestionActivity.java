package agh.edu.pl.quiz.activities;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import agh.edu.pl.quiz.helpers.Answer;
import agh.edu.pl.quiz.helpers.Answer.Marked;
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
	private final String FINISH = " Finish Quiz";
	private final String CHECK_ANSWERS = " Check Answers";
	private final int SELECTED_COLOR = Color.parseColor("#b6fcd5");
	private final int UNSELECTED_COLOR = Color.WHITE;
	private final int CHECK_ANSWERS_COLOR = Color.parseColor("#7fffd4");
	private final int NEXT_QUESTION_COLOR = Color.parseColor("#b6fcd5");
	private final int ANSWER_MARKED_CORRECT = Color.parseColor("#00ff7f");
	private final int ANSWER_MARKED_INCORRECT = Color.parseColor("#ff4040");

	private List<Integer> selected;
	private Question question;

	private void getQuestion() {
		selected = new ArrayList<Integer>();
		question = QuestionsDispatcher.getNextQuestion();
		if (QuestionsDispatcher.questionsFinished()) {
			Intent intent = new Intent(this, FinishActivity_.class);
			startActivity(intent);
		} else {
			List<String> answersContent = new ArrayList<String>();
			String number = Integer.toString(QuestionsDispatcher
					.getQuestionIndex());
			questionsNumber.setText("");
			questionNumber.setText(QUESTION_NUMBER + " " + number);
			questionContent.setText(question.getContent());
			nextQuestionButton.setText(CHECK_ANSWERS);
			nextQuestionButton.setBackgroundColor(CHECK_ANSWERS_COLOR);

			for (int i = 0; i < question.getAnswers().size(); i++) {
				answersContent.add(question.getAnswers().get(i).getContent());
			}
			ArrayAdapter<String> answersList = new ArrayAdapter<String>(this,
					R.layout.answer, answersContent);
			setListAdapter(answersList);
		}
	}

	private void checkAnswers() {
		for (int index = 0; index < question.getAnswers().size(); index++) {
			Answer answer = question.getAnswers().get(index);
			if (!answer.isUnmarked()) {
				if (answer.isMarkedCorrectly())
					this.getListView().getChildAt(index)
							.setBackgroundColor(ANSWER_MARKED_CORRECT);
				else
					this.getListView().getChildAt(index)
							.setBackgroundColor(ANSWER_MARKED_INCORRECT);
			} else if (answer.isCorrect()) {
				this.getListView().getChildAt(index)
						.setBackgroundColor(ANSWER_MARKED_CORRECT);
			}
		}
		question.setCorrect();
		if (question.isCorrect()) {
			QuestionsDispatcher.addCorrectlyAnswered();
		} else {
			QuestionsDispatcher.addInorrectlyAnswered();
		}
		nextQuestionButton.setBackgroundColor(NEXT_QUESTION_COLOR);
		nextQuestionButton.setText(NEXT_QUESTION);
		if (QuestionsDispatcher.getQuestionIndex() == QuestionsDispatcher
				.getQuestionsNumber()) {
			nextQuestionButton.setText(FINISH);
		}
	}

	@ViewById(R.id.questionNumber)
	TextView questionNumber;

	@ViewById(R.id.questionContent)
	TextView questionContent;

	@ViewById(R.id.nextQuestion)
	Button nextQuestionButton;

	@ViewById(R.id.questionsNumber)
	TextView questionsNumber;
	
	@ViewById(R.id.restartTest)
	Button restartTestButton;

	@AfterViews
	void setButtonText() {
		nextQuestionButton.setText(START);
		nextQuestionButton.setBackgroundColor(NEXT_QUESTION_COLOR);
		questionsNumber.setText(QUESTIONS_NUMBER
				+ QuestionsDispatcher.getQuestionsNumber());
	}

	@Click(R.id.nextQuestion)
	void getNextQuestion() {
		if (nextQuestionButton.getText() == CHECK_ANSWERS) {
			checkAnswers();
		} else {
			getQuestion();
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
					.setBackgroundColor(SELECTED_COLOR);
			question.getAnswers().get(position).setMarked(Marked.CORRECT);

		} else {
			selected.remove(Integer.valueOf(position));
			this.getListView().getChildAt(position)
					.setBackgroundColor(UNSELECTED_COLOR);
			question.getAnswers().get(position).setMarked(Marked.UNMARKED);
		}

	}

}
