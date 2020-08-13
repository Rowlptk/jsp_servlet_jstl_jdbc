package com.online.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class QuestionDbUtil {
	
	private DataSource dataSource;

	public QuestionDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Question> getQuestions() throws Exception {
	
		List<Question> questions = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from questions";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String question = myRs.getString("question");
				String optionOne = myRs.getString("option_one");
				String optionTwo = myRs.getString("option_two");
				String optionThree = myRs.getString("option_three");
				String optionFour = myRs.getString("option_four");
				String answer = myRs.getString("answer");
				
				// create new student object
				Question tempQuestion = new Question(id, question, optionOne, optionTwo, optionThree, optionFour, answer);
				
				// add it to the list of students
				questions.add(tempQuestion);
			}
			return questions;
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}

	}
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		
		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close(); // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}

	public void addQuestion(Question newQuestion) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into questions "
						+ "(question, option_one, option_two, option_three, option_four, answer) "
						+ "values (?, ?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the question
			myStmt.setString(1, newQuestion.getQuestion());
			myStmt.setString(2, newQuestion.getOptionOne());
			myStmt.setString(3, newQuestion.getOptionTwo());
			myStmt.setString(4, newQuestion.getOptionThree());
			myStmt.setString(5, newQuestion.getOptionFour());
			myStmt.setString(6, newQuestion.getAnswer());
			
			
			// execute sql insert
			myStmt.execute();
			
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}

	}

	public Question getQuestion(String theQuestionId) throws Exception {
		
		Question theQuestion = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int questionId;
		
		try {
			// convert question id to int
			questionId = Integer.parseInt(theQuestionId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from questions where id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, questionId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String question = myRs.getString("question");
				String optionOne = myRs.getString("option_one");
				String optionTwo = myRs.getString("option_two");
				String optionThree = myRs.getString("option_three");
				String optionFour = myRs.getString("option_four");
				String answer = myRs.getString("answer");
			
				// use the studentId during construction
				theQuestion = new Question(questionId, question, optionOne, optionTwo, optionThree, optionFour, answer);
			}
			else {
				throw new Exception("Could not find student id: " + questionId);
			}
			
			return theQuestion;
		}
		finally {
			// clean up JDBC object
			close(myConn, myStmt, myRs);
		}
	}

	public void updateQuestion(Question theQuestion) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		 
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update questions "
						+ "set question=?, option_one=?, option_two=?, option_three=?, option_four=?, answer=? "
						+ "where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, theQuestion.getQuestion());
			myStmt.setString(2, theQuestion.getOptionOne());
			myStmt.setString(3, theQuestion.getOptionTwo());
			myStmt.setString(4, theQuestion.getOptionThree());
			myStmt.setString(5, theQuestion.getOptionFour());
			myStmt.setString(6, theQuestion.getAnswer());
			myStmt.setInt(7, theQuestion.getQuestionId());
	
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
		
	}
	
}
