cd C:\MangoAppsAutomation\
mvn clean verify  serenity:aggregate -Dcucumber.options="--tags @LMS" > Report.txt
pause