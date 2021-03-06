CREATE TABLE IF NOT EXISTS STORE (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(50) NOT NULL,
    DESCRIPTION VARCHAR(200),
    CREATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS STAFF (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    LAST_NAME VARCHAR(50) NOT NULL,
    FIRST_NAME VARCHAR(50) NOT NULL,
    EMAIL VARCHAR(50) NOT NULL UNIQUE,
    PASSWORD VARCHAR(300) NOT NULL,
    BIRTHDAY TIMESTAMP NOT NULL,
    CREATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    STORE_ID INT NOT NULL,
    FOREIGN KEY (STORE_ID) REFERENCES STORE (ID) ON DELETE CASCADE
);
