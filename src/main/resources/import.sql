-- USERS
INSERT INTO users (name, email, password, created_at) VALUES ('Ruan Developer', 'ruan@dev.com', '123456', CURRENT_TIMESTAMP);
INSERT INTO users (name, email, password, created_at) VALUES ('Usuario Teste', 'teste@email.com', '123456', CURRENT_TIMESTAMP);

-- INCOME
INSERT INTO transactions (description, amount, date, type, user_id)
VALUES ('Salário Mensal', 5000.00, '2025-01-05', 'INCOME', 1);

INSERT INTO transactions (description, amount, date, type, user_id)
VALUES ('Freelance Projeto Java', 1500.00, '2025-01-15', 'INCOME', 1);

-- EXPENSE
INSERT INTO transactions (description, amount, date, type, user_id)
VALUES ('Aluguel', 1800.00, '2025-01-10', 'EXPENSE', 1);

INSERT INTO transactions (description, amount, date, type, user_id)
VALUES ('Supermercado', 400.00, '2025-01-12', 'EXPENSE', 1);

-- FEBRUARY - DATA FILTER TEST
INSERT INTO transactions (description, amount, date, type, user_id)
VALUES ('Conta de Luz', 150.00, '2025-02-10', 'EXPENSE', 1);

-- USER 2 TRANSACTIONS - To test data isolation
INSERT INTO transactions (description, amount, date, type, user_id)
VALUES ('Salário do Outro Cara', 10000.00, '2025-01-05', 'INCOME', 2);