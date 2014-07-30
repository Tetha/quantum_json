package org.subquark.quantum_json;

/**
 * This is the super interface for all JSON Objects.
 *
 * This class exists in order to have a way to say
 * "Here be some json-shaped dragons". The object
 * offers methods to cast it to the various more
 * specific JSON Parts, such as a JSONObject or 
 * a JSONNumber. These methods can throw runtime
 * exceptions if they are called on a JSON Element
 * not supporting a cast to this type.
 *
 * In the standard json library, this interface is 
 * missing and as such,it is necessary to use Object
 * whenever you want an arbitrary JSON Element to
 * be passed around. I feel like that is a bad
 * architectural decision, because straight up
 * "Object" is a lot more than just "Some JSON".
 *
 * @author Harald Kraemer &lt;tetha42@gmail.com&gt;
 */
public interface JSONElement {
    // Implementation Note: 
    // All these default methods are overridden in exactly
    // one class which doesn't throw, but rather just
    // returns "this". This introduces a cyclic dependency
    // and spreads the casting responsibility across multiple
    // files, but it avoids ugly casts. I think it's worth it.

    default JSONObject asJSONObject() {
        throw new UnsupportedOperationException( "Cannot convert " + getClass() + " to JSONObject" );
    }

    default JSONArray asJSONArray() {
        throw new UnsupportedOperationException( "Cannot convert " + getClass() + " to JSONObject" );
    }

    default JSONNumber asJSONNumber() {
        throw new UnsupportedOperationException( "Cannot convert " + getClass() + " to JSONNumber" );
    }

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
