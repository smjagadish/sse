# Spring Boot SSE Demo

This is a Spring Boot application that demonstrates Server-Sent Events (SSE). It has a single endpoint (`/sse`) that clients can connect to. Once connected, the server sends a "first data" message. Then, every second, a scheduled task sends a message containing user data to all connected clients. The `sse.html` file provides a simple web page that connects to the SSE endpoint and displays the received events.
