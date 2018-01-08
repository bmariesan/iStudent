# iStudent
Design Patterns Course UBB 2017-2018

1. User management & Course subscriptions
2. Assignments, tests and gradings/ & Test/Assignment design
3. Analytics & Reporting
4. University Blog - push notifications etc.

# Build Status
[![wercker status](https://app.wercker.com/status/158f0fee4804c3da09b80f23ca8f29d6/m/master "wercker status")](https://app.wercker.com/project/byKey/158f0fee4804c3da09b80f23ca8f29d6)

# Project Details
## Prerequisites
- __[JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)__

## How to build and run the project?
### Windows
```java
step 1:
gradlew clean build
step 2:
gradlew bootRun
step 3:
http://localhost:8080
step 4:
Enjoy coding :)
```
### Linux/Mac OS
```java
step 1:
./gradlew clean build
step 2:
./gradlew bootRun
step 3:
http://localhost:8080
step 4:
Enjoy coding :)
```

# Logistics
## __[Slack channel](https://ubbdesignpatterns2017.slack.com/)__ ([Invitation link](https://join.slack.com/t/ubbdesignpatterns2017/shared_invite/enQtMjU4ODcyMDc3MjcwLWQ3ZmFmMDIzYmU4YmYwZGRkMWQxMTU1YjUyYTE3YmJlMWExMTAzY2JiYzY0MDdiN2VkMzVlMzc4ZGVjMGJhY2M))

## Courses
- __[Course 1](https://docs.google.com/presentation/d/1vQ-MAlstyvX_rapYVQ4uLOabJwdJ7010YurVI_bQYpM): Solid Principles and Creational Patterns (Factory, Builder, Prototype, Singleton)__
- __[Course 2](https://docs.google.com/presentation/d/1xZhmu2bT6TYEeAyY02-e5vRRglJ_5G-UnXmGUIlpNPg): Structural Patterns (Adapter, Bridge, Composite, Decorator, Facade, Proxy)__
- __[Course 3](https://docs.google.com/presentation/d/1udyA-FaIrRZ9EQUn-biKbpMH8yDvkEsIEr1CbYGMW7s): Behavioral Patterns (Chain of Responsibility, Command, Iterator, Mediator, Observer)__
- __[Course 4](https://docs.google.com/presentation/d/1fzrjC-HwMg7WEsOtEXUHubnNU1c8CU1OaFyv4UXRJfA): Behavioral Patterns (State, Strategy, Template)__
- __[Course 5](https://docs.google.com/presentation/d/1ST44IKG6YdCsIeFgGLhVFe7AAfv3q2Bbc5qe_WgXDQM): Recap (Factory, Builder, Singleton, Adapter, Composite, Proxy)__
- __[Course 6](https://docs.google.com/presentation/d/13_ti3b6dvfMjiNsu3JwDoGHoYVfboajH-log6ltRf3Q): Architectural Patterns (MVVM, MVP, MVC), JS Module Pattern__
- __[Course 7](https://docs.google.com/presentation/d/1Ve1qciit3QQGJtPLZeckjccqrAInAdheE9_R5_geVuE): Enterprise Integration Patterns__
- __[Course 8](https://docs.google.com/presentation/d/1TOsBMnRkrkTxVZ-2yUKr_jJ1RxCRb8g4koiShp-2lwM): Enterprise Integration Patterns: Messaging__

## __[Attendance status](https://docs.google.com/spreadsheets/d/1lzO3BhkjEk6xRPxoFb3Yq5RtlE43MPsmJ1ofIl_kk9M/edit?usp=sheets_home&ths=true)__ of students

## __[Self-scheduling for labs](https://docs.google.com/spreadsheets/d/16gnOL0lQRFnXmxHOGag_XkTWqVXekKKYTLMFK_mtWec/edit#gid=0)__, for a more balanced number of students at each laboratory

## __[Exams scheduling](https://goo.gl/forms/9ewYvWvFT6N06WlP2 ), to be filled out by 19th dec, end-of-day__

# Grading options:
Option 1:
- 50% individual presentations, during the semester
- 50% final presentation, with the whole project team (max 6 people), at the end of the semester, before the exam period, in the "Presesiune" period

Option 2 (date to be decided), in case a student is uneligible for the first option, or receives a grade under 5 on the first option:
- written exam at the end of the semester, in the "Sesiune" period

Option 3 (20 feb), in case the student fails first options or is caught cheating at Option 1:
- written exam after the exam period, in the "Restante" period

Bonus given for course attendance: max 2 points divided to the number of course attendances, added to the final exam grade and rounded up (ceiling rounding).

# 1. Option 1: 

## 1.1. Option 1, first half: Individual presentation during the semester requirements:

## 1.1.1. Requirements __[scheduling and buzzwords](https://docs.google.com/spreadsheets/d/16gnOL0lQRFnXmxHOGag_XkTWqVXekKKYTLMFK_mtWec/edit#gid=199462140)__
 - Each group can have 1 or 2 people presenting per laboratory.
 -- 1 person has a 10min timeslot for presenting.
 -- 2 people have a 20min timeslot for presenting.
 - If few people are presenting, the timeslots can vary.
 
## 1.1.2. Each presentation (for each person) must have the following:
### 1.1.2.1. Production usage of a Design Pattern 
 From repositories such as:
 - Spring Framework
 - JUnit framework
 - Any project dependencies used
 - [GitHub trending repos](https://github.com/trending/java)
 - Your code at work (if it is allowed)
 
### 1.1.2.2. Buzzword
 - Each buzzword can be presented by a maximum of 2 people, __from different groups__
 - The spreadsheet linked in the title header has the list of buzzwords, you can write your name to one to reserve it

### 1.1.2.3. Current project progress
 - Any design patterns used
 - Progress of functionality
 - Code explanations


# 1.2. Option 1, second half: Final presentation, with the whole team (max 6 people)
If a person from the team does not participate, that person will be graded using Option 2 (written exam)

## 1.2.1. Presentation requirements for the team
- Overall implementation of team task

## 1.2.2. Presentation requirements for each team member:
- Code contribution
- Used Design Patterns
- Purpose and added value of used Design Pattern
- Git statistics shown for contributions brought

# 2. Option 2: Written exam (sesiune)
Exam will be given across multiple rows of students to prevent fraud.

## 2.1. Exercise 1: Student is presented with a Non-Enterprise Design Pattern
### 2.1.1. Draw UML class diagram
### 2.1.2. Provide use cases (minimum 2 but will be specified on each exam variant)
### 2.1.3. Pseudocode implementation

## 2.2. Exercise 2: Student is given a business use case:
### 2.2.1. Name a Design Pattern which can be used to solve this business use case
### 2.2.2. Give reason and benefits for usage of this Pattern 
### 2.2.3. Draw UML class diagram
### 2.1.4. Pseudocode implementation

## 2.3. Exercise 3: Student is given an Enterprise business use case:
### 2.2.1. Name an Enterprise Integration Pattern which can be used to improve the messaging system
### 2.2.2. Explain (drawing+text) information flow with given Enterprise Integration Pattern
### 2.2.3. Give reason and benefits for usage of this Pattern

# 3. Option 3: Written exam (restanta) - same requirements as Option 2)


# Programare examene
- Colocviu, sambata, 27 ian, 8:00 - 14:00 (cu posibilitate de prelungire dupa 14:00, daca sunt oameni) (__[Programare](https://docs.google.com/spreadsheets/d/16gnOL0lQRFnXmxHOGag_XkTWqVXekKKYTLMFK_mtWec/edit?usp=sharing)__) 
- Examen, duminica, 11 feb, 10:00 - 13:00
- Restanta, duminica, 18 feb, 10:00 - 13:00 (cu posibilitate de schimbare pe 20 feb, care va fi anuntata)
