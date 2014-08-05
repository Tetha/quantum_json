package org.subquark.quantum_json.impl;

import org.subquark.quantum_json.ReadOnlyJSONElement;
import org.subquark.quantum_json.ReadOnlyJSONObject;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import java.util.function.Function;

import java.util.stream.Stream;


public final class ConcreteReadOnlyJSONObject implements ReadOnlyJSONObject {
    private final Map<String, ReadOnlyJSONElement> elements;

    public ConcreteReadOnlyJSONObject( Map<String, ReadOnlyJSONElement> elements ) {
        Map<String, ReadOnlyJSONElement> defensiveCopy = new HashMap<>( elements );
        this.elements = Collections.unmodifiableMap( defensiveCopy );
    }

    public ConcreteReadOnlyJSONObject( Stream<String> keyStream,
                                       Function<String, ReadOnlyJSONElement> valueGen ) {
        Map<String, ReadOnlyJSONElement> generatedMap = new HashMap<>();
        keyStream.forEach( k -> generatedMap.put( k, valueGen.apply( k ) ) );
        this.elements = Collections.unmodifiableMap( generatedMap );
    }

    @Override
    public Map<String, ReadOnlyJSONElement> asReadOnlyJavaMap() {
        return elements; // all constructors make sure to make elements unmodifiable
    }
}

