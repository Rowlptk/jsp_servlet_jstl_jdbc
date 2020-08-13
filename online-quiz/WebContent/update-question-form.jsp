<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>

<head>

	<title>Update Question</title>
</head>

<body>
	<div id="container">
		<h3>Update Question</h3>
		
		<form action="QuestionControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE" />
			
			<input type="hidden" name="questionId" value="${THE_QUESTION.questionId}" />
			
			<div>
				<label>Question : </label> <br><br>
				<textarea name="question"
				>${THE_QUESTION.question}</textarea>
			</div>
			<br>
			<div>
				<label>A. </label>
				<input type="text" name="optionOne" 
				value="${THE_QUESTION.optionOne}" />
			</div>
			<br>
			<div>
				<label>B. </label>
				<input type="text" name="optionTwo" 
				value="${THE_QUESTION.optionTwo}" />
			</div>
			<br>
			<div>
				<label>C. </label>
				<input type="text" name="optionThree" 
				value="${THE_QUESTION.optionThree}" />
			</div>
			<br>
			<div>
				<label>D. </label>
				<input type="text" name="optionFour" 
				value="${THE_QUESTION.optionFour}" />
			</div>	
			<br>	
			<div>
				<c:set var="answer" value="${THE_QUESTION.answer}" />
				
				<label>Answer: </label>
				<input type="radio" name="answer" value='A' ${answer == 'A'? 'checked' : ''} /> A
				<input type="radio" name="answer" value='B' ${answer == 'B'? 'checked' : ''} /> B
				<input type="radio" name="answer" value='C' ${answer == 'C'? 'checked' : ''} /> C
				<input type="radio" name="answer" value='D' ${answer == 'D'? 'checked' : ''} /> D
			</div>
			<br>
			<div>
				<input type="submit" value="Save" class="save" />
			</div>
		</form>
		
		<div style="clear: both;"></div>
		<p>
			<a href="QuestionControllerServlet">Back to Questions</a>
		</p>
	</div>

</body>

</html>