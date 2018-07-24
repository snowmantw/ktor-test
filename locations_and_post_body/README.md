# Test for

[Locations feature}(https://ktor.io/features/locations.html) with POST request body

# Reason

It is unclear in Locations feature document about how to handle POST request body,
since the data class of its examples is majorly for GET request.

# Run it

1. `cd ./locations_and_post_body/ && gradle build && gradle fatjar && java -jar ./build/libs/ktor-test-all-1.0-SNAPSHOT.jar`
2. `curl -H "Content-Type: application/json" -X POST -d '{"say":"hello","times":3}' http://127.0.0.1:8080/test/Foo`

It should output:

```
Foo says:
hello
hello
hello
```
# Memo

The routing rules can follow the nice data class oriented format, but body still need to use the `call.receive<BodyClass>()`.

The `call.receive<T>` is in `io.ktor.request.*`. Without this, for some cases it just reports error, or find wrong
implementation of `call` without the `receive`. 
