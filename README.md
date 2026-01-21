📖 Project Overview

This project is a Library Management System developed using Spring Boot.
It provides RESTful APIs to manage books, members, librarians, loans, and reservations, including borrowing and returning books, reservations approval, penalty calculations, and popularity tracking.
The system follows a layered architecture:
 - Controller layer (REST APIs)
 - Service layer (business logic)
 - Repository layer (database access using JPA)
 - DTOs for clean data transfer
 - Unit & integration testing using Spring Boot Test

🧱 Architecture Overview

Controller → Service → Repository → Database
Controllers handle HTTP requests and responses
Services contain all business logic
Repositories interact with the database using Spring Data JPA
DTOs decouple API requests from entity models
Entities represent database tables

🗄️ Database Entities & Relationships

📘 Book
Represents a book in the library.
Fields:
 - id
 - title
 - author
 - category
 - available
 - borrowCount
Relationships:
One Book → Many Loans
One Book → Many Reservations

👤 Member
Represents a library member.
Fields:
 - id
 - name
 - email
 - unpaidPenalty
Relationships:
One Member → Many Loans
One Member → Many Reservations

📄 Loan
Represents borrowing history of a book.
Fields:
 - id
 - borrowDate
 - dueDate
 - returnDate
Relationships:
Many Loans → One Book
Many Loans → One Member

⏳ Reservation
Represents a reservation request for a book.
Fields:
 - id
 - reservationDate
Relationships:
Many Reservations → One Book
Many Reservations → One Member

🧑‍💼 Librarian
Represents a librarian who manages reservations and penalties.
Fields:
 - id
 - name
 - role

📦 DTOs (Data Transfer Objects)

DTOs are used to validate and structure incoming requests.
The DTO'S used:
 - CreateBookRequest
 - CreateMemberRequest
 - CreateLibrarianRequest
 - BorrowBookRequest
Why DTOs?
Prevent exposing entity internals
Improve validation
Clean API design

🌐 REST Controllers & Endpoints

📘 BookController (/books)
| Method | Endpoint                      | Description             |
| ------ | ----------------------------- | ----------------------- |
| POST   | `/books`                      | Create a new book       |
| GET    | `/books`                      | Get all books           |
| GET    | `/books/{id}`                 | Get book by ID          |
| GET    | `/books/search?title=`        | Search by title         |
| GET    | `/books/category?category=`   | Filter by category      |
| GET    | `/books/available?available=` | Filter by availability  |
| GET    | `/books/popular?limit=`       | Get most borrowed books |

👤 MemberController (/members)
| Method | Endpoint        | Description      |
| ------ | --------------- | ---------------- |
| POST   | `/members`      | Create member    |
| GET    | `/members`      | Get all members  |
| GET    | `/members/{id}` | Get member by ID |
| PUT    | `/members/{id}` | Update member    |
| DELETE | `/members/{id}` | Delete member    |

📄 LoanController (/loans)
| Method | Endpoint                 | Description   |
| ------ | ------------------------ | ------------- |
| POST   | `/loans/borrow`          | Borrow a book |
| POST   | `/loans/return/{loanId}` | Return a book |
| GET    | `/loans`                 | Get all loans |

⏳ ReservationController (/reservations)
| Method | Endpoint                          | Description          |
| ------ | --------------------------------- | -------------------- |
| POST   | `/reservations?bookId=&memberId=` | Reserve book         |
| DELETE | `/reservations/{id}?memberId=`    | Cancel reservation   |
| GET    | `/reservations`                   | Get all reservations |

🧑‍💼 LibrarianController (/librarians)
| Method | Endpoint                              | Description         |
| ------ | ------------------------------------- | ------------------- |
| POST   | `/librarians`                         | Add librarian       |
| GET    | `/librarians`                         | Get all librarians  |
| GET    | `/librarians/{id}`                    | Get librarian by ID |
| DELETE | `/librarians/{id}`                    | Delete librarian    |
| POST   | `/librarians/approveReservation/{id}` | Approve reservation |
| GET    | `/librarians/totalPenalties`          | Calculate penalties |

Approve Reservation Logic:
 - Finds reservation
 - Deletes it after approval
 - Can be extended to auto-loan logic

🧠 Business Logic (Service Layer)

LoanService:
 - Borrow book
 - Return book
 - Enforces business rules: No double borrowing, No double return

LibrarianService:
 - Manage librarians
 - Approve reservations
 - Calculate total unpaid penalties

MemberService:
 - Create and update members
 - Maintain penalties

ReservationService:
 - Create and cancel reservations
 - Validate member ownership

🧪 Testing Strategy

🧪 Controller Tests
Verify HTTP status codes
Ensure endpoints respond correctly
Examples:
 - BookControllerTest
 - LoanControllerTest
 - LibrarianControllerTest

🧪 Service Tests
Verify business logic correctness
Test database interactions
Examples:
 - LoanServiceTest
 - MemberServiceTest
 - LibrarianServiceTest

🚀 Technologies Used

 - Java 17+
 - Spring Boot
 - Spring Data JPA
 - Hibernate
 - MySQL (Production)
 - Gradle
 - JUnit 5
 - MockMvc
 - Jackson
DTOs decouple API requests from entity models

Entities represent database tables
