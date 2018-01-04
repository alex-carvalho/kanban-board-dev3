ALTER TABLE public.users
  ADD CONSTRAINT users_email_uk UNIQUE (email);