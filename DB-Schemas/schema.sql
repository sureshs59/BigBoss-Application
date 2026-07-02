CREATE TABLE users(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100),
email VARCHAR(150) UNIQUE NOT NULL,
password VARCHAR(255) NOT NULL,
enabled BOOLEAN DEFAULT TRUE,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE roles(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
role_name VARCHAR(50) UNIQUE
);

CREATE TABLE user_roles(
user_id BIGINT,
role_id BIGINT,
PRIMARY KEY(user_id,role_id),
FOREIGN KEY(user_id) REFERENCES users(id),
FOREIGN KEY(role_id) REFERENCES roles(id)
);

CREATE TABLE contestants(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(100),
last_name VARCHAR(100),
age INT,
gender VARCHAR(20),
city VARCHAR(100),
state VARCHAR(100),
profession VARCHAR(100),
bio TEXT,
profile_photo VARCHAR(500),
active BOOLEAN DEFAULT TRUE,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE seasons(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
season_number INT UNIQUE,
title VARCHAR(100),
start_date DATE,
end_date DATE,
active BOOLEAN DEFAULT FALSE
);

CREATE TABLE weeks(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
season_id BIGINT,
week_number INT,
start_date DATE,
end_date DATE,
voting_open BOOLEAN DEFAULT FALSE,
FOREIGN KEY(season_id) REFERENCES seasons(id)
);

CREATE TABLE weekly_nominations(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
week_id BIGINT,
contestant_id BIGINT,
reason TEXT,
FOREIGN KEY(week_id) REFERENCES weeks(id),
FOREIGN KEY(contestant_id) REFERENCES contestants(id)
);

CREATE TABLE votes(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
user_id BIGINT,
contestant_id BIGINT,
week_id BIGINT,
vote_date DATE,
voted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ip_address VARCHAR(100),
device_id VARCHAR(150),
FOREIGN KEY(user_id) REFERENCES users(id),
FOREIGN KEY(contestant_id) REFERENCES contestants(id),
FOREIGN KEY(week_id) REFERENCES weeks(id),
UNIQUE(user_id,vote_date)
);

CREATE TABLE weekly_results(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
week_id BIGINT,
contestant_id BIGINT,
total_votes BIGINT,
ranking INT,
vote_percentage DECIMAL(5,2),
eliminated BOOLEAN,
FOREIGN KEY(week_id) REFERENCES weeks(id),
FOREIGN KEY(contestant_id) REFERENCES contestants(id)
);

CREATE TABLE prediction_history(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
week_id BIGINT,
contestant_id BIGINT,
predicted_probability DECIMAL(5,2),
confidence_score DECIMAL(5,2),
prediction_time TIMESTAMP,
model_version VARCHAR(50)
);

CREATE TABLE audit_logs(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
user_id BIGINT,
action VARCHAR(100),
entity_name VARCHAR(100),
entity_id BIGINT,
action_time TIMESTAMP,
ip_address VARCHAR(100)
);

CREATE INDEX idx_user_email ON users(email);
CREATE INDEX idx_vote_user ON votes(user_id);
CREATE INDEX idx_vote_week ON votes(week_id);
CREATE INDEX idx_vote_contestant ON votes(contestant_id);
CREATE INDEX idx_nomination_week ON weekly_nominations(week_id);
CREATE INDEX idx_results_week ON weekly_results(week_id);
