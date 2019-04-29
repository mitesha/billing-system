# billing-system
Demo application for billing system

## Pre-requisites
* Java 8
* Apache maven (Build tool)
* SonarQube (running on local machine)

## Project setup
* Clone the project on your local machine
* Navigate to <root directory>/billing-system
* Below commands shall be executed on command prompt

## Building code and executing test suits
`mvn clean install`

## Executing generated jar
`java -jar target/billing-system-0.0.1-SNAPSHOT.jar`

## Code quality and test coverage
`mvn sonar:sonar -Dsonar.host.url=http://localhost:9000` (Assuming sonarqube is running on http://localhost:9000 )

![Code quality](https://github.com/mitesha/billing-system/blob/master/Code_Quality_with_Code_Coverage.png)
