
ReadOnlyJSONElements
####################

This library supports fully mutable json elements, and
readonly json elements. 

Let me explain this distinction in my own world. On this
level of abstraction, an object have 2 important
characteristics:

 - Does it offer *a mutation interface*? A mutation interface
   contains methods explicitly designed to change the state
   of an object. For example. java.util.Map offers methods 
   such as V put( K key, V value ). If this method was
   supposed to maintain the state of the object it was 
   called on, it would have to return a new map. Since it
   doesn't, this method is clearly designed to be part
   of a mutation interface.

 - Does this interface have *immutability guarantees*?
   An immutability guarantee exists in truly functional
   langauges like haskell - if you have a value v, no 
   function call on this value v changes the value v.
   This is different in java, because even if v is a
   Collections.unmodifiableList, the contents of 
   v might change, because someone change the decorated
   list.

QuantumJSONs Readonly elements offer *no mutation interface*,
but they *offer no immutability guarantees*.

To be more precise, we have to talk about individual classes.
At first, you have the json values, JSONString, JSONNumber,
JSONBoolean and JSONNull. These are fully immutability 
guarantee and offer no mutation interface, because they
are boring wrappers around java primitives, enums or
java strings.

After this, we have 2 pairs of classes, ReadOnlyJSONObjects
or ReadOnlyJSONArrays and JSONObject and JSONArrays. The
readonly elements are trying very, very hard to offer no
mutation interface. However, the ReadOnlyJSONElements
cannot offer a strong immutability guarantee, because
mutable json elements offer a read-only interface. However,
a read only view of a mutable json element can change
at any time.

On the other hand, mutable json elements try very, very
hard to be mutable. For example, mutable json arrays
intentionally don't defensively copy a java.util.List 
they are created from so you can keep changing this
list and it will change the json element. Or if you
convert a mutable JSONArray to a java.util.List,
modifying this list will modify the JSONArray. This
is implemented this way in order to simplify leveraging
powerful libraries working on java.util.List or
java.util.Map. 
