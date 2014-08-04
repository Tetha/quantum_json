package org.subquark.quantum_json;

public interface JSONNumber extends ReadOnlyJSONElement {
    @Override
    default JSONNumber asJSONNumber() {
        return this;
    }
}
