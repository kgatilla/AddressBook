# AddressBook

AddressBook Exercise

Build with

`gradle build`


Input: Takes one Address Book file path parameter.

Execution from this folder:

`java -jar build/libs/AddressBook-0.1.jar AddressBook.txt`

Output:
Attempts to answers 4 questions:

   1. Number of Female and Male persons in the book
   2. Name of oldest person in the book
   3. How many days older is Bill than Paul?
   4. How many days older is Gemma than Sarah?

If any of the questions does not have an answer the answer is omitted.

Limitations: processing is single threaded.