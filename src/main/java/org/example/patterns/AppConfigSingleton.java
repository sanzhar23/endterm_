package org.example.patterns;

public final class AppConfigSingleton {

    private static final AppConfigSingleton INSTANCE = new AppConfigSingleton();

    private AppConfigSingleton() {}

    public static AppConfigSingleton getInstance() {
        return INSTANCE;
    }

    public String defaultLevel() {
        return "EASY";
    }
}
