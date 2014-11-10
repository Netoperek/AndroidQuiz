package com.example.file;

import android.util.SparseArray;

public class QuestionsDispatcher {
	public static SparseArray<Question> questions = new SparseArray<Question>();
	private static int questionIndex = 1;
	static int correctAnswers = 0;
	static int wrongAnswers = 0;
	
	public static Question getNextQuestion() {
		return questions.get(questionIndex++);
	}
	
	public static Question getQuestion(int index) {
		return questions.get(index);
	}
	
	public static int getQuestionIndex() {
		return questionIndex;
	}
}
