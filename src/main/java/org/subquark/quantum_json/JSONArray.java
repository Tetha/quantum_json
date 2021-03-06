package org.subquark.quantum_json;

import java.util.List;

public interface JSONArray extends ReadOnlyJSONArray, JSONElement {
    @Override
    default JSONArray asJSONArray() {
        return this;
    }

    @Override
    default ReadOnlyJSONArray asReadOnlyJSONArray() {
        return this;
    }
    
}
