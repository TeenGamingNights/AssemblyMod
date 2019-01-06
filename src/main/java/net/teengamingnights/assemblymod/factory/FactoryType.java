package net.teengamingnights.assemblymod.factory;

public enum FactoryType {
    PLACEHOLDER_FACTORY("Placeholder");

    private final String typeName;

    FactoryType(String name) {

        this.typeName = name;

    }

    public String getName() {

        return typeName;

    }
}
