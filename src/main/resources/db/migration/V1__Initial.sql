CREATE TABLE users (
  id         INT4         NOT NULL,
  email      VARCHAR(100) NOT NULL,
  password   VARCHAR(100) NOT NULL,
  first_name VARCHAR(100) NOT NULL,
  last_name  VARCHAR(100) NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE cards (
  id          INT4         NOT NULL,
  description TEXT         NULL,
  state       VARCHAR(100) NOT NULL,
  title       VARCHAR(200) NOT NULL,
  user_id     INT4         NOT NULL,
  CONSTRAINT cards_pkey PRIMARY KEY (id)
);

ALTER TABLE cards
  ADD CONSTRAINT cards_users_fk FOREIGN KEY (id) REFERENCES users (id);
