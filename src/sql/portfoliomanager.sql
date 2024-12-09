drop database portfoliomanager;

CREATE DATABASE IF NOT EXISTS portfoliomanager;
use portfoliomanager;

CREATE TABLE users (
	id INT PRIMARY KEY auto_increment, 
	fullname VARCHAR(30) NOT NULL, 
    email VARCHAR(100),
    phone_number VARCHAR(20)
);

create table account_details (
	id int primary key auto_increment,
    account_type varchar(30),
	balance double,
	currency varchar(10),
	user_id int,
    FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE Bought_Stocks
(
   stock_name     		varchar(40) 	    NOT NULL,
   amount_invested    	double          	NOT NULL,
   ticker_symbol       	varchar(40)       	NOT NULL,
   volume          		int(40)          	NOT NULL,
   PRIMARY KEY(ticker_symbol)
);

CREATE TABLE info_stocks (
	ticker_symbol varchar(40) NOT NULL,
    date_time DATETIME NOT NULL,
    open_price double,
    close_price double,
    low_price double,
    high_price double,
    volume int,
    PRIMARY KEY(ticker_symbol, date_time)

);

CREATE TABLE stocks (
	symbol varchar(500) PRIMARY KEY,
    sname varchar(500),
    currency varchar(10),
	stock_exchange varchar(60),
    mic_code varchar(60),
    country varchar(60),
    stock_type varchar(60)
);

CREATE TABLE watchlist(
    ticker_symbol varchar(500) PRIMARY KEY,
    FOREIGN KEY(ticker_symbol) REFERENCES stocks(symbol)
);


insert into users values(1, "John Smith", 'john@example.com', '123-456-7890');

insert into account_details values (1, "CHEQ", 100000, "USD", 1);

INSERT INTO Bought_Stocks VALUES('Tesla Inc', 100.00, 'TSLA',  20);
INSERT INTO Bought_Stocks VALUES('Alphabet Inc Class A', 200.00, 'GOOGL',  13);
INSERT INTO Bought_Stocks VALUES('Amazon.com, Inc.', 300.00, 'AMZN',  12);

use portfoliomanager;

