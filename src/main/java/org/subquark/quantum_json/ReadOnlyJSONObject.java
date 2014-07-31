package org.subquark.quantum_json;

import java.util.Map;

public interface ReadOnlyJSONObject extends ReadOnlyJSONElement {
    @Override
    default ReadOnlyJSONObject asReadOnlyJSONObject() {
        return this;
    }
}
