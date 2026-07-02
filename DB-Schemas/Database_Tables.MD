Database Tables
==========================================

users

roles

user_roles

contestants

seasons

weeks

weekly_nominations

votes

weekly_results

prediction_history

audit_logs

========================================
Table 1 — Users
========================================
CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id),
    UNIQUE KEY uk_users_email (email)
);


Table 2 ---Roles
=========================================

CREATE TABLE roles(id BIGINT NOT NULL, role_name VARCHAR(30) NOT NULL);

=========================================
Table 3 — User Roles
=========================================
Many-to-Many

Users

↓

ROLE_USER

ROLE_ADMIN

Table

user_roles

|

user_id
role_id


CREATE TABLE user_roles(user_id BIGINT NOT NULL, role_id VARCHAR(30) NOT NULL);
======================================

=========================================
Table 4 — Contestants
=========================================
| Column        | Type         |
| ------------- | ------------ |
| id            | BIGINT       |
| first_name    | VARCHAR(100) |
| last_name     | VARCHAR(100) |
| age           | INT          |
| gender        | VARCHAR(20)  |
| city          | VARCHAR(100) |
| state         | VARCHAR(100) |
| occupation    | VARCHAR(100) |
| biography     | TEXT         |
| profile_photo | VARCHAR(255) |
| active        | BOOLEAN      |
| created_at    | TIMESTAMP    |




CREATE TABLE contestants (
    id BIGINT NOT NULL ,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(20) NOT NULL,
    city VARCHAR(100),
    state VARCHAR(100),
    occupation VARCHAR(100),
    biography TEXT,
    profile_photo VARCHAR(255),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

=========================================
Table 5 — Seasons
=========================================

| Column        | Type         |
| ------------- | ------------ |
| id            | BIGINT       |
| season_number | INT          |
| title         | VARCHAR(100) |
| start_date    | DATE         |
| end_date      | DATE         |
| active        | BOOLEAN      |


CREATE TABLE seasons (
    id BIGINT NOT NULL ,
    season_number INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

Example

	Season 18

	Season 19
	
=========================================	
Table 6 — Weeks	
=========================================

| Column      | Type    |
| ----------- | ------- |
| id          | BIGINT  |
| season_id   | FK      |
| week_number | INT     |
| start_date  | DATE    |
| end_date    | DATE    |
| voting_open | BOOLEAN |


CREATE TABLE weeks (
    id BIGINT NOT NULL,
    season_id BIGINT NOT NULL,
    week_number INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    voting_open BOOLEAN NOT NULL DEFAULT FALSE,

    PRIMARY KEY (id),

    CONSTRAINT fk_weeks_season
        FOREIGN KEY (season_id)
        REFERENCES seasons(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

========================================
Table 7 — Weekly Nominations
========================================
   weekly_nominations

| Column            | Type   |
| ----------------- | ------ |
| id                | BIGINT |
| week_id           | FK     |
| contestant_id     | FK     |
| nomination_reason | TEXT   |



CREATE TABLE weekly_nominations (
    id BIGINT NOT NULL,
    week_id BIGINT NOT NULL,
    contestant_id BIGINT NOT NULL,
    nomination_reason TEXT,

    PRIMARY KEY (id),

    CONSTRAINT fk_weekly_nominations_week
        FOREIGN KEY (week_id)
        REFERENCES weeks(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_weekly_nominations_contestant
        FOREIGN KEY (contestant_id)
        REFERENCES contestants(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
========================================
Table 8 — Votes
========================================


| Column        | Type      |
| ------------- | --------- |
| id            | BIGINT    |
| user_id       | FK        |
| contestant_id | FK        |
| week_id       | FK        |
| voted_at      | TIMESTAMP |

CREATE TABLE votes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    contestant_id BIGINT NOT NULL,
    week_id BIGINT NOT NULL,
    voted_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (id),

    CONSTRAINT fk_votes_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_votes_contestant
        FOREIGN KEY (contestant_id)
        REFERENCES contestants(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_votes_week
        FOREIGN KEY (week_id)
        REFERENCES weeks(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

ALTER TABLE votes
ADD CONSTRAINT uk_votes_user_contestant_week
UNIQUE (user_id, contestant_id, week_id);

ALTER TABLE votes
ADD CONSTRAINT uk_votes_user_week
UNIQUE (user_id, week_id);

========================================
Table 9 — Weekly Results
========================================


| Column          | Type         |
| --------------- | ------------ |
| id              | BIGINT       |
| week_id         | FK           |
| contestant_id   | FK           |
| total_votes     | BIGINT       |
| ranking         | INT          |     
| vote_percentage | DECIMAL(5,2) |
| eliminated      | BOOLEAN      |   table name --> weekly_results 

CREATE TABLE weekly_results (
    id BIGINT NOT NULL AUTO_INCREMENT,
    week_id BIGINT NOT NULL,
    contestant_id BIGINT NOT NULL,
    total_votes BIGINT NOT NULL DEFAULT 0,
    ranking INT NOT NULL,
    vote_percentage DECIMAL(5,2) NOT NULL DEFAULT 0.00,
    eliminated BOOLEAN NOT NULL DEFAULT FALSE,

    PRIMARY KEY (id),

    CONSTRAINT fk_weekly_results_week
        FOREIGN KEY (week_id)
        REFERENCES weeks(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_weekly_results_contestant
        FOREIGN KEY (contestant_id)
        REFERENCES contestants(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT uk_weekly_results_week_contestant
        UNIQUE (week_id, contestant_id)
);

========================================
Table 10 — Prediction History
========================================

| Column                | Type         |
| --------------------- | ------------ |
| id                    | BIGINT       |
| week_id               | FK           |
| contestant_id         | FK           |
| predicted_probability | DECIMAL(5,2) |
| confidence_score      | DECIMAL(5,2) |
| prediction_time       | TIMESTAMP    |
| model_version         | VARCHAR(50)  |


CREATE TABLE prediction_history (
    id BIGINT NOT NULL AUTO_INCREMENT,
    week_id BIGINT NOT NULL,
    contestant_id BIGINT NOT NULL,
    predicted_probability DECIMAL(5,2) NOT NULL,
    confidence_score DECIMAL(5,2) NOT NULL,
    prediction_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    model_version VARCHAR(50) NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT fk_prediction_history_week
        FOREIGN KEY (week_id)
        REFERENCES weeks(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_prediction_history_contestant
        FOREIGN KEY (contestant_id)
        REFERENCES contestants(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

========================================
Table 11 — Audit Logs
========================================

| Column      | Type         |
| ----------- | ------------ |
| id          | BIGINT       |
| user_id     | FK           |
| action      | VARCHAR(100) |
| entity_name | VARCHAR(100) |
| entity_id   | BIGINT       |
| action_time | TIMESTAMP    |
| ip_address  | VARCHAR(45)  |


CREATE TABLE audit_logs (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    action VARCHAR(100) NOT NULL,
    entity_name VARCHAR(100) NOT NULL,
    entity_id BIGINT NOT NULL,
    action_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ip_address VARCHAR(45) NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT fk_audit_logs_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


roles
   │
   │ M:N
   │
user_roles
   │
users

season
   │
   │1
   │
weeks
   │
   │1
   │
weekly_nominations
   │
   │N
contestants

votes
 ├──────── users
 ├──────── contestants
 └──────── weeks

weekly_results
 ├──────── weeks
 └──────── contestants

prediction_history
 ├──────── weeks
 └──────── contestants
 
 
 Indexing Strategy

To keep voting fast as data grows:

users(email) — unique index
contestants(active)
weeks(season_id, week_number)
weekly_nominations(week_id)
votes(week_id, contestant_id)
votes(user_id, week_id) (or the appropriate unique constraint)
weekly_results(week_id, ranking)
Future Enhancements

The schema is designed so we can later add:

OTP login
Social login
Live vote streaming (WebSockets)
AI sentiment analysis from social media
Image uploads to cloud storage
Notifications
Admin analytics
Multiple reality show formats
Multi-language support

Updated Voting Rule
Rule

A registered user can:

✅ Vote once per calendar day
✅ Vote for only one nominated contestant
✅ Vote only when voting is open
✅ Vote only for contestants nominated for the current week

Examples:

User	Date					Vote				Allowed
John	2026-07-01	Contestant 		A	✅
John	2026-07-01	Contestant 		B	❌ Already voted today
John	2026-07-02	Contestant 		C	✅ New day
Mary	2026-07-01	Contestant 		A	✅


Updated Vote Table

Instead of relying only on voted_at, we'll explicitly store the voting date to simplify uniqueness checks and reporting.

votes

| Column          | Type                | Description                |
| -------------     | ------------        | -------------------------- |
| id            	   | BIGINT             | Primary Key                |
| user_id          | BIGINT             | FK → users                 |
| contestant_id  | BIGINT             | FK → contestants           |
| week_id         | BIGINT             | FK → weeks                 |
| vote_date      | DATE                | Calendar day of the vote   |
| voted_at        | TIMESTAMP      | Exact timestamp            |
| ip_address     | VARCHAR(45)   | Optional (fraud detection) |
| device_id       | VARCHAR(100) | Optional (fraud detection) |

Database Constraint

We'll enforce one vote per day directly in MySQL:

ALTER TABLE votes
ADD CONSTRAINT uk_vote_per_day
UNIQUE (user_id, vote_date);

This guarantees that even if two requests arrive simultaneously, the database prevents duplicate daily votes.


Backend Validation Flow
-------------------------------------
User Clicks Vote
        │
        ▼
Is Voting Open?
        │
      Yes
        │
        ▼
Is Contestant Nominated?
        │
      Yes
        │
        ▼
Has User Already Voted Today?
        │
   ┌────┴────┐
   │         │
  Yes        No
   │         │
 Reject   Save Vote
 
 
 
 AI Benefits

Daily voting gives us much richer analytics than weekly totals.

We can calculate:

Daily vote growth
Vote momentum
Seven-day moving averages
Daily prediction confidence
Vote velocity (votes/hour)
Peak voting times
Weekend vs. weekday trends
Historical participation

These features will significantly improve the quality of the AI prediction model.

Dashboard Analytics

We'll be able to display:

Today's Total Votes
Votes by Hour
Daily Leaderboard
Contestant Vote Trend
Winning Probability
Prediction Confidence
Most Active Voting Day
Total Registered Users
Daily Participation Rate


Enterprise Enhancements

To make this production-ready, I recommend adding a few more fields to the votes table:

Column					Purpose
status					ACTIVE, CANCELLED (supports moderation or rollback)
source					WEB, MOBILE_APP, ADMIN (tracks where the vote originated)
created_by			For administrative actions, if any
created_at				Record creation timestamp
updated_at			Last modification timestamp

