# Spring Boot Book Seller

### Endpoints

#### Sign-Up

```
POST /api/authentication/sign-up HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{
"name": "user",
"username": "user",
"password": "user"
}
```

#### Sign-In
```
POST /api/authentication/sign-in HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{
    "username": "user",
    "password": "user"
}
```

#### Make-Admin
```
PUT /api/internal/make-admin/admin HTTP/1.1
Host: localhost:8080
Authorization: Bearer InternalApiKey1234!
```

### Save Book
```
POST /api/book HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjIiLCJyb2xlcyI6IlJPTEVfQURNSU4iLCJ1c2VySWQiOjYsImV4cCI6MTY5MzAwMTg5NH0.-o2WxJOC7UelQssb-zE66ZK8003qt7_AyYTIj1GXARuuPP4tLVnfyBOhtw1kE0pIERfBgfVLJUIOWR9B2K-cEw
Content-Length: 121

{
    "title": "Test 2",
    "price": "20",
    "description": "Description test 2",
    "author": "Author test 2"
}
```

### Delete Book
```
DELETE /api/book/2 HTTP/1.1
Host: localhost:8080
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjIiLCJyb2xlcyI6IlJPTEVfQURNSU4iLCJ1c2VySWQiOjYsImV4cCI6MTY5MzAwMTg5NH0.-o2WxJOC7UelQssb-zE66ZK8003qt7_AyYTIj1GXARuuPP4tLVnfyBOhtw1kE0pIERfBgfVLJUIOWR9B2K-cEw
```

### Get All Books
```
GET /api/book HTTP/1.1
Host: localhost:8080
```

### Save Purchase
```
POST /api/purchase-history HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMiIsInJvbGVzIjoiUk9MRV9VU0VSIiwidXNlcklkIjo1LCJleHAiOjE2OTMwMDIxMzZ9.5rQpzI34m-nVSSI4dF5TBSW46DBrbMbNGXk_gpyyVZ26YeayylGy2F5Oj2z-YtY5j4jCpgIZwl_OMzIFffgKPQ
Content-Length: 57

{
    "userId": 5,
    "bookId": 1,
    "price": 20
}
```

### Get User Purchases
```
GET /api/purchase-history HTTP/1.1
Host: localhost:8080
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMiIsInJvbGVzIjoiUk9MRV9VU0VSIiwidXNlcklkIjo1LCJleHAiOjE2OTMwMDIxMzZ9.5rQpzI34m-nVSSI4dF5TBSW46DBrbMbNGXk_gpyyVZ26YeayylGy2F5Oj2z-YtY5j4jCpgIZwl_OMzIFffgKPQ
```