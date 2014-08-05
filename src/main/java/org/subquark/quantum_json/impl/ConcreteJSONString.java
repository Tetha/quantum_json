package org.subquark.quantum_json.impl;

import org.subquark.quantum_json.JSONString;

import java.util.Objects;

public final class ConcreteJSONString implements JSONString {
    private final String contents;

    public ConcreteJSONString( String contents ) {
        this.contents = contents;
    }

    @Override
    public String asJavaString() {
        return contents;
    }

    @Override
    public boolean equals( Object o ) {
        if ( o == this ) return true;
        if ( o == null ) return false;

        if ( !( o instanceof ConcreteJSONString ) ) {
            return false;
        }

        ConcreteJSONString co = (ConcreteJSONString) o;

        return Objects.equals( this.contents, co.contents );
    }

    @Override
    public int hashCode() {
        return contents.hashCode();
    }

    @Override
    public String toString() {
        return "ConcreteJSONString( contents=" + contents + ")";
    }
}
