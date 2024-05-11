# Maju Mundur Marketplace

## Overview

Maju Mundur is clothing market place to connect multiple merchant and customer. To be able maintan retention customer, Maju Mundur preparing reward point to customer which succeed transaction. Reward point can be trade with reward which already prepared by Maju Mundur. There is two reward, Reward A worth 20 point and Reward B worth 40 point.



## API Endpoint

### Authentication

#### Register Customer --- `[POST] /api/auth/register`

- No Authorization needed

- Request Body

  ```json
  {
      "identityNumber" : "1234123412341234",
      "name" : "Zaldi Irvana",
      "username" : "zaldi",
      "password" : "irvana",
      "phone" : "088123123123",
      "email" : "zaldi.irvana@majumundur.id",
      "address" : "Jl. Salak No. 27, Kabupaten Malang"
  }
  ```

- Response

  ```json
  {
      "statusCode": 200,
      "message": "Register customer success",
      "data": {
          "username": "zaldi",
          "role": "ROLE_CUSTOMER"
      }
  }
  ```

  

#### Register Merchant --- `[POST] /api/auth/register/merchant`

- No Authorization needed

- Request Body

  ```json
  {
      "name" : "Toko Bagus Jaya",
      "username" : "bagus",
      "password" : "jaya",
      "phone" : "088123321222",
      "email" : "bagus.jaya@majumundur.id",
      "address" : "Jl. Salak No. 28, Kabupaten Malang"
  }
  ```

- Response

  ```json
  {
      "statusCode": 200,
      "message": "Register merchant success",
      "data": {
          "username": "bagus",
          "role": "ROLE_MERCHANT"
      }
  }
  ```



#### Login --- `[POST] /api/auth/login`

- No Authorization needed

- Request Body

  ```json
  {
      "username" : "bagus",
      "password" : "jaya"
  }
  ```

- Response

  ```json
  {
      "statusCode": 401,
      "message": "Login Failed",
      "timestamp": "2024-05-11T22:01:17.360786100"
  }
  ```





### Merchant

#### Get All Merchant --- `[GET] /api/merchant`

- Auth needed

- Response

  ```json
  {
      "statusCode": 200,
      "message": "Get all merchants success",
      "data": [
          {
              "id": 1,
              "name": "Toko SUmber Rejeki",
              "phone": "088123321111",
              "email": "sumber.rejeki@majumundur.id",
              "address": "Jl. Salak No. 28, Kabupaten Malang"
          },
          {
              "id": 2,
              "name": "Toko Bagus Jaya",
              "phone": "088123321222",
              "email": "bagus.jaya@majumundur.id",
              "address": "Jl. Salak No. 28, Kabupaten Malang"
          }
      ]
  }
  ```

  

#### Get Merchant By Id --- `[GET] /api/merchant/{id}`

- Auth needed

- Response

  ```json
  {
      "statusCode": 200,
      "message": "Get merchant success",
      "data": {
          "id": 1,
          "name": "Toko SUmber Rejeki",
          "phone": "088123321111",
          "email": "sumber.rejeki@majumundur.id",
          "address": "Jl. Salak No. 28, Kabupaten Malang"
      }
  }
  ```





### Customer

#### Get All Customer --- `[GET] /api/customer`

- Auth needed

- Response

  ```json
  {
      "statusCode": 200,
      "message": "Get all customers success",
      "data": [
          {
              "id": 1,
              "name": "Zaldi Irvana"
          },
          {
              "id": 2,
              "name": "Jane Doe"
          },
          {
              "id": 3,
              "name": "John Doe"
          }
      ]
  }
  ```



#### Get Customer By Id --- `[GET] /api/customer/{id}`

- Auth needed

- Response

  ```json
  {
      "statusCode": 200,
      "message": "Get customer success",
      "data": {
          "id": 3,
          "identityNumber": "12341234123413333",
          "name": "Zaldi Irvana",
          "pointsObtained": 0,
          "phone": "0881231231123231",
          "email": "zaldi.irvana@majumundur.id",
          "address": "Jl. Salak No. 27, Kabupaten Malang"
      }
  }
  ```



#### Get All Customer Ordered --- `[GET] /api/customer/order/my-merchant`

- Get all customer ordered in one merchant

- Merchant ID stored from authentication token

- Auth needed (`ROLE_MERCHANT` only)

- Response

  ```json
  {
      "statusCode": 200,
      "message": "Get customer success",
      "data": {
          "id": 3,
          "identityNumber": "12341234123413333",
          "name": "Zaldi Irvana",
          "pointsObtained": 0,
          "phone": "0881231231123231",
          "email": "zaldi.irvana@majumundur.id",
          "address": "Jl. Salak No. 27, Kabupaten Malang"
      }
  }
  ```





### Product

#### Create Product --- `[POST] /api/product`

- Auth needed (`ROLE_MERCHANT` only)

- Merchant ID stored from authentication token

- Request Body

  ```json
  {
      "name" : "Songkok Durian",
      "description" : "songkok kualitas nasional",
      "price" : 90000,
      "stock" : 12
  }
  ```

- Response

  ```json
  {
      "statusCode": 201,
      "message": "Create product success",
      "data": {
          "id": 5,
          "name": "Songkok Durian",
          "description": "songkok kualitas nasional",
          "price": 90000,
          "stock": 12,
          "merchant": {
              "id": 1,
              "name": "Toko SUmber Rejeki"
          }
      }
  }
  ```

  

#### Get All Product --- `[GET] /api/product`

- Auth needed
- Get all product from every merchant

- Response

  ```json
  {
      "statusCode": 200,
      "message": "Get all product success",
      "data": [
          {
              "id": 1,
              "name": "Sarung BHS Classic",
              "description": "sarung kualitas nasional",
              "price": 300000,
              "stock": 12,
              "merchant": {
                  "id": 2,
                  "name": "Toko Bagus Jaya"
              }
          },
          {
              "id": 5,
              "name": "Songkok Durian",
              "description": "songkok kualitas nasional",
              "price": 90000,
              "stock": 12,
              "merchant": {
                  "id": 1,
                  "name": "Toko SUmber Rejeki"
              }
          }
      ]
  }
  ```

  

#### Get Product By Id --- `[GET] /api/product/{id}`

- Auth needed

- Response

  ```json
  {
      "statusCode": 200,
      "message": "Get product success",
      "data": {
          "id": 2,
          "name": "holahup",
          "description": "lingkaran setan",
          "price": 12000,
          "merchant": {
              "id": 3,
              "name": "Toko Jaya Bagus"
          }
      }
  }
  ```

  

#### Update Product --- `[PUT] /api/product`

- Auth needed (`ROLE_MERCHANT` only)

- Merchant ID stored from authentication token

- Only permitted to authenticated merchant

- Request Body

  ```json
  {
      "id" : 1,
      "name" : "Sarung BHS Premium",
      "description" : "Sarung tenun sutra kualitas Premium",
      "price" : 500000,
      "stock" : 7
  }
  ```

- Response

  ```json
  {
      "statusCode": 200,
      "message": "Update product success",
      "data": {
          "id": 1,
          "name": "Sarung BHS Premium",
          "description": "Sarung tenun sutra kualitas Premium",
          "price": 500000,
          "stock": 7,
          "merchant": {
              "id": 1,
              "name": "Toko Sumber Rejeki"
          }
      }
  }
  ```

  

#### Delete Product --- `[DELETE] /api/product/{id}`

- Auth needed (`ROLE_MERCHANT` only)
- Merchant ID stored from authentication token
- Only permitted to authenticated merchant

- Response

  ```json
  {
      "statusCode": 200,
      "message": "Delete product success",
      "data": null
  }
  ```





### Order

#### Create Order --- `[POST] /api/order`

- Auth needed (`ROLE_CUSTOMER` only)

- Customer ID stored from authentication token

- If order succeed, customer will obtain 5 point/product (ex: on request below, customer will obtain 10 points)

- Request Body

  ```json
  {
      "orders" : [
          {
              "productId" : 4,
              "qty" : 1
          },
          {
              "productId" : 5,
              "qty" : 1
          }
      ]
  }
  ```

- Response

  ```json
  {
      "statusCode": 201,
      "message": "Create order success",
      "data": {
          "id": 9,
          "customer": {
              "id": 3,
              "name": "Zaldi Irvana"
          },
          "orders": [
              {
                  "id": 23,
                  "qty": 1,
                  "product": {
                      "id": 4,
                      "name": "Songkok Mangga",
                      "description": "songkok kualitas nasional",
                      "price": 30000,
                      "stock": 4,
                      "merchant": {
                          "id": 1,
                          "name": "Toko SUmber Rejeki"
                      }
                  }
              },
              {
                  "id": 24,
                  "qty": 1,
                  "product": {
                      "id": 5,
                      "name": "Songkok Durian",
                      "description": "songkok kualitas nasional",
                      "price": 90000,
                      "stock": 7,
                      "merchant": {
                          "id": 1,
                          "name": "Toko SUmber Rejeki"
                      }
                  }
              }
          ]
      }
  }
  ```



#### Get All Order --- `[GET] /api/order`

- Auth needed 

- Response

  ```json
  {
      "statusCode": 200,
      "message": "Get all orders success",
      "data": [
          {
              "id": 8,
              "customer": {
                  "id": 3,
                  "name": "Zaldi Irvana"
              },
              "orders": [
                  {
                      "id": 21,
                      "qty": 1,
                      "product": {
                          "id": 4,
                          "name": "Songkok Mangga",
                          "description": "songkok kualitas nasional",
                          "price": 30000,
                          "stock": 3,
                          "merchant": {
                              "id": 1,
                              "name": "Toko SUmber Rejeki"
                          }
                      }
                  },
                  {
                      "id": 22,
                      "qty": 1,
                      "product": {
                          "id": 5,
                          "name": "Songkok Durian",
                          "description": "songkok kualitas nasional",
                          "price": 90000,
                          "stock": 6,
                          "merchant": {
                              "id": 1,
                              "name": "Toko SUmber Rejeki"
                          }
                      }
                  }
              ]
          }
      ]
  }
  ```



#### Get Order By Id --- `[GET] /api/order/{id}`

- Auth needed

- Response

  ```json
  {
      "statusCode": 200,
      "message": "Get order success",
      "data": {
          "id": 4,
          "customer": {
              "id": 3,
              "name": "Zaldi Irvana"
          },
          "orders": [
              {
                  "id": 13,
                  "qty": 1,
                  "product": {
                      "id": 4,
                      "name": "Songkok Mangga",
                      "description": "songkok kualitas nasional",
                      "price": 30000,
                      "stock": 3,
                      "merchant": {
                          "id": 1,
                          "name": "Toko SUmber Rejeki"
                      }
                  }
              },
              {
                  "id": 14,
                  "qty": 1,
                  "product": {
                      "id": 5,
                      "name": "Songkok Durian",
                      "description": "songkok kualitas nasional",
                      "price": 90000,
                      "stock": 6,
                      "merchant": {
                          "id": 1,
                          "name": "Toko SUmber Rejeki"
                      }
                  }
              }
          ]
      }
  }
  ```





### Reward

#### Claim Reward --- `[POST] /api/reward`

- Auth needed (`ROLE_CUSTOMER` only)

- Claim reward using customer's points obtained from order succeed

- Customer ID stored from authentication token

- Request Body

  ```json
  {
      "reward" : "reward_a"
  }
  ```

- Response

  ```json
  {
      "statusCode": 201,
      "message": "Claim reward success",
      "data": {
          "id": 6,
          "reward": "REWARD_A",
          "redeemDate": "2024-05-11T18:15:54.9032567",
          "customer": {
              "id": 3,
              "name": "Zaldi Irvana"
          }
      }
  }
  ```

  

#### Get All Reward Transaction --- `[GET] /api/reward`

- Auth needed (`ROLE_CUSTOMER` only)

- Get all reward transaction from one customer only

- Customer ID stored from authentication token

- Response

  ```json
  {
      "statusCode": 200,
      "message": "Get all reward transaction success",
      "data": [
          {
              "id": 5,
              "reward": "REWARD_B",
              "redeemDate": "2024-05-12T00:49:35.3544255",
              "customer": {
                  "id": 3,
                  "name": "Zaldi Irvana"
              }
          },
          {
              "id": 6,
              "reward": "REWARD_A",
              "redeemDate": "2024-05-12T00:49:35.3544255",
              "customer": {
                  "id": 3,
                  "name": "Zaldi Irvana"
              }
          }
      ]
  }
  ```

  

#### Get Reward Transaction By Id --- `[GET] /api/reward/{id}`

- Auth needed (`ROLE_CUSTOMER` only)

- Only permitted to rewarded customer

- Customer ID stored from authentication token

- Response

  ```json
  {
      "statusCode": 200,
      "message": "Get reward transaction success",
      "data": {
          "id": 5,
          "reward": "REWARD_B",
          "redeemDate": "2024-05-12T00:51:56.0393222",
          "customer": {
              "id": 3,
              "name": "Zaldi Irvana"
          }
      }
  }
  ```









### Database Schema

![ERD](C:\Users\Lenovo\Documents\placement-test\BPD Jogja\ERD.png)









### Tech

- Java 17
- Spring Boot Framework
- PostgreSQL
