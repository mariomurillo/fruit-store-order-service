# fruit-store-order-service

This project was generate with Spring Boot, Java 11 and Gradle.

## Run project

For run the project, in the root folder you can run the next command:

`./gradlew bootRun`

## Run test

For run the project test, in the root  folder you can run the next command:

`./gradlew test`

## Send Request

When the project is runing you can send a post in the next endpoint:

`http://localhost:8080/order`

And send a request similar to this:

```
{
    "items": [
        {
            "type": "APPLE",
            "quantity": 1 
        },
        {
            "type": "ORANGE",
            "quantity": 1
        }
    ]
}
```

With the previous request you will get the next response:

```
{
    "items": [
        {
            "type": "APPLE",
            "quantity": 1,
            "price": 60.0
        },
        {
            "type": "ORANGE",
            "quantity": 1,
            "price": 25.0
        }
    ],
    "total": 85.0
}
```
