package org.subquark.quantum_json;

import java.util.List;
import java.util.Map;

public interface ReadOnlyJSONElement {
    // Implementation Note: 
    // All these default methods are overridden in exactly
    // one class which doesn't throw, but rather just
    // returns "this". This introduces a cyclic dependency
    // and spreads the casting responsibility across multiple
    // files, but it avoids ugly casts. I think it's worth it.
    //

    /* ------------------------------------------------------------------ */

    default ReadOnlyJSONObject asReadOnlyJSONObject() {
        throw new UnsupportedOperationException( "Cannot convert " + getClass() + " to read-only JSONObject" );
    }

    default ReadOnlyJSONArray asReadOnlyJSONArray() {
        throw new UnsupportedOperationException( "Cannot convert " + getClass() + " to read-only JSONArray" );
    }

    /* ------------------------------------------------------------------ */

    default List<ReadOnlyJSONElement> asReadOnlyJavaList() {
        throw new UnsupportedOperationException( "Cannot convert " + getClass() + " to read-only java list" );
    }


    default Map<String, ReadOnlyJSONElement> asReadOnlyJavaMap() {
        throw new UnsupportedOperationException( "Cannot convert " + getClass() + " to read-only java map" );
    }

    /* ------------------------------------------------------------------ */

    /*

    default JSONBoolean asJSONBoolean() {
        throw new UnsupportedOperationException( "Cannot convert " + getClass() + " to JSONBoolean" );
    }

    */
    default boolean asJavaBoolean() {
        throw new UnsupportedOperationException( "Cannot convert " + getClass() + " to java boolean" );
    }

    /* ------------------------------------------------------------------ */

    default JSONString asJSONString() {
        throw new UnsupportedOperationException( "Cannot convert " + getClass() + " to JSONString" );
    }

    default String asJavaString() {
        throw new UnsupportedOperationException( "Cannot convert " + getClass() + " to java string" );
    }

    /* ------------------------------------------------------------------ */

    /*
    default JSONNumber asJSONNumber() {
        throw new UnsupportedOperationException( "Cannot convert " + getClass() + " to JSONNumber" );
    }
    */
    default long asJavaLong() {
        throw new UnsupportedOperationException( "Cannot convert " + getClass() + " to java long" );
    }

    default int asJavaInt() {
        throw new UnsupportedOperationException( "Cannot convert " + getClass() + " to java int" );
    }

    default float asJavaFloat() {
        throw new UnsupportedOperationException( "Cannot convert " + getClass() + " to java float" );
    }
    
    default double asJavaDouble() {
        throw new UnsupportedOperationException( "Cannot convert " + getClass() + " to java double" );
    }
}
