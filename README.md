# RedKing
## Summary Description

A Game similar to BlackJack, with slight alteration to the rules to win. As with traditional blackjack, users play against the dealer in a one-on-one, turn based scenario, to draw cards from the deck until the cumulative value on either side reaches 21 or over. The player may 'stand' to hold the value of their cards and pass the turn to the dealer. Which ever hand is closest to 21 wins.

## Features

1. Users may register for a new account and log into the system with the credentials provided
2. Users may deposit/withdraw money into their account
3. Users may start a new game which initializes a new deck of 52 shuffled cards
4. Users may display the highscores board which displays all users in the system and their scores/money value
5. Users can load a previous deck that they have initialized previously, and continue playing the deck
6. during each game, users may bet a certain amount of money. If they win the round, they will receive double the amount the have betted
7. user's may withdraw their money at the homepage

## Todo List
- Implement admin features to monitor users for suspicous activity
- Add multipler features 
- Implement different difficulty settings that will adjust the winnings multiplier (for instance: hiding dealer card values during the player's turn, multiple dealers, initializing a deck of more than 52 cards to reduce the chance of counting cards to win)
- Track the amount of wins and losses associated with the player  

## Technologies Used
- Javalin, version 4.1.1
- Java, version 1.8.0
- slf4j version 1.7.32
- React
- AWS S3 Bucket (Hosts Front-End)
- AWS EC2 (Hosts Back-End)
- Hibernate
- Spring Boot
- Spring Data
- PostgreSQL
- Mockito
- MockMVC
- JUnit

## Getting Started
1. Database, Back-End, Front-End is hosted on Amazon Web Service wth RDS, EC2, and S3 bucket respectively
2. Follow the link to the launch page when the EC2 server is up at:<br/> http://redking-frontend.s3-website.us-east-2.amazonaws.com/login 

## Usage
1a. Players may login or register a new account<br/>
![image](https://user-images.githubusercontent.com/101683611/172219055-bf0743e1-457b-47c3-87a3-1841dd598b06.png)<br/><br/>
1b. Registration page after clicking on "Not Registered Yet"
![image](https://user-images.githubusercontent.com/101683611/172267260-024bcf45-b544-4b47-8de3-3ee974a9b2cf.png)<br/><br/>
2a. After loging players can start a new game which initializes a deck of 52 cards and alternatively deals 2 cards to the player and 2 cards to the dealer
![image](https://user-images.githubusercontent.com/101683611/172511233-a72a0ac9-c363-4710-b5de-6f60b49690f1.png)<br/><br/>
2b. When the game starts, bets are locked!
![image](https://user-images.githubusercontent.com/101683611/172266967-fda92b67-846c-4e9b-96c1-667bbfaaa8c5.png)<br/><br/>
3. When the player clicks on "hit", a card is drawn from the deck and rendered on the screen, the total value is calculated and displayed, if the player "busts" (value of 22 or more), the turn is given to the dealer and the dealer draws its cards
![image](https://user-images.githubusercontent.com/101683611/172511586-5233f8fa-99b5-4ca4-819d-726433923aca.png)<br/><br/>
4. If the player clicks on "stand", the turn will be passed to the dealer and the dealer will draw until it has a hand value of 17 or more
5. Clicking on next will start a new round, on the same deck of 52 cards, the deck will be reinitialized at 10 or less cards and shuffled
![image](https://user-images.githubusercontent.com/101683611/172266261-9acb9f9d-9aba-4d65-881d-d3ee90ba4e91.png)<br/><br/>
6. Clicking on the "money" button in the nav bar will bring up a form and any amount can be added to the players account
![image](https://user-images.githubusercontent.com/101683611/172266926-c7410081-75e1-4b56-84a3-4d0deaf63d66.png)<br/>
7. When the round finishes, the player can make a bet based on how much money they have. The player may not bet 100% of their money or bet an amount greater than what they have
![image](https://user-images.githubusercontent.com/101683611/172266990-56f0e6e0-6af4-41dc-9e8c-1ad7d4dfb181.png)<br/>
![image](https://user-images.githubusercontent.com/101683611/172267101-63ccca7a-00d7-4a4d-86ed-40ea437941f8.png)<br/><br/>
8. The after quiting the game, the player can view their score (in terms of money) and compare their score with other players in the system by clicking the score button
![image](https://user-images.githubusercontent.com/101683611/172273833-aba08df6-a6a3-4f02-81ae-4893b16f9f06.png)<br/><br/>
9. To Update the user infomation (username, first name, last name ... ), the user may click on their name on the top left corner to bring up a form
![image](https://user-images.githubusercontent.com/101683611/172514313-a7b3ec61-793a-4064-98fd-8d5694df9130.png)<br/><br/>








