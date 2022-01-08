# wallet-service

## Why
We need to develop a microservice that receive payments via kafka messages.
It has to save these payments in a database, validate to a third party service and log any error.

## How
* Run class PaymentApplication

This starts the application and consume any message received via kafka (localhost:29092)

It persists the data in a postgresql database (jdbc:postgresql://localhost:5432/payments)

Validate payments to third party service (http://localhost:9000/payment)

All the errors are logged to other service (http://localhost:9000/log)

## Where
* PaymentApplicationIntegrationTest: try a complete process. Because of time, it is pending to emulate kafka messages. Need all systems up
* AccountEntityManagerTest and PaymentEntityManagerTest: just try the needed operations, not all the crud operations
* KafkaConsumerTest: check how messages are being received and common exceptions
* ProcessPaymentOfflineServiceTest and ProcessPaymentOnlineServiceTest: main business test

## What
This is a basic structure, where each package has its own logic. We work with segregate interfaces. There are packages action where we define implementations

* consumer: receive kafka messages and convert to incoming java objects
* incoming: this layer process all incoming messages, allows to this project receive messages from any source (kafka, queue, rest, ...)
* domain: main pojos in this service
* exception: all defined errors and its handler. Moreover, it sends errors to log service
* business: the main logic of the service
* repository: logic to persist information. In this case, in a database. There is entity objects to separate from domain objects.
* provider: external service or logic that is needed in this project

## Who
* **Alvaro Longueira** - [alvarolongueira](https://github.com/alvarolongueira)


