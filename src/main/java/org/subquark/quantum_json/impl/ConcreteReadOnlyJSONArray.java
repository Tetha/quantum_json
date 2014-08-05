package org.subquark.quantum_json.impl;

import org.subquark.quantum_json.ReadOnlyJSONElement;
import org.subquark.quantum_json.ReadOnlyJSONArray;


import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

import java.util.function.IntFunction;

import java.util.stream.Stream;


public final class ConcreteReadOnlyJSONArray implements ReadOnlyJSONArray {
    private final List<ReadOnlyJSONElement> elements;

    public ConcreteReadOnlyJSONArray( List<ReadOnlyJSONElement> elements ) {
        List<ReadOnlyJSONElement> defensiveCopy = new ArrayList<>( elements );
        this.elements = Collections.unmodifiableList( defensiveCopy );
    }

    public ConcreteReadOnlyJSONArray( int size, IntFunction<ReadOnlyJSONElement> elementGen ) {
        List<ReadOnlyJSONElement> generatedList = new ArrayList<>( size );
        for ( int ii = 0; ii < size; ii++ ) {
            generatedList.add( elementGen.apply( ii ) );
        }
        this.elements = Collections.unmodifiableList( generatedList );
    }
    
    @Override
    public List<ReadOnlyJSONElement> asReadOnlyJavaList() {
        return elements; // all constructors make sure to create unmodifiable lists
    }
}
