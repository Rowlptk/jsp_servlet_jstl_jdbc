package com.online.quiz;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;




@WebServlet("/QuestionControllerServlet")
public class QuestionControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

private QuestionDbUtil questionDbUtil;
	
	@Resource(name="jdbc/online_quiz")
	private DataSource dataSource;

	
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			questionDbUtil = new QuestionDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
						
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}
						
			// route to the appropriate method
			switch (theCommand) {
							
			case "LIST":
				listQuestion(request, response);
				break;
				
			case "ADD":
				addQuestion(request, response);
				break;
				
			case "LOAD":
				loadQuestion(request, response);
				break;
				
			case "UPDATE":
				updateQuestion(request, response);
				break;
				
			default:
				listQuestion(request, response);
			}
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	private void updateQuestion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read student info from form data
		int questionId = Integer.parseInt(request.getParameter("questionId"));
		String question = request.getParameter("question");
		String optionOne = request.getParameter("optionOne");
		String optionTwo = request.getParameter("optionTwo");
		String optionThree = request.getParameter("optionThree");
		String optionFour = request.getParameter("optionFour");
		String answer = request.getParameter("answer");
		
		switch(answer) {
		case "A":
			answer = optionOne;
			break;
		case "B":
			answer = optionTwo;
			break;
		case "C":
			answer = optionThree;
			break;
		case "D":
			answer = optionFour;
			break;
		default:
			answer = null;
		}
				
		// create a new student object
		Question theQuestion = new Question(questionId, question, optionOne, optionTwo, optionThree, optionFour, answer);
				
		// perform update on database
		questionDbUtil.updateQuestion(theQuestion);
				
		// send them back to the "list students" page
		listQuestion(request, response);
		
	}

	private void loadQuestion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read question id from form data
		String questionId = request.getParameter("questionId");
				
		// get question from database (db util)
		Question theQuestion = questionDbUtil.getQuestion(questionId);
				
		if(theQuestion.getAnswer().equals(theQuestion.getOptionOne())) {
			theQuestion.setAnswer("A");
		} else if (theQuestion.getAnswer().equals(theQuestion.getOptionTwo())) {
			theQuestion.setAnswer("B");
		} else if (theQuestion.getAnswer().equals(theQuestion.getOptionThree())) {
			theQuestion.setAnswer("C");
		} else if (theQuestion.getAnswer().equals(theQuestion.getOptionFour())) {
			theQuestion.setAnswer("D");
		} else {
			theQuestion.setAnswer(null);
		}
		
		// place question in the request attribute
		request.setAttribute("THE_QUESTION", theQuestion);
				
		// send to jsp page: update-question-form.jsp
		RequestDispatcher dispatcher = 
			request.getRequestDispatcher("/update-question-form.jsp");
		dispatcher.forward(request, response);
		
	}

	private void addQuestion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student info from form data
		String question = request.getParameter("question");
		String optionOne = request.getParameter("optionOne");
		String optionTwo = request.getParameter("optionTwo");
		String optionThree = request.getParameter("optionThree");
		String optionFour = request.getParameter("optionFour");
		String answer = request.getParameter("answer");
		
		switch(answer) {
			case "A":
				answer = optionOne;
				break;
			case "B":
				answer = optionTwo;
				break;
			case "C":
				answer = optionThree;
				break;
			case "D":
				answer = optionFour;
				break;
			default:
				answer = null;
		}
				
		// create a new student object
		Question newQuestion = new Question(question, optionOne, optionTwo, optionThree, optionFour, answer);
				
		// add the student to the database
		questionDbUtil.addQuestion(newQuestion);
				
		// send back to main page (the student list)
		listQuestion(request,response);
		
	}

	private void listQuestion(HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
				// get questions from db util
				List<Question> questions = questionDbUtil.getQuestions();
				
				// add questions to the request
				request.setAttribute("QUESTION_LIST", questions);
				
				// send to JSP page (view)
				RequestDispatcher dispatcher = request.getRequestDispatcher("/list-questions.jsp");
				dispatcher.forward(request, response);
		
	}

}
