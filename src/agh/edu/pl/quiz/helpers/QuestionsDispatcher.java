package agh.edu.pl.quiz.helpers;

import android.util.SparseArray;

public class QuestionsDispatcher {
	public static SparseArray<Question> questions = new SparseArray<Question>();
	private static int questionIndex = 0;
	private static int correctAnswers = 0;
	private static int wrongAnswers = 0;
	
	public static boolean questionsFinished() {
		return questionIndex > getQuestionsNumber();
	}
	
	public static void setDispatcher() {
		questions.clear();
		questionIndex = 0;
		setCorrectAnswers(0);
		setWrongAnswers(0);
	}
	
	public static Question getNextQuestion() {
		return questions.get(questionIndex++);
	}
	
	public static Question getQuestion(int index) {
		return questions.get(index);
	}
	
	public static int getQuestionIndex() {
		return questionIndex;
	}
	
	public static int getQuestionsNumber() {
		return questions.size();
	}
	

	public static int getCorrectAnswers() {
		return correctAnswers;
	}

	public static void setCorrectAnswers(int correctAnswers) {
		QuestionsDispatcher.correctAnswers = correctAnswers;
	}

	public static int getWrongAnswers() {
		return wrongAnswers;
	}

	public static void setWrongAnswers(int wrongAnswers) {
		QuestionsDispatcher.wrongAnswers = wrongAnswers;
	}
}
