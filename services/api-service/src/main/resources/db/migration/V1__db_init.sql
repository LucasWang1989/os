-- User
CREATE TABLE os_auth_user (
                        F_ID       BIGINT PRIMARY KEY AUTO_INCREMENT,
                        F_USERNAME VARCHAR(50)  NOT NULL UNIQUE,
                        F_PASSWORD VARCHAR(100) NOT NULL,
                        F_ENABLED  TINYINT      NOT NULL DEFAULT 1   -- 1-Y 0-N
);

-- Role
CREATE TABLE os_auth_role (
                        F_ID          BIGINT PRIMARY KEY AUTO_INCREMENT,
                        F_ROLE_NAME   VARCHAR(20)  NOT NULL UNIQUE,  -- ADMIN / USER
                        DESCRIPTION   VARCHAR(100)                   -- Role desc
);

-- The relationships between users and roles
CREATE TABLE os_auth_user_role (
                             F_USER_ID BIGINT NOT NULL,
                             F_ROLE_ID BIGINT NOT NULL,
                             PRIMARY KEY (F_USER_ID, F_ROLE_ID)
);

-- Initial role data
INSERT INTO os_auth_role(F_ROLE_NAME, DESCRIPTION) VALUES ('ADMIN', 'Administrator');
INSERT INTO os_auth_role(F_ROLE_NAME, DESCRIPTION) VALUES ('USER',  'User');

-- Initial default test account (password 123456, BCrypt）
INSERT INTO os_auth_user(F_USERNAME, F_PASSWORD) VALUES
    ('admin', '$2a$10$N8SAnNrwAgUh7bqGmUQ3kuUKNfnl/2Wywj3F4pIwXFlc.x1Do/Neq');
INSERT INTO os_auth_user(F_USERNAME, F_PASSWORD) VALUES
    ('user1', '$2a$10$N8SAnNrwAgUh7bqGmUQ3kuUKNfnl/2Wywj3F4pIwXFlc.x1Do/Neq');

-- Set admin role to admin
INSERT INTO os_auth_user_role(F_USER_ID, F_ROLE_ID) VALUES (1, 1);
-- Set user role to user1
INSERT INTO os_auth_user_role(F_USER_ID, F_ROLE_ID) VALUES (2, 2);
