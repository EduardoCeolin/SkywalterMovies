create table movies (
id UUID primary key,
title VARCHAR(30) NOT NULL,
release DATE NOT NULL,
synopsis VARCHAR NOT NULL,
user_rating int
);