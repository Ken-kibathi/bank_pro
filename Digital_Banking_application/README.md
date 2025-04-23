# Digital_Banking_Platform
A Digital Banking Platform that allows users to create accounts, manage transactions, transfer funds between accounts, generate bank statements, and provide admin controls — entirely microservices-based and fully secure.
It Has 
1. Account Service
Features:
Create & manage bank accounts.
Link accounts to customers.
Freeze/unfreeze accounts.
2. Transaction Service
Features:
Deposit, Withdraw, and Transfer funds.
Handle transactional integrity (e.g., debit one account, credit another atomically).
Generate mini statements.
3. User Service (Identity & Access Management)
Features:
User registration (bank admin and customers).
Login/logout.
JWT token generation and verification.
Role-based authorization (CUSTOMER, ADMIN).
4. Notification Service (Event-driven)
Features: 
Notify customers via SMS/Email on: 
Successful deposits/withdrawals/transfers.
Low balance alerts.
Consume events from Apache Kafka.
5. Audit & Logging Service
Features: 
Capture and store audit logs for: 
All transactions.
Account operations.
Expose secure API for admins to view logs.
6. API Gateway
Purpose:
Single entry point for all clients.
Handle routing, authentication filter, rate-limiting, and fallback.
7. Service Discovery
Purpose: 
Auto-discover running instances of microservices.

