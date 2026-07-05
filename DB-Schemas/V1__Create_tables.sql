USERS
=====================================

CREATE TABLE users(

id BIGINT PRIMARY KEY AUTO_INCREMENT,

first_name VARCHAR(100),

last_name VARCHAR(100),

email VARCHAR(150) UNIQUE,

password VARCHAR(255),

enabled BOOLEAN DEFAULT TRUE,

created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);
=====================================

ROLES
=====================================
CREATE TABLE roles(

id BIGINT PRIMARY KEY AUTO_INCREMENT,

role_name VARCHAR(50) UNIQUE

);


=====================================

USER ROLES
=====================================

CREATE TABLE user_roles(

user_id BIGINT,

role_id BIGINT,

PRIMARY KEY(user_id,role_id),

FOREIGN KEY(user_id)

REFERENCES users(id),

FOREIGN KEY(role_id)

REFERENCES roles(id)

);


