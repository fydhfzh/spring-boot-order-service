DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    role VARCHAR NOT NULL,
    user_id INTEGER NOT NULL,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW(),
    constraint fk_user_roles
        foreign key (user_id) references users(id)
);

INSERT INTO roles (role, user_id)
VALUES ('ROLE_ADMIN', 1);