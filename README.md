# BookCrosser

## Summary

BookCrosser is a book-exchanging online system that is designed to help readers to reach out to one another to buy, sell, exchange, or give away books for free. 
Users can create accounts, log in, and manage their profiles and adverts. Key features include creating and managing book adverts and browsing through adverts using preferred filters. 
User authentication is handled securely using OAuth2, their passwords are stored using Firebase Authentification.

## Pre-requisites

1. Java (JDK) 21 (IntelliJ might be able to install it automatically)
2. Docker-ready system (Docker Desktop on windows). It has to be running when the application is launched.

## Launching the application

Make sure the docker is running and launch the application. And launch the application

### Using Swagger

1. If willing to test the application with swagger. Go to: http://localhost:8080/swagger-ui/index.html#
2. Run the scripts.sh using  ./scripts.sh test@test.com tester within the git bash in the repository folder.
3. Copy the idToken that has been generated from the script and use it for the authorization.

### Using frontend

1. Clone repository https://github.com/RugileVa/BookExchange-front.git
2. Open terminal and input command _npm install_ ,to download all necessary libraries
3. Open new terminal and input command _ng serve_
4. Press h + enter to show help

For more info read BookExchange-front README file.







