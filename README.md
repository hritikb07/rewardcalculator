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
    "monthlyRewardDtos": [
      {
        "month": "JULY",
        "rewardAmount": 580
      }
    ]
  },
  {
    "customerName": "Priyanka",
    "monthlyRewardDtos": [
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
    "monthlyRewardDtos": [
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

### Sample response for start and end date(15 Feb 2025 to 15 Sept 2025) 
```json
[
    {
        "customerName": "Hritik",
        "monthlyRewardDtos": [
            {
                "month": "JULY",
                "rewardAmount": 580
            }
        ]
    },
    {
        "customerName": "Priyanka",
        "monthlyRewardDtos": [
            {
                "month": "JULY",
                "rewardAmount": 930
            }
        ]
    },
    {
        "customerName": "Manoj",
        "monthlyRewardDtos": [
            {
                "month": "JULY",
                "rewardAmount": 1090
            }
        ]
    }
]
```