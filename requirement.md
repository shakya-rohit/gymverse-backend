# Gym Management Web App - Requirements

## 🎯 Functional Requirements

### 0. Gym & Branch Management

- Gym owner can register with gym name, email, and password
- A unique workspace is created for each gym (e.g., `gymverse.com/fitzone`)
- Gym name must be unique and acts as a namespace
- Each gym account can have multiple branches (e.g., Andheri, Bandra)
- Branches are managed by the gym owner
- All data (trainers, members, payments) is scoped to a specific branch
- Users (trainers/admins) are scoped to a branch or gym level depending on their role

### 1. Member Management

- Add new members with details (name, email, phone, join date, due date, photo)
- Assign member to a specific branch and trainer
- Update and delete member information
- Track membership status (active, expiring, expired)

### 2. Trainer Management

- Add/update/delete trainer profiles
- Assign trainer to a specific branch
- Assign members to trainers
- Trainers can only access data within their assigned branch

### 3. Payment Tracking

- Record membership payments
- Auto-calculate and update next due date
- View payment history per member and branch

### 4. Email Notifications

- Reminder email sent 15 days before due date
- Revocation email sent after due date expires
- Scheduler runs daily to check for expiring/expired memberships

### 5. Dashboard & Reports

- Display statistics (active members, expiring soon, revenue, etc.) per branch and overall
- Graphs via Chart.js
- Gym owner sees aggregate stats across branches
- Trainers see stats scoped to their branch

### 6. Data Export

- Export member and payment lists to Excel or PDF
- Export filtered by branch or trainer if needed

### 7. Photo Upload

- Upload and store member photos (local file system or S3)
- Link photos with member profile

### 8. Authentication (to be implemented later)

- JWT-based login/logout
- Role-based access: ADMIN, TRAINER
- Users are scoped to their gym and branch (if not owner)
- Gym owners can create trainer accounts scoped to a branch

## 🧱 Non-Functional Requirements

- Deployable on Heroku or AWS
- Use H2 database for easy local and cloud deployment
- Use open-source libraries where possible
- Clean, responsive UI (Angular Material or Bootstrap)

### Additional Considerations

#### 🔒 Security
- Secure API endpoints with HTTPS
- Input validation and sanitization to prevent XSS/SQL injection
- Sensitive config values (API keys, secrets) stored in env files

#### 📈 Performance
- Backend should respond to standard requests in < 500ms
- Angular frontend should be optimized for lazy loading where applicable

#### 🧪 Testability
- Backend should include unit tests (JUnit) and integration tests (Spring Test)
- Frontend should use Jasmine/Karma for component testing

#### 🔄 Scalability & Extensibility
- Backend modular structure to support future integrations (e.g., Stripe payments)
- DB schema designed to scale from H2 to PostgreSQL with minimal change
- Future support for sub-admins, roles, or gym-specific branding

#### 📦 Maintainability
- Codebase follows standard naming conventions and layered architecture
- Clearly documented REST APIs (e.g., with Swagger/OpenAPI)

## 🧰 Tech Stack

- Frontend: Angular
- Backend: Spring Boot
- DB: H2 (can be replaced with PostgreSQL later)
- Email: JavaMailSender or SendGrid API
- Charts: Chart.js
- File Export: xlsx, jspdf
- Auth: Spring Security + JWT (Phase 2)

## 🧑‍💻 User Roles

- Admin (Gym Owner)
- Trainer
- Member (passive role, email only)

