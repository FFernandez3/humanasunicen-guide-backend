package org.humanas.guia;

import jakarta.annotation.PostConstruct;
import org.humanas.guia.utils.DataLoaderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.text.ParseException;

@SpringBootApplication
@EnableJpaRepositories("org.humanas.guia.repositories")
public class GuiaApplication {
    @Autowired
    private DataLoaderHelper dataLoaderHelper;
    public static void main(String[] args) {
        SpringApplication.run(GuiaApplication.class, args);
    }

    @PostConstruct
    public void init() throws ParseException {
        dataLoaderHelper.loadCarreras();
        dataLoaderHelper.loadMaterias();
        dataLoaderHelper.loadArchivos();
    }
}
