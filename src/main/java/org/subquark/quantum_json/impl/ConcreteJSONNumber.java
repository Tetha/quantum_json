package org.subquark.quantum_json.impl;

import org.subquark.quantum_json.JSONNumber;

import java.util.Objects;

public final class ConcreteJSONNumber implements JSONNumber {
    private final String unparsedNumber;

    public ConcreteJSONNumber( String unparsedNumber ) {
        this.unparsedNumber = unparsedNumber;
    }

    @Override
    public long asJavaLong() {
        return Long.valueOf( unparsedNumber );
    }

    @Override
    public int asJavaInt() {
        return Integer.valueOf( unparsedNumber );
    }

    @Override
    public float asJavaFloat() {
        return Float.valueOf( unparsedNumber );
    }

    @Override
    public double asJavaDouble() {
        return Double.valueOf( unparsedNumber );
    }

    @Override
    public boolean equals( Object o ) {
        if ( o == this ) return true;
        if ( o == null ) return false;

        if ( !( o instanceof ConcreteJSONNumber ) ) {
            return false;
        }

        ConcreteJSONNumber co = (ConcreteJSONNumber) o;

        return Objects.equals( this.unparsedNumber, co.unparsedNumber );
    }

    @Override
    public int hashCode() {
        return unparsedNumber.hashCode();
    }

    @Override
    public String toString() {
        return "ConcreteJSONNumber( unparsedNumber=" + unparsedNumber + ")";
    }
}
