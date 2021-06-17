package com.budger.configurations;

import com.budger.data.entities.Category;
import com.budger.data.entities.Role;
import com.budger.repositories.CategoryRepository;
import com.budger.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ApplicationConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository, CategoryRepository categoryRepository) {
        return args -> {
            Role userRole = new Role("ROLE_USER");
            Role adminRole = new Role("ROLE_ADMIN");

            roleRepository.saveAll(List.of(adminRole, userRole));

            Category category1 = new Category("Еда");
            Category category2 = new Category("Развлечения");
            Category category3 = new Category("Подписки");
            Category category4 = new Category("Транспорт");
            Category category5 = new Category("Напитки");
            Category category6 = new Category("Покупки");
            Category category7 = new Category("Для дома");
            Category category8 = new Category("Прочее");
            Category category9 = new Category("Доход");

            categoryRepository.saveAll(List.of(
                    category1,
                    category2,
                    category3,
                    category4,
                    category5,
                    category6,
                    category7,
                    category8,
                    category9
            ));
        };
    }
}
