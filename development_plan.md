# Gym Management Web App - Development Plan

## ✅ Phase 1: Planning & Design
- Define functional and non-functional requirements
- Identify core entities and relationships
- Create ER diagram and Use Case diagram


## ✅ Phase 2: Backend Development (Spring Boot)

### Step 1: Project Setup
- Use Spring Initializr: Web, JPA, H2, Mail, Validation
- Folder structure: controller, service, repository, model, config, util

### Step 2: Core Modules (without auth)
- Member Module
  - CRUD endpoints
  - Assign Trainer
  - Membership status logic
- Trainer Module
  - CRUD endpoints
- Payment Module
  - Record payments
  - Calculate next due
- Email Module
  - Reminder and revocation email logic
- Scheduler Module
  - Spring `@Scheduled` job to run daily and trigger emails


## ✅ Phase 3: Frontend Development (Angular)

### Step 1: Angular Setup
- Angular CLI, routing, Angular Material or Bootstrap

### Step 2: Core UI Pages
- Member List & Form (Add/Edit)
- Trainer List & Form
- Payment History Page
- Dashboard with Chart.js
- Export Buttons (Excel, PDF)
- Upload Member Photo


## ⏳ Phase 4: Authentication & Security (Deferred)
- Implement JWT Auth (Spring Security)
  - `/auth/login`, `/auth/register`
  - Roles: ADMIN, TRAINER
- Add role-based route guards in Angular
- Protect backend endpoints


## ✅ Phase 5: Testing & Deployment
- Unit & Integration Testing
- Dockerize Spring Boot App (optional)
- Deploy to Heroku or AWS EC2/Beanstalk
- Use PostgreSQL in production if needed


## 🧩 Optional Enhancements (Future Scope)
- Member mobile notifications (Firebase)
- Member portal to view their profile
- Online payment integration

