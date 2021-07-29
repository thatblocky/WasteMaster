# WasteMaster
Waste Management program
Problem: We often see that people don’t recycle their waste properly, this app helps a person do so correctly.

Solution: This program allows a user to enter their respective waste, if we shall find it in our waste records, we shall return all information regarding the waste and what price it would be sold at for its respective quantity.

Research done: How to read and write to txt files in java using filewriter. Other forms of storage of information like json, csv files. How to use Javadoc to generate a developer documentation for my program

Concepts used: Method calling, String handling, txt file writing, reading, Menu, using regex to replace all digits in a string.

Description:


The user is greeted with a menu asking them what they want to use the program for, the three options being:
1.	Add a new waste
2.	Add a new category and corresponding bin
3.	Enter the waste a user has and find out what price they can recycle it for

1.	Add a new waste:
•	User is prompted to enter the name of waste, category of waste, price of waste
•	Information is passed onto a second method which writes to a master waste file
2.	Add a new category
•	User is prompted to enter to name of category, bin color
•	Information is passed onto a second method which writes to a category file
3.	Enter a waste
•	User is prompted to enter waste name, quantity
•	Program returns category, value of waste, which bin to put it in
