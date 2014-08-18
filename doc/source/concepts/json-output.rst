
JSON Output
###########

If you are used to simple json handling with concrete
json objects, the json output subsystem is probably 
a very weird construct. Why would you jump through
these extra hoops just to make a json object and print
it's string representation? Why use a builder, with an
interface, and a whole ton of unintuitive things instead
of simply constructing json objects?

The core answer is efficiency, and easily switching to
a less powerful, but faster way to output json. At work,
we had a recurring class of issues: You want to output
some json, potentially on a socket. So, you go ahead
and use the simple, naive way: You construct json objects
in memory, call ``toString()`` on them with some pretty 
printing and output the result to a socket output stream.

Then, the first thing goes wrong: The string builder
in a JSONObjects toString throws an OutOfMemoryError,
Requested Array Size exceeds VM limit. Whoops. I mean 
it might be kinda weird to output around 1 - 2 Gigabyes 
of JSON, but an application gotta do what an application
gotta do. So, you switch from ``object.toString()`` to
``object.write( Writer )``. That prevents the OOM Exception,
but after some time you might notice some effects:

 - You traverse your data twice. Once to construct the
   json, and once to serialize the json into a string
   or a byte sequence on a writer. This is less efficient
   than possible.
 - You spend a ridiculous time in quoting json strings.
   The org.json library easily spends 10% - 15% of it's
   time just quoting strings. 

These two issues combined easily end up being up 
to 50% - 60% CPU overhead just to output some json. That's 
the point where in the default org.json library, 
things become painful, because you have to modify all your
code to manually writing json to a writer on the fly. In
the process, you lose all guarantees of a nice, safe 
library that your created json is parseable by another
json library.

This nasty conclusion gave rise to the JSONOutput interface.
The JSONOutput offers you a way to say "I want to output
json here. I don't care about the details, I want to
print JSON here." This way, your actually working code
can remain unchanged, while higher level code can choose
how the JSON is printed.

As a whole, quantum json offers three ways to output
json:

 - The NaiveJSONWriter.

   The naive json writer builds json objects internally.
   This isn't very effective, but if you want to output
   the internal state of the writer for debugging 
   purposes, you can get a nice, formatted json object.

   Also, the NaiveJSONWriter offers the highest level 
   of error checking, because the NaiveJSONWriter
   has a full json object, and it can check if you are
   constructing well formed JSON. As such, if you are
   writing tests, you should use a NaiveJSONWriter,
   since it can validate, complain and output nice 
   error messages.

 - The JSONStreamer

   The JSONStreamer is the first iteration of efficient
   JSON output. The JSONStreamer has an OutputStream,
   and directly writes valid JSON to the OutputStream.

   The JSONStreamer does not offer full formatting of
   the JSON written to the stream, because the JSONStreamer
   doesn't keep full state of the output JSON. It
   offers some basic validation, and it will try to
   quote strings you write. 

   Thus, the JSONStreamer is the interface to use if
   you have huge JSON Objects, but you'd still like
   some validation while you are writing JSON Objects.

 - The UnsafeJSONStreamer

   The UnsafeJSONStreamer drops all sanity checks
   and potentially outputs correct json. The
   UnsafeJSONStreamer doesn't guarantee anything here.
   If your code works with the other streamers, and
   doesn't receive errors from the other streamers, and
   you are correctly quoting everything you tell the
   UnsafeJSONStreamer to write, there's a good chance
   that other JSON libraries are able to parse the
   output of this monster.

   The advantage of this is raw, pure performance.
   This monster doesn't waste a single cycle on error
   checking or being smart, it just translates
   calls into adding characters to an output stream.

The preferred way to operate with these streamers
is to use the NaiveJSONWriter in tests. This gives
you a strongly, carefully validating writer, which
produces nice, readable error messages for your
asserts, produces loud and annoying errors for your
usual errors, and generally ends up being very simple.

In a low-pressure production system, the NaiveJSONWriter
remains competetive, because it allows you to fully
log application-application interactions in a readable
way in a trace log and all is nice. You might want to
switch to the JSONStreamer in order to save some 
memory if you're confident in your code, but usually,
that's not necessary.

The fun starts if you have a ton of pressure on your
application, with huge amounts of requests hammering 
the poor application. That's a good situation to switch
to the JSONStreamer, and if you can measure the json
quoting being a problem, switch to the UnsafeJSONStreamer.

However, in order to switch to the UnsafeJSONStreamer,
you **must** check that you quote everything you
hand to the UnsafeJSONStreamer correctly. In many cases,
this requires no code change, though. For example, 
in one of our cases, our strings are guaranteed to 
consist of dot-separated alphanumeric strings. I don't 
need to quote that, so I don't need to do anything for the
UnsafeJSONStreamer. I can just put the UnsafeJSONStreamer
into the well-tested code requiring a JSONOutput and
suddenly I save a lot of CPU.
