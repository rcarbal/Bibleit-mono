# Bibleit-mono
Monolith application that runs the domain bible-it.com, uses AWS and Spring libraries.

## Live URL
[Bible-it.com](https://www.bible-it.com/)

## Features
- Contains RESTful endpoints to return complete JSON for the NIV bible. 
- Previews recommended questions based on user input.
- Presents answer for selected question including supporting verses.

## Implementation
- Java 11
- Built using Spring framework and Service-Oriented architecture 
- Client uses Bootstrap 4.0 and thymeleaf libraries
- Connects to AWS using the AWS-JAVA-SDK
- Parses JSON using Googles' JSON-simple
- Run in docker container
- Uses Apache Commons Levenshtein-Distance to mesure string matching

## Search
![search](https://user-images.githubusercontent.com/20021751/87258880-e28a3d80-c45b-11ea-8915-9c254a7ca7c6.png)

## Search Result
![search-result](https://user-images.githubusercontent.com/20021751/87258888-f170f000-c45b-11ea-9ab5-71b3a4cfb0f0.png)
