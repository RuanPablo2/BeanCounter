package com.RuanPablo2.BeanCounter.jobs;

import com.RuanPablo2.BeanCounter.config.DemoSeeder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DemoResetScheduler {

    private final JdbcTemplate jdbcTemplate;
    private final DemoSeeder demoSeeder;

    public DemoResetScheduler(JdbcTemplate jdbcTemplate, DemoSeeder demoSeeder) {
        this.jdbcTemplate = jdbcTemplate;
        this.demoSeeder = demoSeeder;
    }

    @Scheduled(cron = "0 0 */12 * * *")
    @Transactional
    public void resetDatabase() {
        System.out.println("🧹 [DemoResetScheduler] Iniciando limpeza do banco de dados...");

        jdbcTemplate.execute("TRUNCATE TABLE transactions, users RESTART IDENTITY CASCADE");

        System.out.println("✨ [DemoResetScheduler] Banco limpo! Recriando os dados da vitrine...");
        demoSeeder.seed();
    }
}