CREATE TABLE IF NOT EXISTS STORE (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(50) NOT NULL,
    DESCRIPTION VARCHAR(200),
    CREATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);