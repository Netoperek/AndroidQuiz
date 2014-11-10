package com.example.file;

import android.util.SparseArray;

public class Question {
	private int number;
	private boolean correct;
	private String content;
	private SparseArray<Answer> answers = new SparseArray<Answer>();

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public SparseArray<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(SparseArray<Answer> answers) {
		this.answers = answers;
	}
}
