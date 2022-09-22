# jms-test

Simple war to send and receive a JMS message. A INFO message is printed on each received message.

## Send message

```bash
curl -X POST http://localhost:19080/jms-test/message
```

Send 10 messages

```bash
curl -X POST http://localhost:19080/jms-test/message?number=10
```
