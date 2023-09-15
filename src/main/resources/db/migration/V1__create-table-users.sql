create table users(
      id TEXT PRIMARY KEY ,
      name TEXT NOT NULL,
      email TEXT NOT NULL,
      password TEXT NOT NULL,
      balance FLOAT NOT NULL,
      role TEXT NOT NULL
);