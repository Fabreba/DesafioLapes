create table usuario(
      id TEXT PRIMARY KEY ,
      nome TEXT NOT NULL,
      email TEXT NOT NULL,
      senha TEXT NOT NULL,
      saldo FLOAT NOT NULL,
      cargo TEXT NOT NULL
);