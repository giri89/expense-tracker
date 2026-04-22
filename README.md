# 💰 Expense Tracker (Java + MySQL + JDBC)

A secure and scalable **web-based Expense Tracker application** developed using **Java, Servlets, MySQL, and JDBC**, deployed on **Apache Tomcat**. This application enables users to manage daily expenses efficiently with robust backend processing and structured database operations.

---

## 🚀 Features

* 🔐 **User Authentication & Session Management**

  * Secure registration and login functionality
  * Session-based access control using Servlets

* 📊 **Expense Management (CRUD Operations)**

  * Add, view, update, and delete expenses
  * Structured handling of user-specific financial data

* ⚡ **Efficient Database Integration**

  * JDBC-based connectivity with MySQL
  * Optimized SQL queries for high performance

* 🌐 **Web-Based Interface**

  * Simple and responsive UI using HTML
  * Seamless interaction between frontend and backend via Servlets

* 🧪 **Testing & Debugging**

  * Dedicated test classes for database validation
  * Ensured reliability through debugging and validation

---

## 🛠️ Tech Stack

* **Backend:** Java (Servlets, JDBC)
* **Frontend:** HTML
* **Database:** MySQL
* **Server:** Apache Tomcat 9
* **IDE:** Eclipse

---

## 📂 Project Structure

```
ExpenseTracker/
│── src/main/java/
│   ├── com.expense.db/
│   │   └── DBConnection.java
│   │
│   ├── com.expense.servlet/
│   │   ├── AddExpenseServlet.java
│   │   ├── DashboardServlet.java
│   │   ├── DeleteExpenseServlet.java
│   │   ├── EditExpenseServlet.java
│   │   ├── UpdateExpenseServlet.java
│   │   ├── ViewExpenseServlet.java
│   │   ├── LoginServlet.java
│   │   ├── LogoutServlet.java
│   │   └── RegisterServlet.java
│   │
│   └── com.expense.test/
│       └── TestDB.java
│
│── src/main/webapp/
│   ├── META-INF/
│   ├── WEB-INF/
│   ├── addExpense.html
│   ├── dashboard.html
│   ├── login.html
│   └── register.html
│
│── build/
│── libraries/
│── server/
│   └── Apache Tomcat v9
│── README.md
```

---

## ⚙️ Setup & Installation

1. **Clone the repository**

   ```bash
   git clone <your-repo-link>
   ```

2. **Import into Eclipse**

   * Open Eclipse → Import → Existing Projects into Workspace

3. **Configure Apache Tomcat**

   * Add Apache Tomcat v9 server in Eclipse
   * Deploy the project on the server

4. **Database Setup**

   * Create a MySQL database (e.g., `expense_tracker`)
   * Create required tables for users and expenses

5. **Update Database Configuration**

   * Edit `DBConnection.java` with your MySQL credentials

6. **Run the Application**

   * Start the Tomcat server
   * Access via:

     ```
     http://localhost:8080/ExpenseTracker/
     ```

---

## 🔄 Application Flow

1. User Registration
2. User Login
3. Dashboard Access
4. Add / View / Edit / Delete Expenses
5. Logout

---

## 📈 Key Highlights

* Strong implementation of **Java Servlets and JDBC**
* Clean package structure following **modular design principles**
* Efficient handling of HTTP requests and responses
* Secure session tracking for authenticated users
* Designed for **scalability and maintainability**

---

## 🔮 Future Enhancements

* Improved UI using CSS/Bootstrap
* Integration with JavaScript for dynamic interactions
* Expense reports with charts and analytics
* REST API integration for mobile support

---

## 👨‍💻 Author

**Girijesh Kumar Rajak**

---

## 📄 License

This project is open-source and available under the MIT License.

---

⭐ If you found this project useful, consider giving it a star!


