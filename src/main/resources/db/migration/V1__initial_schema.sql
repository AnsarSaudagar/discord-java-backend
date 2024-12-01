CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    display_name VARCHAR(255) ,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    dob DATE ,
    created_at DATETIME,
    updated_at DATETIME
);