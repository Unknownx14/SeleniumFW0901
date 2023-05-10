Feature: Purchase an order from the Ecommerce website

Background:
	Given A user has landed on the Ecommerce landing page

@Regression
Scenario Outline: Positive path of submiting the order
	Given A user has logged in with a username <username> and a password <password>
	When A user adds a product <productName> to the cart
	And Checkout that this product <productName> is added and submit this order
	And Populate Shipping information using <partialCountry> and <wantedCountry> and Place the Order
	Then Verify the message "THANKYOU FOR THE ORDER." is displayed
	And A browser is closed
	
Examples:
	| username					| password					| productName					| partialCountry					| wantedCountry					|
	| unknownxjk@gmail.com		| kojikurac123				| ZARA COAT 3					| yugo								| Yugoslavia					|