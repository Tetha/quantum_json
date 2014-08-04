package org.subquark.quantum_json;

public interface JSONBoolean extends ReadOnlyJSONElement {
    @Override
    default JSONBoolean asJSONBoolean() {
        return this;
    }
}
