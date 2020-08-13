<!DOCTYPE html>

<html>

<head>

	<title>Add Question</title>
</head>

<body>
	<div id="container">
		<h3>Add Question</h3>
		
		<form action="QuestionControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="ADD" />
			
			<div>
				<label>Question : </label> <br><br>
				<textarea name="question"></textarea>
			</div>
			<br>
			<div>
				<label>A. </label>
				<input type="text" name="optionOne" />
			</div>
			<br>
			<div>
				<label>B. </label>
				<input type="text" name="optionTwo" />
			</div>
			<br>
			<div>
				<label>C. </label>
				<input type="text" name="optionThree" />
			</div>
			<br>
			<div>
				<label>D. </label>
				<input type="text" name="optionFour" />
			</div>	
			<br>	
			<div>
				<label>Answer: </label>
				<input type="radio" name="answer" value='A' /> A
				<input type="radio" name="answer" value='B' /> B
				<input type="radio" name="answer" value='C' /> C
				<input type="radio" name="answer" value='D' /> D
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