package main

import (
    "fmt"
    "net/http"
)

func headers(w http.ResponseWriter, req *http.Request) {
    for name, headers := range req.Header {
        for _, v := range headers {
            fmt.Fprintf(w, "%v: %v\n", name, v)
        }
    }
}

func main() {
    http.HandleFunc("/", headers)

	fmt.Println("start")
    http.ListenAndServe(":8080", nil)
}