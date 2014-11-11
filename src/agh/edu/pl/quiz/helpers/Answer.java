package agh.edu.pl.quiz.helpers;

public class Answer {
	private boolean correct;
	private String content;
	private boolean markedCorrect;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public boolean isMarkedCorrect() {
		return markedCorrect;
	}

	public void setMarkedCorrect(boolean markedCorrect) {
		this.markedCorrect = markedCorrect;
	}

}
