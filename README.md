## Introduction
This is a Java application to fetch Most Active Cookies by Specific Date

## Work flow
Command Line Arguments : -f filename.csv -d date
This gets csv file name and a date as Command Line Arguments and validates it.
The Application reads the csv file and fetch the cookies which occurred frequently on the date given in command line arguments

## Explanation
Once we run the application, the command line arguments are validated by length, date and parameters.
Post Validation, the csv file is read from the resources folder (we can add more csv files under it / we can customize the inputs in the existing cookies.csv file)

CookieLogEvent - class which consist of LocalDate value of timestamp, String cookie, timestamp for future purpose actual timestamp value received.
-hashcode and equals implemented using cookies,date since we are focused on cookies based on a specific date. 
CookieLog - class which holds two maps 
- cookieCounter which holds CookieLogEvent as key and Integer value for counter
- dateCookiesMap which holds CookieLogs with Date as key and value as list of cookies

Custom Exceptions :
InvalidArgumentsException - if the Arguments are invalid or missing
DateNotFoundException - if the date provided is not found in the cookies log file

## Unit Testing - Junits
src/test/java/CookieLogTests.java contains all the Junit test cases required for testing.
Run the CookieLogTests class to test Junits.

## Build and run
The following command install the dependencies needed for the application and build it.
mvn clean install

Run as Java Application using command line arguments. Below is an example of command line arguments:
-f cookies.csv -d 2018-12-09




