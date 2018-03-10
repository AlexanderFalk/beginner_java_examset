# Examset for new Computer Science Students

#### Prerequisite
You need the [following jar files](https://drive.google.com/open?id=14NKnQfmVI4mOnfIa_QFr0YgeM05d_mE6) and export them into your project before you can use this exam set.  


This repository contains an exam set for new students at the Computer Science undergrad at CPHBusiness. The exam set is for Java Development and tests the students in their newly learned abilities. 

The data files:

- marketdata - Not used
- marketsummary - Not used
- currencies - Used as data for the currency.java class
- datafile1 - Used for data driven testing
- datafile2 - Used for data driven testing

are used as a part of the exam set. 

Every method that the student has to complete will have a test combined to it, so the implementation from the student can be validated.

There are several classes inside the src folder. Below is an overview of which is used to what: 

- Currency.java - Object class for currencies (the currency.json file is from bittrex.com and it's a copy from an API call. It is in json and it's being converted from json to an objectarray to help the student)
- ExchangeAPI.java - The interface of the exercise methods
- Helper.java - The helper class to the student that might become useful
- Implementation.java - An example of the completed exercises
- Market.java - Object class for markets (the marketdata.json file is from bittrex.com and it's a copy from an API call. It is in json and it's being converted from json to an objectarray to help the student)
- Trader.java - Object class for Trader

In the test folder a java test file is stored and can be used as validation of the methods.
