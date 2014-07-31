package org.subquark.quantum_json;

import java.util.List;
import java.util.Map;

public interface JSONElement extends ReadOnlyJSONElement {
    default JSONObject asJSONObject() {
        throw new UnsupportedOperationException( "Cannot convert " + getClass() + " to JSONObject" );
    }

    default Map<String, JSONElement> asJavaMap() {
        throw new UnsupportedOperationException( "Cannot convert " + getClass() + " to java map" );
    }
    
    default JSONArray asJSONArray() {
        throw new UnsupportedOperationException( "Cannot convert " + getClass() + " to JSONObject" );
    }

    default List<JSONElement> asJavaList() {
        throw new UnsupportedOperationException( "Cannot convert " + getClass() + " to java list" );
    }
}
