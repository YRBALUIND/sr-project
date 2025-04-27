Analyzer code base which analyzes  organizational structure and identify potential improvements.

Code has been verified by writing test cases of much possible cases, working as expected, also verified with data provided .

Building the Application
**mvn clean package
This will compile the code, run the tests, and create an executable JAR file in the target directory.



Running the Application
java -jar target/org-structure-analyzer-1.0-SNAPSHOT.jar path/to/employees.csv



Output
The application will print to the console:

Managers earning less than they should, and by how much
Managers earning more than they should, and by how much
Employees with reporting lines that are too long, and by how much




Assumptions

All employees (including the CEO) are listed in the CSV file

Each employee has a unique ID

The reporting structure forms a tree (no cycles)

If a manager has no subordinates, they are not subject to salary ratio constraints

The CSV file is well-formed and contains the expected columns
