package org.subquark.quantum_json.impl;

import org.subquark.quantum_json.ReadOnlyJSONElement;
import org.subquark.quantum_json.ReadOnlyJSONObject;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import java.util.function.Function;

import java.util.stream.Stream;


public final class ConcreteReadOnlyJSONObject implements ReadOnlyJSONObject {
    private final Map<String, ReadOnlyJSONElement> elements;

    public ConcreteReadOnlyJSONObject( Map<String, ReadOnlyJSONElement> elements ) {
        Map<String, ReadOnlyJSONElement> defensiveCopy = new HashMap<>( Objects.requireNonNull( elements  ));
        this.elements = Collections.unmodifiableMap( defensiveCopy );
    }

    public ConcreteReadOnlyJSONObject( Stream<String> keyStream,
                                       Function<String, ReadOnlyJSONElement> valueGen ) {
        Map<String, ReadOnlyJSONElement> generatedMap = new HashMap<>();
        Stream<String> nullSafeKeyStream = Objects.requireNonNull( keyStream );
        Function<String, ReadOnlyJSONElement> nullSafeValueGen = Objects.requireNonNull( valueGen );
        nullSafeKeyStream.forEach( k -> generatedMap.put( k, nullSafeValueGen.apply( k ) ) );
        this.elements = Collections.unmodifiableMap( generatedMap );
    }

    @Override
    public Map<String, ReadOnlyJSONElement> asReadOnlyJavaMap() {
        return elements; // all constructors make sure to make elements unmodifiable
    }

    @Override
    public boolean equals( Object o ) {
        if ( o == this ) return true;
        if ( o == null ) return false;

        if ( !( o instanceof ConcreteReadOnlyJSONObject ) ) return false;

        ConcreteReadOnlyJSONObject co = (ConcreteReadOnlyJSONObject) o;

        if ( co.elements.size() != elements.size() ) return false;

        for ( Map.Entry<String, ReadOnlyJSONElement> e : elements.entrySet() ){
            String potentiallyCommonKey = e.getKey();
            if ( !co.elements.containsKey( potentiallyCommonKey ) ) return false;

            ReadOnlyJSONElement thisValue = e.getValue();
            ReadOnlyJSONElement coValue = co.elements.get( potentiallyCommonKey );

            if ( !thisValue.equals( coValue ) ) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int arbitraryPrime = 31;
        int result = 1;
        for ( Map.Entry<String, ReadOnlyJSONElement> e : elements.entrySet() ) {
            // mostly by intuition and java best practices.. 
            // If you have good reasons, feel free to 
            // tweak it.
            // if you tweaked this with good reasons,
            // please document your reasoning here.
            result = result * 31 + e.getKey().hashCode() + e.getValue().hashCode();
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("ConcreteReadOnlyJSONObject(");
        result.append("elements=[");

        elements.forEach( (k, v) -> result.append( k.toString() )
                                          .append( " -> ")
                                          .append( v.toString() ) 
                        );

        result.append("]");
        result.append(")");
        return result.toString();
    }
}

