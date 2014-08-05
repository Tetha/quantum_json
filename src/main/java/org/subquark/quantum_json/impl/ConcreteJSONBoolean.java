package org.subquark.quantum_json;

public enum ConcreteJSONBoolean implements JSONBoolean {
    TRUE(true), FALSE(false);

    private final boolean value;

    private ConcreteJSONBoolean( boolean value ) {
        this.value = value;
    }

    @Override
    public boolean asJavaBoolean() {
        return this.value;
    }

    @Override
    public String toString() {
        return "ConcreteJSONBoolean( value=" + value + ")";
    }
}
