# Reward calculator

This is reward calculator application which created monthly rewards for all transactions

# REST APIs

### 1. All rewards

#### End point - `/rewards`

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

### 2. Reward by start and end date

#### End Point - `/rewards`

        Method -   GET
        This API returns the customer reward based on provided start and end date

### Sample request - `/rewards?startDate=2025-02-15&endDate=2025-09-15`

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
    "transactionDate": "2025-07-15",
    "customer": {
      "customerId": 1000,
      "customerName": "Hritik"
    }
  },
  {
    "transactionId": 2,
    "transactionAmount": 240.0,
    "transactionDate": "2025-07-15",
    "customer": {
      "customerId": 1000,
      "customerName": "Hritik"
    }
  },
  {
    "transactionId": 3,
    "transactionAmount": 45.0,
    "transactionDate": "2025-07-15",
    "customer": {
      "customerId": 1000,
      "customerName": "Hritik"
    }
  },
  {
    "transactionId": 4,
    "transactionAmount": 40.0,
    "transactionDate": "2025-07-15",
    "customer": {
      "customerId": 1000,
      "customerName": "Hritik"
    }
  },
  {
    "transactionId": 5,
    "transactionAmount": 450.0,
    "transactionDate": "2025-07-15",
    "customer": {
      "customerId": 1001,
      "customerName": "Manoj"
    }
  },
  {
    "transactionId": 6,
    "transactionAmount": 120.0,
    "transactionDate": "2025-07-15",
    "customer": {
      "customerId": 1001,
      "customerName": "Manoj"
    }
  },
  {
    "transactionId": 7,
    "transactionAmount": 200.0,
    "transactionDate": "2025-07-15",
    "customer": {
      "customerId": 1001,
      "customerName": "Manoj"
    }
  },
  {
    "transactionId": 8,
    "transactionAmount": 250.0,
    "transactionDate": "2025-07-15",
    "customer": {
      "customerId": 1002,
      "customerName": "Priyanka"
    }
  },
  {
    "transactionId": 9,
    "transactionAmount": 90.0,
    "transactionDate": "2025-07-15",
    "customer": {
      "customerId": 1002,
      "customerName": "Priyanka"
    }
  },
  {
    "transactionId": 10,
    "transactionAmount": 240.0,
    "transactionDate": "2025-07-15",
    "customer": {
      "customerId": 1002,
      "customerName": "Priyanka"
    }
  },
  {
    "transactionId": 11,
    "transactionAmount": 180.0,
    "transactionDate": "2025-07-15",
    "customer": {
      "customerId": 1002,
      "customerName": "Priyanka"
    }
  },
  {
    "transactionId": 12,
    "transactionAmount": 120.0,
    "transactionDate": "2025-01-15",
    "customer": {
      "customerId": 1002,
      "customerName": "Priyanka"
    }
  },
  {
    "transactionId": 13,
    "transactionAmount": 100.0,
    "transactionDate": "2025-12-15",
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
  "customer": {
    "customerId": 1002,
    "customerName": "Priyanka"
  }
}
```

### 5.Get rewards by customerId,startDate,endDate

### method-Get

### endPoint- `/rewards/customer?customerName=Manoj`

### returns rewards

###              

### Sample Response

```json
[
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

# Test status

```cmd

D:\Work\JavaProjects\github\rewardcalculator>mvn test
[INFO] Scanning for projects...
[INFO]
[INFO] ---------------------< com.infy:rewardcalculator >----------------------
[INFO] Building rewardcalculator 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ rewardcalculator ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.11.0:compile (default-compile) @ rewardcalculator ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ rewardcalculator ---
[INFO] skip non existing resourceDirectory D:\Work\JavaProjects\github\rewardcalculator\src\test\resources
[INFO]
[INFO] --- compiler:3.11.0:testCompile (default-testCompile) @ rewardcalculator ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- surefire:3.1.2:test (default-test) @ rewardcalculator ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.infy.rewardcalculator.controller.RewardControllerTest
16:19:25.599 [main] INFO org.springframework.test.context.support.AnnotationConfigContextLoaderUtils -- Could not detect default configuration classes for test class [com.infy.rewardcalculator.controller.RewardControllerTest]: RewardControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
16:19:25.766 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper -- Found @SpringBootConfiguration com.infy.rewardcalculator.RewardcalculatorApplication for test class com.infy.rewardcalculator.controller.RewardControllerTest

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.4)

2025-10-28T16:19:26.223+05:30  INFO 5924 --- [rewardcalculator] [           main] c.i.r.controller.RewardControllerTest    : Starting RewardControllerTest using Java 21.0.8 with PID 5924 (started by Hritik in D:\Work\JavaProjects\github\rewardcalculator)
2025-10-28T16:19:26.228+05:30  INFO 5924 --- [rewardcalculator] [           main] c.i.r.controller.RewardControllerTest    : No active profile set, falling back to 1 default profile: "default"
WARNING: A Java agent has been loaded dynamically (C:\Users\Hritik\.m2\repository\net\bytebuddy\byte-buddy-agent\1.14.12\byte-buddy-agent-1.14.12.jar)
WARNING: If a serviceability tool is in use, please run with -XX:+EnableDynamicAgentLoading to hide this warning
WARNING: If a serviceability tool is not in use, please run with -Djdk.instrument.traceUsage for more information
WARNING: Dynamic loading of agents will be disallowed by default in a future release
Java HotSpot(TM) 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
2025-10-28T16:19:28.976+05:30  INFO 5924 --- [rewardcalculator] [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2025-10-28T16:19:28.976+05:30  INFO 5924 --- [rewardcalculator] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2025-10-28T16:19:28.981+05:30  INFO 5924 --- [rewardcalculator] [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 5 ms
2025-10-28T16:19:29.025+05:30  INFO 5924 --- [rewardcalculator] [           main] c.i.r.controller.RewardControllerTest    : Started RewardControllerTest in 3.144 seconds (process running for 4.431)
[INFO] Tests run: 12, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 4.208 s -- in com.infy.rewardcalculator.controller.RewardControllerTest
[INFO] Running com.infy.rewardcalculator.RewardcalculatorApplicationTests
2025-10-28T16:19:29.604+05:30  INFO 5924 --- [rewardcalculator] [           main] t.c.s.AnnotationConfigContextLoaderUtils : Could not detect default configuration classes for test class [com.infy.rewardcalculator.RewardcalculatorApplicationTests]: RewardcalculatorApplicationTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration.
2025-10-28T16:19:29.616+05:30  INFO 5924 --- [rewardcalculator] [           main] .b.t.c.SpringBootTestContextBootstrapper : Found @SpringBootConfiguration com.infy.rewardcalculator.RewardcalculatorApplication for test class com.infy.rewardcalculator.RewardcalculatorApplicationTests

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.4)

2025-10-28T16:19:29.684+05:30  INFO 5924 --- [rewardcalculator] [           main] c.i.r.RewardcalculatorApplicationTests   : Starting RewardcalculatorApplicationTests using Java 21.0.8 with PID 5924 (started by Hritik in D:\Work\JavaProjects\github\rewardcalculator)
2025-10-28T16:19:29.684+05:30  INFO 5924 --- [rewardcalculator] [           main] c.i.r.RewardcalculatorApplicationTests   : No active profile set, falling back to 1 default profile: "default"
2025-10-28T16:19:30.102+05:30  INFO 5924 --- [rewardcalculator] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2025-10-28T16:19:30.172+05:30  INFO 5924 --- [rewardcalculator] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 55 ms. Found 2 JPA repository interfaces.
2025-10-28T16:19:30.521+05:30  INFO 5924 --- [rewardcalculator] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2025-10-28T16:19:31.151+05:30  INFO 5924 --- [rewardcalculator] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@5acd7d1c
2025-10-28T16:19:31.154+05:30  INFO 5924 --- [rewardcalculator] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2025-10-28T16:19:31.218+05:30  INFO 5924 --- [rewardcalculator] [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2025-10-28T16:19:31.329+05:30  INFO 5924 --- [rewardcalculator] [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.4.4.Final
2025-10-28T16:19:31.399+05:30  INFO 5924 --- [rewardcalculator] [           main] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
2025-10-28T16:19:31.570+05:30  INFO 5924 --- [rewardcalculator] [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2025-10-28T16:19:32.664+05:30  INFO 5924 --- [rewardcalculator] [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2025-10-28T16:19:32.667+05:30  INFO 5924 --- [rewardcalculator] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2025-10-28T16:19:33.187+05:30  WARN 5924 --- [rewardcalculator] [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2025-10-28T16:19:33.356+05:30  INFO 5924 --- [rewardcalculator] [           main] c.i.r.RewardcalculatorApplicationTests   : Started RewardcalculatorApplicationTests in 3.728 seconds (process running for 8.76)
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 3.760 s -- in com.infy.rewardcalculator.RewardcalculatorApplicationTests
[INFO] Running com.infy.rewardcalculator.service.TransactionServiceImplTest
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.099 s -- in com.infy.rewardcalculator.service.TransactionServiceImplTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 15, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  11.353 s
[INFO] Finished at: 2025-10-28T16:19:33+05:30
[INFO] ------------------------------------------------------------------------

D:\Work\JavaProjects\github\rewardcalculator>
```

# Application run status

```cmd
D:\Work\JavaProjects\github\rewardcalculator>mvn spring-boot:run
[INFO] Scanning for projects...
[INFO]
[INFO] ---------------------< com.infy:rewardcalculator >----------------------
[INFO] Building rewardcalculator 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] >>> spring-boot:3.2.4:run (default-cli) > test-compile @ rewardcalculator >>>
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ rewardcalculator ---
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.11.0:compile (default-compile) @ rewardcalculator ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ rewardcalculator ---
[INFO] skip non existing resourceDirectory D:\Work\JavaProjects\github\rewardcalculator\src\test\resources
[INFO]
[INFO] --- compiler:3.11.0:testCompile (default-testCompile) @ rewardcalculator ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] <<< spring-boot:3.2.4:run (default-cli) < test-compile @ rewardcalculator <<<
[INFO]
[INFO]
[INFO] --- spring-boot:3.2.4:run (default-cli) @ rewardcalculator ---
[INFO] Attaching agents: []

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.4)

2025-10-28T16:07:55.696+05:30  INFO 19564 --- [rewardcalculator] [           main] c.i.r.RewardcalculatorApplication        : Starting RewardcalculatorApplication using Java 21.0.8 with PID 19564 (D:\Work\JavaProjects\github\rewardcalculator\target\classes started by Hritik in D:\Work\JavaProjects\github\rewardcalculator)
2025-10-28T16:07:55.696+05:30  INFO 19564 --- [rewardcalculator] [           main] c.i.r.RewardcalculatorApplication        : No active profile set, falling back to 1 default profile: "default"
2025-10-28T16:07:56.308+05:30  INFO 19564 --- [rewardcalculator] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2025-10-28T16:07:56.368+05:30  INFO 19564 --- [rewardcalculator] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 55 ms. Found 2 JPA repository interfaces.
2025-10-28T16:07:56.831+05:30  INFO 19564 --- [rewardcalculator] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2025-10-28T16:07:56.850+05:30  INFO 19564 --- [rewardcalculator] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2025-10-28T16:07:56.858+05:30  INFO 19564 --- [rewardcalculator] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.19]
2025-10-28T16:07:56.930+05:30  INFO 19564 --- [rewardcalculator] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2025-10-28T16:07:56.930+05:30  INFO 19564 --- [rewardcalculator] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1173 ms
2025-10-28T16:07:57.055+05:30  INFO 19564 --- [rewardcalculator] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2025-10-28T16:07:57.403+05:30  INFO 19564 --- [rewardcalculator] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@25b38203
2025-10-28T16:07:57.408+05:30  INFO 19564 --- [rewardcalculator] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2025-10-28T16:07:57.450+05:30  INFO 19564 --- [rewardcalculator] [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2025-10-28T16:07:57.498+05:30  INFO 19564 --- [rewardcalculator] [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.4.4.Final
2025-10-28T16:07:57.543+05:30  INFO 19564 --- [rewardcalculator] [           main] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
2025-10-28T16:07:57.790+05:30  INFO 19564 --- [rewardcalculator] [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2025-10-28T16:07:58.736+05:30  INFO 19564 --- [rewardcalculator] [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2025-10-28T16:07:58.741+05:30  INFO 19564 --- [rewardcalculator] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2025-10-28T16:07:59.058+05:30  WARN 19564 --- [rewardcalculator] [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2025-10-28T16:07:59.368+05:30  INFO 19564 --- [rewardcalculator] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2025-10-28T16:07:59.378+05:30  INFO 19564 --- [rewardcalculator] [           main] c.i.r.RewardcalculatorApplication        : Started RewardcalculatorApplication in 4.081 seconds (process running for 4.431)

```


