-- USERS
INSERT INTO users (name, email, password, created_at) VALUES ('admin', 'admn@dev.com', '$2a$10$W7YMyzA/rkHo61lr6u0r8eal8QEsGdm5HC/9jNoOD3qi/20pEjItS', CURRENT_TIMESTAMP);
INSERT INTO users (name, email, password, created_at) VALUES ('Test user', 'test@email.com', '$2a$10$W7YMyzA/rkHo61lr6u0r8eal8QEsGdm5HC/9jNoOD3qi/20pEjItS', CURRENT_TIMESTAMP);

-- INCOME
INSERT INTO transactions (description, amount, date, type, user_id) VALUES ('Monthly salary', 5000.00, '2025-01-05', 'INCOME', 1);
INSERT INTO transactions (description, amount, date, type, user_id) VALUES ('Freelance Java Project', 1500.00, '2025-01-15', 'INCOME', 1);

-- EXPENSE
INSERT INTO transactions (description, amount, date, type, user_id) VALUES ('Rent', 1800.00, '2025-01-10', 'EXPENSE', 1);
INSERT INTO transactions (description, amount, date, type, user_id) VALUES ('Supermarket', 400.00, '2025-01-12', 'EXPENSE', 1);

-- FEBRUARY - DATA FILTER TEST
INSERT INTO transactions (description, amount, date, type, user_id) VALUES ('Electricity Bill', 150.00, '2025-02-10', 'EXPENSE', 1);

-- USER 2 TRANSACTIONS - To test data isolation
INSERT INTO transactions (description, amount, date, type, user_id) VALUES ('Other guy salary', 10000.00, '2025-01-05', 'INCOME', 2);