# syntax=docker/dockerfile:1

FROM golang:1.18-alpine AS builder

WORKDIR /app

COPY go.mod ./
RUN go mod download

COPY *.go ./

RUN go build -o echo-http-headers

FROM alpine:latest

COPY --from=builder /app/echo-http-headers /echo-http-headers

EXPOSE 8080

CMD ["/echo-http-headers"]
