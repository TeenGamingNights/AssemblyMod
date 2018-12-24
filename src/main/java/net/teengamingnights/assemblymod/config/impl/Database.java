package net.teengamingnights.assemblymod.config.impl;

public class Database {
    private String host;
    private int port;
    private String username;
    private String password;
    private String database;
    private DatabasePool pool;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabase() {
        return database;
    }

    public DatabasePool getPool() {
        return pool;
    }
}
