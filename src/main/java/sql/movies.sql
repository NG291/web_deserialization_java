use moviesystem;
CREATE TABLE Movies (
                       movieId INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       posterPath VARCHAR(255),
                       timeline DATE,
                       description TEXT,
                       isCurrentlyShowing BOOLEAN,
                       duration VARCHAR(50),
                       Base_Price INT
);
CREATE TABLE Users (
                      user_id INT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      email VARCHAR(255),
                      token VARCHAR(255)
);

CREATE TABLE Ticket (
                        ticket_id int PRIMARY KEY,
                        movieId INT NOT NULL,
                        showTime DATETIME,
                        cinema VARCHAR(255),
                        price DOUBLE,
                        FOREIGN KEY (movieId) REFERENCES Movies(movieId)
);
CREATE TABLE `Order` (
                         orderId INT AUTO_INCREMENT PRIMARY KEY,
                         customerId INT NOT NULL,
                         ticketId int NOT NULL,
                         FOREIGN KEY (customerId) REFERENCES Users(user_Id),
                         FOREIGN KEY (ticketId) REFERENCES Ticket(ticket_id)
);
Alter TABLE `order` modify ticketId int;
CREATE TABLE Ticket_Seats (
                              ticketId INT NOT NULL,
                              seatNumber VARCHAR(50) NOT NULL,
                              PRIMARY KEY (ticketId, seatNumber),
                              FOREIGN KEY (ticketId) REFERENCES Ticket(ticket_id) ON DELETE CASCADE
);
CREATE TABLE Tokens (
                        token_id INT AUTO_INCREMENT PRIMARY KEY,
                        token varchar(255),
                        user_id INT,
                        FOREIGN KEY (user_id) REFERENCES Users(user_id)
);