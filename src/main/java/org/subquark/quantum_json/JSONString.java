package org.subquark.quantum_json;

public interface JSONString extends ReadOnlyJSONElement {
    default JSONString asJSONString() {
        return this;
    }
}
