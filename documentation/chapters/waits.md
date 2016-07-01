[Home](../README.md)

# The Wait Utility Class
The `Wait` utility class contains all kinds of methods which allow you / your tests to wait for certain conditions.
There are two general types of wait operations available:

**Wait a given amount of time:**

- `Wait.exactly(5, TimeUnit.SECONDS)`
- `Wait.exactly(100, TimeUnit.MILLISECONDS)`

**Wait until a specific condition is met:**

- `Wait.until(textField).has(text("foo"))`
- `Wait.withTimeoutOf(1, TimeUnit.SECOND).until(() -> true)`

# Linked Documentation

- [Conditions](conditions.md)
