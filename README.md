# Chat App POE — Part 1: Registration & Login

A Java implementation of the registration and login feature for the Chat App POE,
built with unit-tested, version-controlled code as required by the brief.

## Structure
```
src/main/java/chatapp/Login.java      - Login class (registration & login logic)
src/test/java/chatapp/LoginTest.java  - JUnit 5 tests
pom.xml                               - Maven build configuration
.github/workflows/maven.yml           - CI: runs the test suite on every push
```

## Login class

| Method | Purpose |
|---|---|
| `checkUserName(String)` | Validates the username contains an underscore and is no more than 5 characters. |
| `checkPasswordComplexity(String)` | Validates the password is 8+ characters with a capital letter, a number, and a special character. |
| `checkCellPhoneNumber(String)` | Validates the number includes the +27 country code and is no more than 10 digits after it. |
| `registerUser(...)` | Runs the checks above and returns the relevant feedback message. |
| `loginUser(String, String)` | Verifies entered credentials against the registered ones. |
| `returnLoginStatus(boolean)` | Returns the welcome or failure message. |

## Running the tests

**NetBeans:** open the project (File → Open Project, select this folder), then
right-click the project → Test.

## Running the console app 


**Command line:**
```
mvn test
```

Tests also run automatically on GitHub via Actions on every push.
