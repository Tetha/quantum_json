package org.subquark.quantum_json;

import java.util.Map;

public interface JSONObject extends JSONElement, ReadOnlyJSONObject {
    @Override
    default JSONObject asJSONObject() {
        return this;
    }

    @Override
    default ReadOnlyJSONObject asReadOnlyJSONObject() {
        return this;
    }
}
