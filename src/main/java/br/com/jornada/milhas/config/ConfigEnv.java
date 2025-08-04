package br.com.jornada.milhas.config;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigEnv {

    public ConfigEnv() {
        Dotenv dotenv = Dotenv.configure().load();
        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue())
        );
    }
}
