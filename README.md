# Chat App POE — Part 1 (Registration & Login)

## What's in here
- `src/main/java/chatapp/Login.java` — the `Login` class (checkUserName, checkPasswordComplexity, checkCellPhoneNumber, registerUser, loginUser, returnLoginStatus).
- `src/test/java/chatapp/LoginTest.java` — JUnit 5 tests using the exact test data from the brief.
- `pom.xml` — Maven config (this is what lets NetBeans and GitHub Actions both build/test it the same way).
- `.github/workflows/maven.yml` — GitHub Actions workflow: runs `mvn test` automatically on every push, which satisfies the "automate your tests" requirement.

## ⚠️ One assumption to check against your actual brief
Your brief's `returnLoginStatus` welcome message needs `<user first name>` and `<user last name>`,
but the methods table you photographed doesn't show where those get captured. I added
`firstName`/`lastName` params to `registerUser(...)` to make that message work. If your
brief captures first/last name somewhere else (a separate screen/class), let me know and
I'll adjust the signature — it's a 2-minute change.

## 1. Open it in NetBeans
1. Open NetBeans → File → Open Project → select the `chatapp-poe` folder (NetBeans recognises the `pom.xml` and imports it as a Maven project automatically).
2. Right-click the project → Test → runs all JUnit tests.

## 2. Set up Git + GitHub (from scratch)
```bash
cd chatapp-poe
git init
git add .
git commit -m "Part 1: Login class and unit tests"
```
Then on github.com: New repository → don't initialise with a README (you already have one) → copy the commands it gives you, e.g.:
```bash
git remote add origin https://github.com/<your-username>/chatapp-poe.git
git branch -M main
git push -u origin main
```

## 3. Confirm automated testing is working
Push a commit, then check the **Actions** tab on your GitHub repo — you should see a green
checkmark once `mvn test` runs successfully. That satisfies "automate these tests so your
code is tested with every change you make."

## 4. Submitting
Check your brief for whether they want the GitHub repo link, a zip of the project, or both —
that detail wasn't visible on the pages you sent through.
