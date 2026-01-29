CREATE TABLE IF NOT EXISTS users (

    -- Identifiers
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id      BINARY(16) NOT NULL,

    -- Personal Information
    first_name   VARCHAR(255) NOT NULL,
    last_name    VARCHAR(255) NOT NULL,
    email        VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,

    -- Role and Security
    password     VARCHAR(255) NOT NULL,
    user_role    ENUM('ROLE_ADMIN', 'ROLE_USER') NOT NULL,

    -- MetaData
    created_at   DATETIME(6) NULL,

    CONSTRAINT UK_email UNIQUE (email),
    CONSTRAINT UK_user_id UNIQUE (user_id)

);
