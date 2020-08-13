package com.online.quiz;

public class Question {
	
	private int questionId;
	private String question;
	private String optionOne;
	private String optionTwo;
	private String optionThree;
	private String optionFour;
	private String answer;
	
	
	
	public Question(String question, String optionOne, String optionTwo, String optionThree, String optionFour,
			String answer) {
		this.question = question;
		this.optionOne = optionOne;
		this.optionTwo = optionTwo;
		this.optionThree = optionThree;
		this.optionFour = optionFour;
		this.answer = answer;
	}



	public Question(int questionId, String question, String optionOne, String optionTwo, String optionThree,
			String optionFour, String answer) {
		this.questionId = questionId;
		this.question = question;
		this.optionOne = optionOne;
		this.optionTwo = optionTwo;
		this.optionThree = optionThree;
		this.optionFour = optionFour;
		this.answer = answer;
	}



	public int getQuestionId() {
		return questionId;
	}



	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}



	public String getQuestion() {
		return question;
	}



	public void setQuestion(String question) {
		this.question = question;
	}



	public String getOptionOne() {
		return optionOne;
	}



	public void setOptionOne(String optionOne) {
		this.optionOne = optionOne;
	}



	public String getOptionTwo() {
		return optionTwo;
	}



	public void setOptionTwo(String optionTwo) {
		this.optionTwo = optionTwo;
	}



	public String getOptionThree() {
		return optionThree;
	}



	public void setOptionThree(String optionThree) {
		this.optionThree = optionThree;
	}



	public String getOptionFour() {
		return optionFour;
	}



	public void setOptionFour(String optionFour) {
		this.optionFour = optionFour;
	}



	public String getAnswer() {
		return answer;
	}



	public void setAnswer(String answer) {
		this.answer = answer;
	}



	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", question=" + question + ", optionOne=" + optionOne
				+ ", optionTwo=" + optionTwo + ", optionThree=" + optionThree + ", optionFour=" + optionFour
				+ ", answer=" + answer + "]";
	}
	
	
}
