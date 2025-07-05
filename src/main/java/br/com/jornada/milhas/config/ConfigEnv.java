package br.com.jornada.milhas.config;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;

import java.util.Set;

public class ConfigEnv {

    public ConfigEnv() {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("DB_USER", dotenv.get("DB_USER"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        System.setProperty("DB_NAME_BANCO_DADOS", dotenv.get("DB_NAME_BANCO_DADOS"));
        System.setProperty("DB_HOST", dotenv.get("DB_HOST"));
    }
}
