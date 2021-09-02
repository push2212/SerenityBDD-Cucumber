cd C:\MangoAppsAutomation\
mvn clean verify  serenity:aggregate serenity:aggregate -Dcucumber.options="--tags '@ADomain or @Departments or @Groups or @Modules or @People or @Projects or @Users or @ZDependant or @ZFireFox'" > Report.txt
pause