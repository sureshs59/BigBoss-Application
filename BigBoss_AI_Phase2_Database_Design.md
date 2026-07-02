# BigBoss AI Voting Application - Phase 2 Database Design

## Project Structure

``` text
bigboss-ai/
├── backend/
├── frontend-angular/
├── database/
│   ├── schema.sql
│   ├── data.sql
│   ├── views.sql
│   ├── procedures.sql
│   ├── triggers.sql
│   └── README.md
├── docker/
└── docs/
```

## Tables

-   users
-   roles
-   user_roles
-   contestants
-   seasons
-   weeks
-   weekly_nominations
-   votes
-   weekly_results
-   prediction_history
-   audit_logs

## Business Rule

-   One vote per user per calendar day.
-   User may vote only when voting is open.
-   User may vote only for nominated contestants.

## ER (simplified)

``` text
USERS--<USER_ROLES>--ROLES

SEASONS -> WEEKS -> WEEKLY_NOMINATIONS -> CONTESTANTS
USERS -> VOTES <- CONTESTANTS
WEEKS -> VOTES
WEEKS -> WEEKLY_RESULTS <- CONTESTANTS
WEEKS -> PREDICTION_HISTORY <- CONTESTANTS
```

## Indexes

-   users(email)
-   votes(user_id)
-   votes(week_id)
-   votes(contestant_id)
-   weekly_nominations(week_id)
-   weekly_results(week_id)

## Initial Data

Roles: - ROLE_ADMIN - ROLE_USER - ROLE_MODERATOR

Sample contestants: John, David, Alex, Mike, Sara, Emily, Sophia, Chris,
Kevin, Jennifer

## Future Database Objects

Views: - vw_daily_votes - vw_leaderboard - vw_weekly_results -
vw_prediction_summary

Stored Procedures: - sp_cast_vote - sp_close_voting -
sp_generate_results - sp_generate_prediction - sp_start_new_week

Triggers: - BEFORE INSERT Vote - AFTER INSERT Vote - AFTER UPDATE
Result - AFTER DELETE Contestant
