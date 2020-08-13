<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

	<head>
		<title>Question List</title>
	</head>
	
	<body>
		<h1>Aptitude Questions : </h1>
		
		<input type="button" value="Add Question" 
			onclick="window.location.href='add-question-form.jsp'; return false;"
		/>
		
		<c:forEach var="tempQuestion" items="${QUESTION_LIST}">
					
			<c:url var="tempLink" value="QuestionControllerServlet">
				<c:param name="command" value="LOAD" />
				<c:param name="questionId" value="${tempQuestion.questionId}" />
			</c:url>
			
			<div>
				<h4><span>Q${tempQuestion.questionId}.</span> ${tempQuestion.question}</h4>
				<h5><span>A. </span>${tempQuestion.optionOne}</h5>
				<h5><span>B. </span>${tempQuestion.optionTwo}</h5>
				<h5><span>C. </span>${tempQuestion.optionThree}</h5>
				<h5><span>D. </span>${tempQuestion.optionFour}</h5>
				<br/>
				<p><span>Ans: </span>${tempQuestion.answer}</p>
			</div>
			<br/>
			
			<a href="${tempLink}">Update</a>
		</c:forEach>
	</body>

</html>