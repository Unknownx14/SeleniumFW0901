Feature: Error Validations

Background:
	Given A user has landed on the Ecommerce landing page

@ErrorValidations
Scenario Outline: Invalid username and/or password
	Given A user has attempted to login with a valid username <username> and an invalid password <invalidPassword>
	Then Verify that user is not logged in and the message "Incorrect email or password." is displayed
	And A browser is closed
	
Examples:
	| username					| password					| invalidUsername					| invalidPassword					|
	| unknownxjk@gmail.com		| kojikurac123				| unknownxjkAAA@gmail.com			| kojikurac1234						|