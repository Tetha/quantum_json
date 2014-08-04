package org.subquark.quantum_json;

public interface JSONBoolean extends ReadOnlyJSONElement {
    default JSONBoolean asJSONBoolean() {
        return this;
    }
}
