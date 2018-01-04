ALTER TABLE cards
  DROP CONSTRAINT IF EXISTS cards_users_fk;

ALTER TABLE cards
  ADD CONSTRAINT cards_users_fk FOREIGN KEY (user_id) REFERENCES users (id);