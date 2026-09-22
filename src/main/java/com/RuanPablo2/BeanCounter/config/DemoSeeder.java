package com.RuanPablo2.BeanCounter.config;

import com.RuanPablo2.BeanCounter.entity.Transaction;
import com.RuanPablo2.BeanCounter.entity.User;
import com.RuanPablo2.BeanCounter.entity.enums.TransactionType;
import com.RuanPablo2.BeanCounter.repository.TransactionRepository;
import com.RuanPablo2.BeanCounter.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class DemoSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final PasswordEncoder passwordEncoder;

    public DemoSeeder(UserRepository userRepository, TransactionRepository transactionRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        seed();
    }

    @Transactional
    public void seed() {
        Optional<User> existingDemoUser = userRepository.findByEmail("demo@beancounter.com");
        if (existingDemoUser.isPresent()) {
            return;
        }

        User demoUser = new User();
        demoUser.setName("Perfil teste");
        demoUser.setEmail("demo@beancounter.com");
        demoUser.setPassword(passwordEncoder.encode("test@demo123"));
        userRepository.save(demoUser);

        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();

        List<Transaction> demoTransactions = List.of(
                createTransaction(demoUser, "Salário Mensal - Desenvolvedor", 4800.00, LocalDate.of(year, month, 5), TransactionType.INCOME, "Salário"),
                createTransaction(demoUser, "Freelance - Landing Page Angular", 1250.00, LocalDate.of(year, month, 12), TransactionType.INCOME, "Freelance"),
                createTransaction(demoUser, "Aluguel do Apartamento", 1800.00, LocalDate.of(year, month, 6), TransactionType.EXPENSE, "Moradia"),
                createTransaction(demoUser, "Conta de Luz", 148.63, LocalDate.of(year, month, 1), TransactionType.EXPENSE, "Contas Básicas"),
                createTransaction(demoUser, "Compras do Mês - Carrefour", 654.30, LocalDate.of(year, month, 11), TransactionType.EXPENSE, "Alimentação"),
                createTransaction(demoUser, "Internet", 125.99, LocalDate.of(year, month, 8), TransactionType.EXPENSE, "Internet"),
                createTransaction(demoUser, "Dividendos fundos imobiliários", 800.00, LocalDate.of(year, month, 1), TransactionType.INCOME, "Investimentos")
        );

        transactionRepository.saveAll(demoTransactions);
        System.out.println("✅ [DemoSeeder] Dados de demonstração injetados com sucesso (Total: 7 transações)!");
    }

    private Transaction createTransaction(User user, String description, double amount, LocalDate date, TransactionType type, String category) {
        Transaction t = new Transaction();
        t.setUser(user);
        t.setDescription(description);
        t.setAmount(BigDecimal.valueOf(amount));
        t.setDate(date);
        t.setType(type);
        t.setCategory(category);
        return t;
    }
}