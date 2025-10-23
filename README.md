# rewardcalculator
This is reward calculator application which created monthly rewards for all transactions

# REST APIs

### 1.  All rewards
####    End point - `/rewards`
    Method - GET 
    This APIs returns all the rewards

### Sample response  
  ```json
[
  {
    "customerName": "Hritik",
    "monthlyRewards": [
      {
        "month": "JULY",
        "rewardAmount": 580
      }
    ]
  },
  {
    "customerName": "Priyanka",
    "monthlyRewards": [
      {
        "month": "JANUARY",
        "rewardAmount": 90
      },
      {
        "month": "DECEMBER",
        "rewardAmount": 50
      },
      {
        "month": "JULY",
        "rewardAmount": 930
      }
    ]
  },
  {
    "customerName": "Manoj",
    "monthlyRewards": [
      {
        "month": "JULY",
        "rewardAmount": 1090
      }
    ]
  }
]
```

### 2.  Reward by start and end date

####    End Point - `/rewards/{startDate}/{endDate}`
        Method -   GET
        This API returns the customer reward based on provided start and end date

### Sample request - `/rewards/1739577600000/1757894400000`
### Sample response for start and end date(15 Feb 2025 to 15 Sept 2025) 
```json
[
    {
        "customerName": "Hritik",
        "monthlyRewards": [
            {
                "month": "JULY",
                "rewardAmount": 580
            }
        ]
    },
    {
        "customerName": "Priyanka",
        "monthlyRewards": [
            {
                "month": "JULY",
                "rewardAmount": 930
            }
        ]
    },
    {
        "customerName": "Manoj",
        "monthlyRewards": [
            {
                "month": "JULY",
                "rewardAmount": 1090
            }
        ]
    }
]
```

### 3. GET all transactions

### Method - GET
### EndPoint - `/transactions`
### Sample Response
```json
[
    {
        "transactionId": 1,
        "transactionAmount": 200.0,
        "transactionDate": 1752537600000,
        "customer": {
            "customerId": 1000,
            "customerName": "Hritik"
        }
    },
    {
        "transactionId": 2,
        "transactionAmount": 240.0,
        "transactionDate": 1752537600000,
        "customer": {
            "customerId": 1000,
            "customerName": "Hritik"
        }
    },
    {
        "transactionId": 3,
        "transactionAmount": 45.0,
        "transactionDate": 1752537600000,
        "customer": {
            "customerId": 1000,
            "customerName": "Hritik"
        }
    },
    {
        "transactionId": 4,
        "transactionAmount": 40.0,
        "transactionDate": 1752537600000,
        "customer": {
            "customerId": 1000,
            "customerName": "Hritik"
        }
    },
    {
        "transactionId": 5,
        "transactionAmount": 450.0,
        "transactionDate": 1752537600000,
        "customer": {
            "customerId": 1001,
            "customerName": "Manoj"
        }
    },
    {
        "transactionId": 6,
        "transactionAmount": 120.0,
        "transactionDate": 1752537600000,
        "customer": {
            "customerId": 1001,
            "customerName": "Manoj"
        }
    },
    {
        "transactionId": 7,
        "transactionAmount": 200.0,
        "transactionDate": 1752537600000,
        "customer": {
            "customerId": 1001,
            "customerName": "Manoj"
        }
    },
    {
        "transactionId": 8,
        "transactionAmount": 250.0,
        "transactionDate": 1752537600000,
        "customer": {
            "customerId": 1002,
            "customerName": "Priyanka"
        }
    },
    {
        "transactionId": 9,
        "transactionAmount": 90.0,
        "transactionDate": 1752537600000,
        "customer": {
            "customerId": 1002,
            "customerName": "Priyanka"
        }
    },
    {
        "transactionId": 10,
        "transactionAmount": 240.0,
        "transactionDate": 1752537600000,
        "customer": {
            "customerId": 1002,
            "customerName": "Priyanka"
        }
    },
    {
        "transactionId": 11,
        "transactionAmount": 180.0,
        "transactionDate": 1752537600000,
        "customer": {
            "customerId": 1002,
            "customerName": "Priyanka"
        }
    },
    {
        "transactionId": 12,
        "transactionAmount": 120.0,
        "transactionDate": 1736899200000,
        "customer": {
            "customerId": 1002,
            "customerName": "Priyanka"
        }
    },
    {
        "transactionId": 13,
        "transactionAmount": 100.0,
        "transactionDate": 1765756800000,
        "customer": {
            "customerId": 1002,
            "customerName": "Priyanka"
        }
    }
]
```

### 4. Save single transactions

### Method - POST
### EndPoint - `/transactions`
### returns String
### Sample Request
```json
{
"transactionAmount": 100,
  "transactionDate": 1765756800000,
  "customer": {
  "customerId": 1002,
          "customerName": "Priyanka"
        }
}
```
### Sample Response - Transaction saved successfully.

