package org.subquark.quantum_json;

public interface JSONString extends ReadOnlyJSONElement {
    @Override
    default JSONString asJSONString() {
        return this;
    }
}
