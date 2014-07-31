package org.subquark.quantum_json;

import java.util.List;

public interface ReadOnlyJSONArray extends ReadOnlyJSONElement {
    @Override
    default ReadOnlyJSONArray asReadOnlyJSONArray() {
        return this;
    }
}
