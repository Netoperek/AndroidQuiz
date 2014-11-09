package com.example.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestionParser {

	static HashMap<Question, List<Answer>> questions = new HashMap<Question, List<Answer>>();
	static QuestionParserParameters questionParserParameters = QuestionParserParameters
			.getInstance();

	static BufferedReader reader;

	public static void parseQuestions(File file) {
		try {
			FileInputStream fin = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(fin, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = "";
		String question = "";
		try {
			line = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (line != null) {
			if (line.contains(questionParserParameters.getQUESTION_KEY_WORD())) {
				question += line;
				question += "\n";
				try {
					line = reader.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				while (line != null
						&& !line.contains(questionParserParameters
								.getQUESTION_KEY_WORD())) {
					question += line;
					question += "\n";
					try {
						line = reader.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				getQuestion(question);
				question = "";
			}
			try {
				line = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void getQuestion(String questionConent) {
		int index = 1;
		String[] questionContentArray = questionConent.split("\n");
		List<Answer> answers = new ArrayList<Answer>();
		Question question = new Question();
		question.setContent(questionContentArray[index]);

		for (int i = ++index; i < questionContentArray.length; i++) {
			Answer answer = new Answer();
			if (questionContentArray[i].contains(questionParserParameters
					.getCORRECT_ANSWER())) {
				answer.setCorrect(true);
				questionContentArray[i] = questionContentArray[i].replace(
						questionParserParameters.getCORRECT_ANSWER(), "");
			}
			answer.setContent(questionContentArray[i]);
			answers.add(answer);
		}

		questions.put(question, answers);
	}

}
