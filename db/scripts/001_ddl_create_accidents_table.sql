  CREATE TABLE accidents (
  id serial primary key,
  name text,
  text text,
  address text,
  accident_type_id int REFERENCES accident_type(id)
);