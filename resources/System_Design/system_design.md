# System Design

System design is the process of planning a software system before coding it. It covers architecture, requirements, components, data flow, storage, APIs, scaling, reliability, and trade-offs.

Use this file as a compact interview revision notebook: first clarify what you are building, then estimate scale, choose components, explain trade-offs, and defend failure handling.

## 1. Core Goals

| Goal | Meaning | Interview Point |
|---|---|---|
| **Scalability** | Handles more users, traffic, or data. | Scale vertically first for simplicity, horizontally when one machine is not enough. |
| **Reliability** | Works correctly even when parts fail. | Use retries, replication, backups, failover, and graceful degradation. |
| **Availability** | Stays reachable for users. | Measured with uptime targets like 99.9% or 99.99%. |
| **Performance** | Responds quickly under expected load. | Improve latency, throughput, caching, and database access patterns. |
| **Maintainability** | Easy to understand, debug, and extend. | Keep modules clear, interfaces stable, and ownership simple. |
| **Fault Tolerance** | Continues operating despite failures. | Avoid single points of failure. |

## 2. HLD vs LLD

| Type | Meaning | Focus | Examples |
|---|---|---|---|
| **HLD - High-Level Design** | Bird's-eye view of the system. | Components and communication. | Load balancer, API server, DB, cache, queue. |
| **LLD - Low-Level Design** | Detailed internal design of components. | Classes, APIs, schemas, methods. | Class diagrams, DB tables, API contracts. |

## 3. Engineering Design Principles

These principles help you justify clean, maintainable designs in HLD, LLD, and coding interviews.

### SOLID Principles

| Principle | Full Form | Meaning | Interview Example |
|---|---|---|---|
| **S** | Single Responsibility Principle | A class/module should have one clear reason to change. | Keep payment logic separate from notification logic. |
| **O** | Open/Closed Principle | Code should be open for extension but closed for modification. | Add a new payment method without changing existing payment classes. |
| **L** | Liskov Substitution Principle | Child classes should be replaceable wherever parent classes are expected. | `Square` should not break behavior expected from `Rectangle`. |
| **I** | Interface Segregation Principle | Prefer small focused interfaces over one large interface. | `Printer`, `Scanner`, `Fax` interfaces instead of one huge `Machine` interface. |
| **D** | Dependency Inversion Principle | High-level modules should depend on abstractions, not concrete classes. | Service depends on `PaymentGateway` interface, not directly on Stripe/Razorpay class. |

### Other Important Design Principles

| Principle | Meaning | Why It Matters |
|---|---|---|
| **KISS** | Keep It Simple, Stupid. | Simple designs are easier to build, debug, scale, and explain. |
| **DRY** | Don't Repeat Yourself. | Avoid duplicate logic; one change should not require updates in many places. |
| **YAGNI** | You Aren't Gonna Need It. | Do not build features/components before there is a real requirement. |
| **Separation of Concerns** | Split system into clear responsibilities. | UI, business logic, database, and external integrations stay independent. |
| **Loose Coupling** | Components should depend on each other as little as possible. | Easier to change one part without breaking others. |
| **High Cohesion** | Related logic should stay together. | Makes modules easier to understand and maintain. |
| **Composition over Inheritance** | Prefer combining small objects over deep inheritance trees. | More flexible and avoids fragile parent-child hierarchies. |
| **Fail Fast** | Detect invalid states/errors early. | Prevents hidden bugs from spreading through the system. |
| **Defensive Design** | Assume dependencies can fail or send bad input. | Use validation, timeouts, retries, fallbacks, and clear errors. |
| **Principle of Least Astonishment** | System behavior should match user/developer expectations. | APIs and UI should behave predictably. |
| **Least Privilege** | Give only the minimum permissions required. | Reduces security risk if a token/user/service is compromised. |

Good interview line: "I would keep the first version simple using KISS and YAGNI, separate responsibilities clearly, keep services loosely coupled, and apply SOLID principles mainly where the code is expected to grow."

## 4. Interview Round Playbook

System design interviews test decision-making, not one perfect diagram. Think out loud and justify every component.

| Step | What To Do | Output |
|---|---|---|
| 1 | Clarify scope. | "Are we designing full Netflix or only video playback?" |
| 2 | List functional requirements. | User-facing features and core workflows. |
| 3 | List non-functional requirements. | Scale, latency, availability, consistency, security. |
| 4 | Estimate scale. | DAU, QPS, read/write ratio, storage, bandwidth. |
| 5 | Define APIs. | Key endpoints, request/response shape, idempotency. |
| 6 | Design data model. | Entities, tables/documents, indexes, partition keys. |
| 7 | Draw HLD. | Client, gateway, services, cache, DB, queues, workers. |
| 8 | Deep dive bottlenecks. | Hot paths, failure points, scaling strategy. |
| 9 | Discuss trade-offs. | Simplicity vs scale, latency vs consistency, cost vs reliability. |
| 10 | Add operations. | Monitoring, alerts, logs, retries, rate limits, failover. |

### What Interviewers Like

- Start with requirements before drawing boxes.
- Separate must-have features from nice-to-have features.
- Use numbers, even rough assumptions, to guide design choices.
- Mention alternatives and explain why you are choosing one.
- Add components only when they solve a stated problem.
- Call out failure cases and how the system recovers.

### What To Avoid

- Do not add every component you know.
- Do not jump directly to microservices, sharding, Kafka, or Kubernetes without scale pressure.
- Do not ignore non-functional requirements.
- Do not optimize CPU/GPU when the real bottleneck is database, network, cache, or service calls.
- Do not leave the design without observability.

## 5. Functional vs Non-Functional Requirements

Functional requirements define **what the system does**. Non-functional requirements define **how well the system performs**.

| Type | Meaning | E-Commerce Example |
|---|---|---|
| **Functional Requirement** | Features and user actions. | Register, login, search products, filter, add to cart, apply coupon, pay, track order. |
| **Non-Functional Requirement** | Quality targets and constraints. | 1M DAU, p95 latency under 200 ms, 99.9% availability, encrypted data, 10x sale-day traffic. |

### Functional Requirement Checklist

- Who are the users?
- What are the core actions?
- What read flows are needed?
- What write flows are needed?
- What should happen after success/failure?
- What is explicitly out of scope?

### Non-Functional Requirement Checklist

| Area | Ask This | Example Target |
|---|---|---|
| **Scale** | How many users, requests, reads/writes, regions? | 1M DAU, 10K QPS peak. |
| **Latency** | How fast should responses be? | p95 search under 200 ms. |
| **Availability** | How much downtime is acceptable? | 99.9% monthly uptime. |
| **Reliability** | Can data be lost or duplicated? | No payment duplication, no order loss. |
| **Consistency** | Should reads show latest data immediately? | Strong for payments, eventual for feeds. |
| **Security** | What needs auth, authorization, encryption? | TLS, password hashing, RBAC. |
| **Scalability** | How should the system handle growth/spikes? | Horizontal app scaling, read replicas, CDN. |
| **Observability** | How will we detect and debug problems? | Logs, metrics, traces, alerts. |
| **Cost** | Which components are worth paying for? | Use queues/CDN/cache only when they solve real load. |

### SLA, SLO, SLI

| Term | Full Form | Meaning | Example |
|---|---|---|---|
| **SLI** | Service Level Indicator | Actual measured indicator. | p95 latency, error rate, uptime. |
| **SLO** | Service Level Objective | Internal reliability target. | p95 latency under 200 ms for 99% of requests. |
| **SLA** | Service Level Agreement | External promise/contract. | 99.9% uptime or customer credit. |

## 6. Data-Intensive vs Compute-Intensive

Before choosing components, identify where time and cost are being spent.

| Type | Bottleneck | Examples | Optimize With |
|---|---|---|---|
| **Data-Intensive** | Data movement, storage, reads, writes, network calls. | Video serving, feeds, analytics, search. | Caching, indexing, CDN, sharding, replication, batching. |
| **Compute-Intensive** | CPU/GPU computation. | Recommendations, ML inference, image/video processing, ranking. | Workers, parallel processing, GPUs, queues, precomputation. |

Rule of thumb: if time is lost moving/fetching data, treat it as data-intensive. If time is lost calculating results, treat it as compute-intensive.

## 7. Capacity Estimation

Use rough numbers to decide whether one server, one database, cache, CDN, queues, or sharding are actually needed.

| Estimate | Formula |
|---|---|
| **DAU to QPS** | `daily requests / 86,400` |
| **Peak QPS** | `average QPS * peak factor` |
| **Storage** | `number of objects * average object size * replication factor` |
| **Bandwidth** | `QPS * average response size` |
| **Read/Write Ratio** | `read QPS : write QPS` |
| **Cache Size** | `hot keys * average object size` |

### Quick Example

If 1M users each make 20 requests/day:

- Daily requests: `1,000,000 * 20 = 20,000,000`
- Average QPS: `20,000,000 / 86,400 ~= 232 QPS`
- Peak QPS with 10x spike: `~2,320 QPS`

Say assumptions clearly: "I will assume 1M DAU, 20 requests/user/day, 10x peak, and 80:20 read/write traffic. We can refine if needed."

## 8. Common System Design Concepts

| # | Topic | Compact Definition | Key Interview Notes |
|---:|---|---|---|
| 1 | **Client-Server Architecture** | Client sends requests; server processes and returns responses. | Most web apps follow this model. Stateless servers scale better. |
| 2 | **IP Address** | Unique address used to identify a device on a network. | IPv4 is 32-bit; IPv6 is 128-bit. Needed for routing packets. |
| 3 | **DNS** | Converts domain names into IP addresses. | DNS is hierarchical and cached. It lets users type names instead of raw IPs. |
| 4 | **Proxy / Reverse Proxy** | Proxy hides the client; reverse proxy hides backend servers. | Reverse proxy helps with routing, caching, SSL termination, security, and load balancing. |
| 5 | **Latency** | Time taken for a request to get a response. | Lower latency improves user experience. Measured in milliseconds. |
| 6 | **HTTP / HTTPS** | Protocols used for web communication. HTTPS is encrypted HTTP. | HTTPS uses TLS for secure communication. |
| 7 | **APIs** | Rules that allow software systems to communicate. | APIs define request format, response format, and behavior. |
| 8 | **REST API** | API style where resources are accessed using HTTP methods. | Uses `GET`, `POST`, `PUT`, `PATCH`, `DELETE`. Stateless and simple. |
| 9 | **GraphQL** | API query language where clients ask for exactly required data. | Reduces over-fetching and under-fetching, but caching and server cost can be harder. |
| 10 | **Databases** | Persistent storage for application data. | SQL for structured relational data; NoSQL for flexible or large-scale data. |
| 11 | **SQL vs NoSQL** | SQL is relational and schema-based; NoSQL is flexible and distributed-friendly. | SQL gives strong consistency; NoSQL can make horizontal scaling easier. |
| 12 | **Vertical Scaling** | Increasing CPU, RAM, or storage of one server. | Simple but limited by hardware and cost. |
| 13 | **Horizontal Scaling** | Adding more servers to handle more load. | Preferred at large scale. Requires load balancing and coordination. |
| 14 | **Load Balancer** | Distributes incoming traffic across multiple servers. | Improves scalability, availability, and fault tolerance. |
| 15 | **Database Indexing** | Data structure that makes database reads faster. | Speeds reads but slows writes and uses extra storage. |
| 16 | **Replication** | Keeps copies of data on multiple machines. | Improves availability and read performance, but can introduce consistency lag. |
| 17 | **Sharding** | Splits data across multiple databases or servers. | Useful when one DB cannot handle all data or traffic. Choose shard key carefully. |
| 18 | **Vertical Partitioning** | Splits a table or system by columns/features. | Example: login data separate from profile data. |
| 19 | **Caching** | Stores frequently used data in fast storage. | Reduces latency and DB load. Examples: Redis, CDN, browser cache. |
| 20 | **Denormalization** | Duplicates data to reduce joins and speed up reads. | Improves reads but increases storage and update complexity. |
| 21 | **CAP Theorem** | Distributed systems can strongly guarantee only two of Consistency, Availability, and Partition Tolerance. | During a network partition, choose consistency or availability. |
| 22 | **Blob Storage** | Storage for large unstructured files. | Used for images, videos, logs, backups, and documents. Example: S3. |
| 23 | **CDN** | Globally distributed cache for static content. | Reduces latency by serving content near users. |
| 24 | **WebSockets** | Persistent two-way connection between client and server. | Used in chat, live notifications, games, and dashboards. |
| 25 | **Webhooks** | Server-to-server event notification. | Avoids polling by calling another system when an event happens. |
| 26 | **Microservices** | Application split into small independent services. | Enables independent scaling and deployment, but adds network and operational complexity. |
| 27 | **Message Queues** | Asynchronous communication between services. | Helps with decoupling, buffering, retries, and background jobs. |
| 28 | **Rate Limiting** | Restricts number of requests from a user or client. | Protects against abuse, spam, overload, and accidental traffic spikes. |
| 29 | **API Gateway** | Single entry point for client requests. | Handles routing, auth, rate limiting, logging, SSL, and request aggregation. |
| 30 | **Idempotency** | Repeating the same request gives the same final result. | Important for retries, payments, order creation, and distributed systems. |

## 9. API Design

| Point | REST | GraphQL |
|---|---|---|
| Data Fetching | Server defines fixed response shape. | Client asks for exactly the fields it needs. |
| Endpoints | Multiple resource endpoints like `/users` and `/orders`. | Usually one endpoint with typed queries. |
| Contract | Resources plus HTTP methods. | Strong schema with types, queries, mutations, subscriptions. |
| Status / Errors | Uses HTTP status codes naturally. | Usually returns `200` with errors inside response body unless designed otherwise. |
| Strength | Simple, cache-friendly, widely understood. | Good for complex frontends and nested data. |
| Weakness | Can over-fetch or under-fetch data. | Server processing, authorization, and caching can be more complex. |
| Best Use | CRUD APIs, public APIs, simple resource models. | Apps needing flexible client-driven data fetching. |

### API Design Checklist

- Use nouns for resources: `/users`, `/orders`, `/products`.
- Use HTTP methods correctly: `GET`, `POST`, `PUT`, `PATCH`, `DELETE`.
- Define pagination for list APIs: cursor-based for large/changing data, offset for simple admin lists.
- Define sorting and filtering explicitly.
- Include auth and authorization requirements.
- Include idempotency keys for retryable creates like payments/orders.
- Return stable error codes and error response shape.
- Version public APIs carefully: `/v1/...` or compatible schema evolution.

### REST Interview Points

- REST is an architectural style, not a protocol. It uses HTTP well by modeling data as resources.
- Each request should carry the information needed to process it, which keeps servers stateless and easier to scale.
- Common success/error codes: `200 OK`, `201 Created`, `400 Bad Request`, `401 Unauthorized`, `403 Forbidden`, `404 Not Found`, `429 Too Many Requests`, `500 Internal Server Error`.
- REST pain points: over-fetching, under-fetching, rigid response shapes, and versioning overhead like `/v1/users`.

### GraphQL Interview Points

- GraphQL has three core operations: **queries** for reads, **mutations** for writes, and **subscriptions** for real-time updates.
- It works well when web, mobile, and other clients need different response shapes from the same backend.
- Main risks: expensive nested queries, authorization per field, N+1 resolver problems, harder HTTP/CDN caching, and query-based DoS risk.
- Common mitigations: query depth limits, cost analysis, persisted queries, rate limiting, batching, pagination, and DataLoader-style resolver batching.

## 10. Scaling Patterns

### Vertical vs Horizontal Scaling

| Point | Vertical Scaling | Horizontal Scaling |
|---|---|---|
| Meaning | Add more power to one machine. | Add more machines. |
| Example | 8 GB RAM to 32 GB RAM. | 2 servers to 10 servers. |
| Complexity | Easier. | More complex. |
| Limit | Hardware limit. | Can scale much further. |
| Fault Tolerance | Lower. | Higher when designed well. |

### Load Balancing Algorithms

| Algorithm | Meaning | Best Use |
|---|---|---|
| **Round Robin** | Sends requests to servers one by one in order. | Simple services with similar server capacity. |
| **Weighted Round Robin** | Sends more traffic to servers with higher assigned weight. | Mixed-capacity servers. |
| **Least Connections** | Sends traffic to the server with fewest active connections. | Long-lived or uneven requests. |
| **Least Response Time** | Sends traffic to the server responding fastest. | Latency-sensitive systems with varying server performance. |
| **Geo-Based Routing** | Routes users to the nearest/most appropriate region. | Global products where latency matters. |
| **IP Hashing** | Same client IP maps to the same server. | Session consistency when sticky routing is needed. |

Load balancers often use hybrid strategies, not one pure algorithm.

### Load Balancer Health Checks

| Check | Meaning |
|---|---|
| **Passive Health Check** | Observe real traffic responses without sending extra probe requests. |
| **Active Health Check** | Send probe requests like `/health` at fixed intervals. |
| **Interval** | How often health is checked. |
| **Timeout** | How long to wait before marking a check failed. |
| **Unhealthy Threshold** | Failures required before removing a server. |
| **Healthy Threshold** | Successes required before adding a server back. |

Health checks help with failover, autoscaling, and avoiding unhealthy servers.

### Proxy vs Reverse Proxy

| Type | Sits Near | Hides | Common Use |
|---|---|---|---|
| **Forward Proxy** | Client side. | Client identity/IP from destination servers. | Privacy, access control, filtering, client-side caching. |
| **Reverse Proxy** | Server side. | Backend server details from clients. | Load balancing, WAF, DDoS protection, SSL termination, caching, routing. |

## 11. Database Patterns

### SQL vs NoSQL

| Point | SQL | NoSQL |
|---|---|---|
| Data Model | Tables and rows. | Documents, key-value, column-family, graph. |
| Schema | Fixed schema. | Flexible schema. |
| Scaling | Usually vertical first. | Horizontal scaling friendly. |
| Transactions | Strong ACID support. | Often eventual consistency, depending on database. |
| Best For | Banking, orders, structured data. | Chats, feeds, analytics, large flexible data. |

### When To Pick SQL vs NoSQL

| Pick SQL When | Pick NoSQL When |
|---|---|
| Strong consistency matters. | Horizontal scale and high throughput matter more. |
| Schema is stable and relational. | Schema changes often or data is nested/flexible. |
| Joins and transactions are important. | Data can be stored/retrieved as independent documents or key-value records. |
| Payments, orders, inventory, banking. | Feeds, chat, activity logs, recommendations, metadata, sessions. |

Interview line: "I would start with SQL unless scale, flexible schema, or access patterns clearly push us toward NoSQL."

### ACID

| Property | Meaning |
|---|---|
| **Atomicity** | Transaction is all-or-nothing. |
| **Consistency** | Data moves from one valid state to another. |
| **Isolation** | Concurrent transactions do not corrupt each other. |
| **Durability** | Committed data survives crashes. |

### Common Database Types

| Type | Best For | Examples |
|---|---|---|
| **Relational** | Structured data, joins, transactions, financial correctness. | PostgreSQL, MySQL, Oracle. |
| **Key-Value** | Sessions, cache, simple fast lookups. | Redis, DynamoDB. |
| **Document** | Flexible JSON-like objects and evolving schema. | MongoDB, Couchbase. |
| **Graph** | Highly connected data and relationship traversal. | Neo4j, Amazon Neptune. |
| **Wide-Column** | Large distributed writes, analytics, event data. | Cassandra, HBase, Bigtable. |
| **In-Memory** | Very low latency reads/writes. | Redis, Memcached. |
| **Time-Series** | Metrics, logs, IoT readings, monitoring. | InfluxDB, TimescaleDB, Prometheus. |

### Indexing Trade-Off

Indexes make reads faster by avoiding full table scans. The trade-off is that writes become slower because the database must also update the index.

Use indexes on columns that are commonly used in `WHERE`, `JOIN`, `ORDER BY`, and range queries.

| Index Type | Best For | Watch Out |
|---|---|---|
| **B-Tree / B+ Tree** | Equality, sorting, and range queries. | Most common default in relational DBs. |
| **Hash Index** | Exact match lookups. | Poor for range queries and sorting. |
| **Bitmap Index** | Low-cardinality analytical columns. | Not ideal for high-write OLTP workloads. |
| **Composite Index** | Queries filtering by multiple columns together. | Column order matters. |
| **Covering Index** | Queries where index contains all needed columns. | Faster reads, more storage. |

Good indexing answer: "I would index high-cardinality columns that appear in hot `WHERE`, `JOIN`, and `ORDER BY` paths, then monitor query plans and remove unused indexes to avoid slowing writes."

### Replication

| Concept | Meaning |
|---|---|
| **Primary / Leader** | Handles writes. |
| **Replica / Follower** | Copies data from primary and handles reads. |
| **Read Scaling** | Spread read traffic across replicas. |
| **Failover** | Promote a replica if primary fails. |
| **Replication Lag** | Replica may serve slightly stale data. |

### Replication Types

| Type | Meaning | Trade-Off |
|---|---|---|
| **Single-Leader** | One leader handles writes and replicates to followers. | Simple writes, but leader can bottleneck/fail. |
| **Multi-Leader** | Multiple leaders accept writes and sync with each other. | Better regional writes, but conflict resolution is harder. |
| **Leaderless** | Writes/read go to multiple replicas; quorum decides success. | High availability, but more complex consistency handling. |

### Sync vs Async Replication

| Type | Meaning | Use When |
|---|---|---|
| **Synchronous** | Leader waits for replicas before confirming. | Stronger consistency matters more than latency. |
| **Asynchronous** | Leader confirms first, replicas catch up later. | Low latency and availability matter more. |

Leader failure handling: detect failure, choose the most up-to-date follower, promote it, and make other replicas follow the new leader.

### Sharding

Sharding is horizontal partitioning: split rows across multiple databases/servers using a shard key.

| Strategy | Meaning | Best Use |
|---|---|---|
| **Hash-Based** | `hash(key) % shard_count`. | Even distribution for large user/item sets. |
| **Range-Based** | Split by ranges like date or ID. | Range queries and ordered data. |
| **Geo-Based** | Store data near user region. | Lower latency and data residency needs. |
| **Directory-Based** | Lookup table maps keys to shards. | Maximum routing control. |

Sharding interview risks: bad shard key, hot shards, cross-shard joins, distributed transactions, rebalancing, operational complexity. Use consistent hashing to reduce data movement when adding/removing shards.

### Partitioning Rules

- Partitions combined should represent the complete dataset.
- Data and traffic should be distributed evenly.
- Avoid hot partitions where one node receives most requests.
- Pick partition keys based on access patterns, not just IDs.
- Use global indexes carefully: reads improve, writes become more complex.

### Vertical Partitioning vs Sharding

| Pattern | Splits By | Example |
|---|---|---|
| **Vertical Partitioning** | Columns/features. | `User_Profile`, `User_Login`, `User_Billing`. |
| **Sharding** | Rows/key ranges. | Users `0-999` on shard A, `1000-1999` on shard B. |

### CAP, Consistency, and PACELC

| Choice | Meaning | Example Use |
|---|---|---|
| **CP** | Prefer consistency during partition; may reject requests. | Banking, inventory correctness. |
| **AP** | Prefer availability during partition; may return stale data. | Shopping cart, feed, recommendations. |
| **CA** | Possible only when there is no partition. | Single-node systems, not realistic for distributed systems. |

- In distributed systems, partition tolerance is usually non-negotiable because networks fail.
- **Eventual consistency**: replicas converge later; good for feeds, counters, DNS/CDN-style data.
- **Strong consistency**: after a write succeeds, later reads see it; good for money, inventory, identity.
- **Tunable consistency**: choose stronger or weaker consistency per operation.
- **PACELC** extends CAP thinking: if there is a partition, choose availability vs consistency; else choose latency vs consistency.

## 12. Caching

Caching stores hot data in faster storage to reduce latency, database load, and cost.

| Strategy | Flow | Best For | Trade-Off |
|---|---|---|---|
| **Cache Aside / Lazy Loading** | App checks cache, loads from DB on miss, writes to cache. | Read-heavy data with infrequent updates. | App owns cache logic; stale data possible. |
| **Read Through** | Cache layer loads from DB on miss. | Simplifying app logic. | First miss is slower. |
| **Write Through** | Write to cache and DB synchronously. | Consistency-critical reads. | Higher write latency. |
| **Write Around** | Write only to DB; cache only when later read. | Write-heavy data not immediately reread. | First read after write is slower. |
| **Write Back / Write Behind** | Write to cache first, flush DB asynchronously. | Very fast writes. | Data loss risk if cache fails before flush. |

Cache interview risks: stale data, cache stampede, eviction policy, TTL choice, cache invalidation, hot keys, memory pressure, and whether cache failure should fail open or fail closed.

### Cache Eviction Policies

| Policy | Meaning | Good For |
|---|---|---|
| **LRU** | Remove least recently used data. | General-purpose caches where recent access predicts future access. |
| **MRU** | Remove most recently used data. | Streaming/one-time scan patterns where old consumed data is unlikely to be reused. |
| **LFU** | Remove least frequently used data. | Popularity-based access patterns. |
| **FIFO** | Remove oldest inserted data first. | Simple bounded cache. |
| **LIFO** | Remove newest inserted data first. | Stack-like temporary workloads. |

### Cache Stampede Controls

- Use TTL jitter so many keys do not expire at the same instant.
- Use request coalescing/single-flight so one miss triggers one DB fetch.
- Serve stale data briefly while refreshing in background.
- Prewarm very hot keys before launches or sale events.

### CDN

A CDN is a geographically distributed cache for web content such as HTML, JavaScript, CSS, images, videos, and downloadable files.

| Point | Meaning |
|---|---|
| **Edge Server** | CDN node close to the user. |
| **Origin Server** | Main backend/object storage where source content lives. |
| **Cache Hit** | Edge has the content and serves it directly. |
| **Cache Miss** | Edge fetches from origin, then may cache for future requests. |
| **TTL** | Controls how long CDN content remains fresh. |

CDN interview points: reduces latency, lowers origin load, improves availability for static content, can help with DDoS protection, but needs cache invalidation/versioning for frequently changing assets.

## 13. Communication Patterns

### Sync vs Async

| Type | Meaning | Use When | Examples |
|---|---|---|---|
| **Synchronous** | Caller waits for response. | User needs immediate result or operation must complete now. | Payment auth, inventory deduction, login. |
| **Asynchronous** | Caller submits work and continues. | Work can happen later or independently. | Email, SMS, analytics, report generation, video processing. |

Golden rule: if the system can "fire and forget" safely, consider async. If the user or next step needs immediate confirmation, keep it sync.

### Polling vs Webhooks vs WebSockets

| Method | Meaning | Best Use |
|---|---|---|
| **Polling** | Client repeatedly asks server for updates. | Simple systems with low-frequency updates. |
| **Webhooks** | Server notifies another server when an event happens. | Payment success, GitHub events, alerts. |
| **WebSockets** | Client and server keep a live two-way connection. | Chat, live tracking, multiplayer, dashboards. |

### WebSocket Interview Points

- WebSockets provide full-duplex communication over one persistent connection.
- They reduce repeated HTTP request overhead for real-time features.
- Challenges: connection management, scaling with many open connections, sticky sessions or shared pub/sub, heartbeat/ping checks, reconnect logic, and backpressure.

### Message Queues

Message queues decouple producers and consumers and allow asynchronous work.

| Queue Concept | Meaning |
|---|---|
| **Producer / Publisher** | Sends messages. |
| **Consumer / Subscriber** | Processes messages. |
| **Broker** | Stores, routes, and manages delivery. |
| **Acknowledgment** | Consumer confirms successful processing. |
| **DLQ** | Dead letter queue for messages that repeatedly fail. |

| Queue Type | Use |
|---|---|
| **Point-to-Point** | One message processed by one consumer. |
| **Pub/Sub** | Event broadcast to multiple subscribers. |
| **Priority Queue** | Higher-priority tasks processed first. |
| **Dead Letter Queue** | Debug and recover failed messages. |

Queue interview points: handle duplicate delivery with idempotent consumers, retry with backoff, monitor queue length and consumer lag, secure messages in transit and at rest, and use DLQs for poison messages.

### When To Use / Avoid Queues

| Use Queues For | Avoid Queues For |
|---|---|
| Async jobs, background processing, retries. | Real-time operations needing immediate response. |
| Decoupling services. | Low-volume direct calls where queue cost/complexity is unnecessary. |
| Buffering traffic spikes. | Operations requiring strict immediate acknowledgment. |
| Scheduled/deferred work. | Simple request/response CRUD paths. |

## 14. Rate Limiting and Gateways

### Rate Limiting Algorithms

| Algorithm | Meaning | Best Use | Trade-Off |
|---|---|---|---|
| **Token Bucket** | Tokens refill at fixed rate; each request spends one. | Allows controlled bursts. | Per-user memory can grow. |
| **Leaky Bucket** | Requests drain at steady rate. | Smooth traffic. | Drops bursts aggressively. |
| **Fixed Window** | Count requests per fixed time window. | Simple limits. | Boundary bursts can exceed intended rate. |
| **Sliding Window Log** | Store timestamps in moving window. | Accurate low-volume APIs. | Memory heavy at high scale. |
| **Sliding Window Counter** | Weighted current + previous window counts. | Balanced accuracy and memory. | More complex than fixed window. |

When rate limiting, return `429 Too Many Requests` and include headers/retry guidance so clients can back off.

### API Gateway Features

| Feature | Why It Matters |
|---|---|
| **Routing** | Sends requests to the correct backend service. |
| **Authentication / Authorization** | Centralizes identity and permission checks. |
| **Rate Limiting** | Protects backend services from abuse and overload. |
| **Load Balancing** | Routes to healthy instances. |
| **Caching** | Reduces latency and backend load. |
| **Request / Response Transformation** | Adapts old and new service formats. |
| **Service Discovery** | Finds dynamic service instances. |
| **Circuit Breaking** | Stops calling unhealthy services temporarily. |
| **Logging / Monitoring** | Tracks traffic, errors, latency, and anomalies. |

## 15. Idempotency

Idempotency means repeating the same operation produces the same final state as doing it once.

| Area | Interview Point |
|---|---|
| **Why** | Retries are common after timeouts, network failures, refreshes, and queue redelivery. |
| **HTTP Methods** | `GET`, `PUT`, and `DELETE` are usually idempotent; `POST` is usually not unless designed with keys. |
| **Idempotency Key** | Client sends a unique request ID; server stores processed IDs and returns the previous result for duplicates. |
| **Database Support** | Use unique constraints, upserts, optimistic locking, or version numbers. |
| **Messaging** | Store processed message IDs so consumers can safely ignore duplicates. |
| **Retry Policy** | Combine idempotency with exponential backoff to avoid retry storms. |

Classic example: payment creation should require an idempotency key so a retry does not charge the user twice.

## 16. Fault Tolerance

Fault tolerance means the system keeps working, or degrades gracefully, when components fail.

| Fault Type | Examples | Mitigation |
|---|---|---|
| **Hardware Fault** | Disk full, server crash, DB failure, network cable/power issue. | Replication, redundancy, backups, failover, health checks. |
| **Software Fault** | Bug, missing exception handling, bad config, deployment mismatch, slow query. | Testing, canary deploys, rollback, feature flags, circuit breakers. |
| **Human Fault** | Wrong config, bad manual fix, unsafe deployment. | Reviews, automation, access control, runbooks, audit logs. |

### Reliability Patterns

| Pattern | Meaning |
|---|---|
| **Retry With Backoff** | Retry transient failures without overwhelming downstream systems. |
| **Timeouts** | Do not wait forever for unhealthy dependencies. |
| **Circuit Breaker** | Stop calling a failing dependency temporarily. |
| **Bulkhead** | Isolate failure so one service/path does not exhaust all resources. |
| **Graceful Degradation** | Serve reduced functionality instead of full outage. |
| **Failover** | Move traffic to healthy instance/region. |
| **Backups / Restore** | Protect against data loss and corruption. |

## 17. Monitoring and Observability

Observability tells you whether the system is healthy and why it is unhealthy.

| Pillar | What It Answers |
|---|---|
| **Logs** | What happened for a request/event? |
| **Metrics** | Is the system healthy over time? |
| **Traces** | Where did a request spend time across services? |

### API Monitoring

| Metric | Why It Matters |
|---|---|
| **Throughput / QPS** | Detect load, saturation, and scaling needs. |
| **Error Rate** | Track `4xx`, `5xx`, failed dependencies, and bad releases. |
| **Latency Percentiles** | p50, p90, p95, p99 reveal tail latency better than averages. |
| **Health Checks** | Detect whether service instances should receive traffic. |

### Machine Monitoring

| Metric | Why It Matters |
|---|---|
| **CPU Usage** | Detect compute saturation. |
| **Memory Usage** | Detect leaks, cache pressure, OOM risk. |
| **Disk Usage / Disk I/O** | Detect storage exhaustion and slow reads/writes. |
| **Network I/O** | Detect bandwidth saturation and packet issues. |

Good interview line: "I would alert on symptoms first, such as high error rate and p95 latency, then use logs/traces/infra metrics to find the cause."

## 18. Core Metrics and Estimates

| Metric | Meaning |
|---|---|
| **Latency** | Time taken to process one request. |
| **Throughput** | Number of requests processed per second. |
| **QPS** | Queries per second. |
| **Availability** | `uptime / total time`. |
| **Error Rate** | Failed requests / total requests. |
| **P95 / P99 Latency** | Latency experienced by the slowest 5% / 1% of requests. |
| **Fanout** | Number of downstream calls triggered by one request. |
| **Storage Estimate** | `users * data per user`. |
| **Bandwidth Estimate** | `requests per second * response size`. |

### Availability Numbers

| Target | Approx Downtime / Year |
|---|---:|
| **99%** | 3.65 days |
| **99.9%** | 8.76 hours |
| **99.99%** | 52.6 minutes |
| **99.999%** | 5.26 minutes |

## 19. Common Architecture Skeleton

```txt
Client
  |
  v
DNS / CDN
  |
  v
Load Balancer
  |
  v
API Gateway / Reverse Proxy
  |
  v
Application Servers
  |        |        |
  v        v        v
Cache    Queue    Database
Redis    Kafka    SQL/NoSQL
          |
          v
     Worker Services
```

## 20. Case Study: Video Streaming

Scope example: design the path from uploaded video to user playback, not the entire Netflix/YouTube product.

### Functional Requirements

- Creator can upload a video.
- System stores the original video.
- System transcodes video into multiple resolutions.
- User can stream video without downloading the full file.
- Player can switch quality based on bandwidth/device.

### Non-Functional Requirements

- Low startup latency.
- Smooth playback with minimal buffering.
- High availability across regions.
- Large object storage for video files.
- High read bandwidth.
- Background processing for transcoding.

### Key Concepts

| Concept | Why It Matters |
|---|---|
| **FPS** | More frames per second means smoother video and larger file size. |
| **Resolution** | 4K, 1080p, 720p, 480p change quality and bandwidth needs. |
| **Chunking / Segments** | Download small video pieces instead of full video upfront. |
| **Adaptive Bitrate Streaming** | Switch resolution based on network throughput and device. |
| **CDN** | Serve segments close to users. |
| **Queues + Workers** | Transcode video asynchronously into multiple formats/resolutions. |

### Architecture

```txt
Uploader
  |
  v
Upload API
  |
  v
Blob Storage (original video)
  |
  v
Transcoding Queue
  |
  v
Worker Pool (4K, 1080p, 720p, 480p)
  |
  v
Segment Storage + Metadata DB
  |
  v
CDN
  |
  v
Video Player
```

### Interview Trade-Offs

- Do transcoding asynchronously because users do not need all resolutions immediately during upload.
- Store large videos in blob/object storage, not relational DB.
- Use CDN because video is read-heavy and geographically distributed.
- Use adaptive bitrate because user bandwidth and device size change.
- Cache future segments in the player, but avoid downloading the whole video upfront.

## 21. Interview Flow

1. **Clarify requirements**: users, features, reads/writes, latency, availability, consistency.
2. **Estimate scale**: QPS, storage, bandwidth, peak traffic.
3. **Define APIs**: endpoints, request/response shape, idempotency needs.
4. **Design data model**: entities, relationships, indexes, partitioning needs.
5. **Draw HLD**: client, gateway, services, database, cache, queue, workers.
6. **Handle bottlenecks**: caching, load balancing, replication, sharding, async processing.
7. **Discuss trade-offs**: consistency vs availability, cost vs performance, simplicity vs flexibility.
8. **Add reliability**: monitoring, alerts, retries, backups, rate limiting, failover.

### Good Trade-Off Language

- "For the first version, I would keep this as a monolith with clear module boundaries, then split services only when independent scaling or deployment becomes necessary."
- "I would cache this because reads dominate writes, but I would define TTL and invalidation carefully to avoid stale user-visible data."
- "I would choose SQL here because correctness and relational constraints matter more than flexible schema."
- "I would use async processing because this work does not need to block the user request."
- "I would add sharding only after replication, indexing, caching, and query optimization are no longer enough."

## 22. Source Links For Deeper Revision

- Main article: [30 System Design Concepts](https://blog.algomaster.io/p/30-system-design-concepts)
- [What's an API?](https://blog.algomaster.io/p/whats-an-api)
- [Proxy vs Reverse Proxy](https://blog.algomaster.io/p/proxy-vs-reverse-proxy-explained)
- [REST vs GraphQL](https://blog.algomaster.io/p/rest-vs-graphql)
- [15 Types of Databases](https://blog.algomaster.io/p/15-types-of-databases)
- [SQL vs NoSQL](https://blog.algomaster.io/p/sql-vs-nosql-7-key-differences)
- [Load Balancing Algorithms](https://blog.algomaster.io/p/load-balancing-algorithms-explained-with-code)
- [Database Indexes](https://blog.algomaster.io/p/a-detailed-guide-on-database-indexes)
- [Database Sharding](https://blog.algomaster.io/p/what-is-database-sharding)
- [Caching Strategies](https://blog.algomaster.io/p/top-5-caching-strategies-explained)
- [CAP Theorem](https://blog.algomaster.io/p/cap-theorem-explained)
- [Content Delivery Network](https://blog.algomaster.io/p/content-delivery-networks)
- [WebSockets](https://blog.algomaster.io/p/websockets)
- [Message Queues](https://blog.algomaster.io/p/message-queues)
- [Rate Limiting Algorithms](https://blog.algomaster.io/p/rate-limiting-algorithms-explained-with-code)
- [API Gateway](https://blog.algomaster.io/p/what-is-an-api-gateway)
- [Idempotency](https://blog.algomaster.io/p/idempotency-in-distributed-systems)

## 23. Best Final Line in Interviews

> The final design depends on the scale and trade-offs. For a small system, I would keep it simple with one database and cache. As traffic grows, I would add load balancing, replication, sharding, queues, CDN, monitoring, and stronger fault tolerance.
