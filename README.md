# SqsListenerExample (Spring Boot)
## Used 
```bash
LocalStack:AWS
```
## Run

```bash 
docker-compose -f docker-compose.yml up -d --build
```

## Test

```bash 
curl -L -X POST 'http://localhost:8080/sendSqsMessage' -H 'Content-Type: application/text' -H 'Authorization: I918PIviyaGMdZG0E2qy404F' --data-raw 'Hadi bakalim2'
```
