# GraphQL

This is a very simple introduction to [GraphQL](https://graphql.org/learn/).

To run the application, use the `GraphQlApp` class. When running, the GraphQL
servlet listens on [http://localhost:8080/graphql](http://localhost:8080/graphql)
and the GraphiQL console is available on
[http://localhost:8080/graphiql](http://localhost:8080/graphiql).

The resources descriptor is in the `src/main/resources/graphql/query.graphqls`
file.

Try the application, explore the query possibilities (using the GraphiQL), try the
`helloWorld` query:

```
query	{
  helloWorld
}
```

Try expanding the types, you can create your own class or use anything existing
in the JRE, then query your newly added type.

