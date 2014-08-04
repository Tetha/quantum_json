package org.subquark.quantum_json;

public interface JSONString extends JSONElement {
    default JSONString asJSONString() {
        return this;
    }
}
