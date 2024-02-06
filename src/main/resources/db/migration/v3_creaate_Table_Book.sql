CREATE TABLE books (
  id SERIAL PRIMARY KEY not null,
  author TEXT,
  launch_date TIMESTAMP(6) NOT NULL,
  price DECIMAL(65,2) NOT NULL,
  title TEXT
);
