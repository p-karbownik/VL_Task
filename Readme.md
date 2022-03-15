#VL

A simple backend app that was created using Java and SpringBoot.
Docker is required to run the application.
####Building docker image

```
docker build -t container_name .
```

####Running docker image

```
docker run -p host_port:8080 -t container_name
```

Replace the character string "host_port" to publish the port.