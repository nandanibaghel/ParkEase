# 🚗 ParkEase – Smart Parking Finder

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-17-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-green)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)](https://www.mysql.com/)

## 📌 Overview

ParkEase is a comprehensive Full-Stack Smart Parking Management System designed to streamline parking operations. It empowers users to effortlessly search and book parking slots, enables owners to manage their parking spaces efficiently, and provides admins with powerful tools to monitor system activities and generate insights.

### 🌟 Key Highlights
- **User-Friendly Interface**: Intuitive dashboards for all user roles
- **Real-Time Availability**: Dynamic slot booking with conflict prevention
- **Secure Authentication**: JWT-based role-based access control
- **Scalable Architecture**: Built with Spring Boot and MySQL
- **Cross-Platform**: Responsive frontend with modern web technologies

## 👩‍💻 Team Members

| Name | Role | Responsibilities |
|------|------|------------------|
| Nandani Baghel | Backend Developer | 
| Priyanka Murlidharan | Backend Developer | 
| Akshaya V | Frontend Developer |
| Charishma Gangireddy | Frontend Developer |

## 🧑‍🤝‍🧑 User Roles & Permissions

### 👤 User
- 🔍 Search and filter parking slots by location and vehicle type
- 📅 Book parking slots with flexible time durations
- 📋 View and manage personal bookings
- ❌ Cancel bookings with appropriate policies

### 🧑‍💼 Owner
- 🏢 Create and manage multiple parking areas
- 🅿️ Add, update, and remove parking slots
- 💰 Set pricing per hour for different vehicle types
- 📊 Monitor bookings and revenue for owned spaces

### 🛠️ Admin
- 👥 Monitor and manage user accounts
- 📈 View comprehensive system statistics
- 🔧 Oversee all bookings and parking slots
- 📊 Generate reports on platform usage

## ⚙️ Tech Stack

### 🎨 Frontend
- **HTML5** - Semantic markup
- **CSS3** - Responsive styling
- **JavaScript** - Interactive functionality
- **VS Code Live Server** - Development server

### ⚙️ Backend
- **Java 17** - Programming language
- **Spring Boot 3.0** - Framework for rapid development
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Data persistence with Hibernate
- **JWT** - Token-based authentication

### 🗄️ Database
- **MySQL 8.0** - Relational database management

### 🔧 Development Tools
- **Maven** - Build automation and dependency management
- **Postman** - API testing and documentation
- **Git & GitHub** - Version control and collaboration

## 🚀 Features

### 🔐 Authentication & Authorization
- ✅ User registration and login
- 🔑 JWT-based secure authentication
- 🛡️ Role-based access control (User, Owner, Admin)
- 🔒 Password encryption and security best practices

### 🏢 Parking Area Management (Owner)
- 🆕 Create new parking areas with location details
- 📍 Add precise location and pincode information
- 📝 Manage multiple parking areas per owner
- ✏️ Update area information as needed

### 🅿️ Parking Slot Management (Owner)
- ➕ Add parking slots to specific areas
- 💰 Set competitive pricing per hour
- 🚗 Support for different vehicle types (CAR/BIKE)
- 📊 Track real-time slot availability
- 🔄 Update slot details dynamically

### 📅 Booking System (User)
- 🕒 Book slots with custom time durations
- 🚫 Prevent double bookings through validation
- 💳 Dynamic cost calculation based on duration
- ✅ Instant booking confirmation

### 📖 Booking Management
- 👀 View comprehensive booking history
- ❌ Cancel bookings with refund policies
- 📊 Track booking status (Active, Completed, Cancelled)
- 🔔 Receive booking notifications

### 📊 Admin Dashboard
- 👥 Comprehensive user management
- 📈 Real-time booking and slot monitoring
- 💹 Platform statistics and analytics
- 📋 Generate detailed reports

## 🎨 Frontend Modules

### 🏠 Landing Page
- 🌟 Attractive hero section with system overview
- 🔗 Easy navigation to login/register for all roles
- 📱 Responsive design for all devices

### 👤 User Dashboard
- 🔍 Advanced search and filtering options
- 🚗 Vehicle type selection (Car/Bike)
- 📅 Interactive calendar for booking
- 📋 Booking history with status indicators

### 🧑‍💼 Owner Dashboard
- ➕ Intuitive forms for adding parking areas and slots
- 📊 Visual representation of slot occupancy
- 💰 Revenue tracking and analytics
- 📋 Booking management interface

### 🛠️ Admin Dashboard
- 📊 System-wide statistics overview
- 👥 User management tools
- 📈 Charts for bookings, revenue, and slot utilization
- 🔧 Administrative controls

## 🛠️ Installation & Setup

### Prerequisites

Before setting up ParkEase, ensure you have the following installed on your system:

- **Java 17 or higher**: Download from [Oracle](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) or [OpenJDK](https://openjdk.java.net/)
- **Maven 3.6+**: Download from [Maven Apache](https://maven.apache.org/download.cgi)
- **MySQL 8.0**: Download from [MySQL](https://dev.mysql.com/downloads/mysql/)
- **Git**: Download from [Git](https://git-scm.com/downloads)
- **Web Browser**: Any modern browser (Chrome, Firefox, Edge)

### Environment Setup

1. **Verify Java Installation:**
   ```bash
   java -version
   ```
   Expected output: Java version 17.x.x

2. **Verify Maven Installation:**
   ```bash
   mvn -version
   ```
   Expected output: Apache Maven 3.6.x or higher

3. **Setup MySQL Database:**
   - Install MySQL Server
   - Start MySQL service
   - Create a database named `parkease`:
     ```sql
     CREATE DATABASE parkease;
     ```
   - Create a user (optional, or use root):
     ```sql
     CREATE USER 'parkease_user'@'localhost' IDENTIFIED BY 'your_password';
     GRANT ALL PRIVILEGES ON parkease.* TO 'parkease_user'@'localhost';
     FLUSH PRIVILEGES;
     ```

### Backend Setup

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/parkease.git
   cd parkease
   ```

2. **Configure Database Connection:**
   - Navigate to `backend/src/main/resources/application.properties`
   - Update the database configuration:
     ```properties
     # Database Configuration
     spring.datasource.url=jdbc:mysql://localhost:3306/parkease?useSSL=false&serverTimezone=UTC
     spring.datasource.username=parkease_user
     spring.datasource.password=your_password
     spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

     # JPA/Hibernate Configuration
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

     # JWT Configuration
     jwt.secret=your_jwt_secret_key_here
     jwt.expiration=86400000

     # Server Configuration
     server.port=8080
     ```

3. **Build the Backend:**
   ```bash
   cd backend
   mvn clean compile
   ```

4. **Run Tests (Optional):**
   ```bash
   mvn test
   ```

5. **Run the Backend Application:**
   ```bash
   mvn spring-boot:run
   ```
   The backend API will be available at `http://localhost:8080`

### Frontend Setup

1. **Navigate to Frontend Directory:**
   ```bash
   cd frontend
   ```

2. **Serve the Application:**
   - **Option 1: Using VS Code Live Server Extension**
     - Install the "Live Server" extension in VS Code
     - Right-click on `index.html` and select "Open with Live Server"
   
   - **Option 2: Using Python HTTP Server (if Python is installed)**
     ```bash
     python -m http.server 3000
     ```
     Access at `http://localhost:3000`

   - **Option 3: Using Node.js HTTP Server**
     - Install `http-server` globally:
       ```bash
       npm install -g http-server
       ```
     - Serve the frontend:
       ```bash
       http-server -p 3000
       ```

3. **Access the Application:**
   - Open your web browser and navigate to `http://localhost:3000`
   - The frontend will connect to the backend API at `http://localhost:8080`

### Running the Full Application

1. **Start Backend First:**
   ```bash
   cd backend
   mvn spring-boot:run
   ```

2. **Start Frontend in a New Terminal:**
   ```bash
   cd frontend
   # Use one of the serving methods above
   http-server -p 3000
   ```

3. **Access ParkEase:**
   - Frontend: `http://localhost:3000`
   - Backend API: `http://localhost:8080`

### Troubleshooting

- **Port Conflicts:** If port 8080 or 3000 is in use, change the port in `application.properties` or serving command
- **Database Connection Issues:** Ensure MySQL is running and credentials are correct
- **CORS Issues:** The backend is configured to allow requests from `http://localhost:3000`
- **Build Failures:** Ensure all dependencies are downloaded with `mvn clean install`

### Development Workflow

- **Backend Changes:** Restart the Spring Boot application after code changes
- **Frontend Changes:** Refresh the browser (Live Server auto-refreshes on file changes)
- **Database Schema Changes:** The application uses `hibernate.ddl-auto=update` for automatic schema updates

## 📡 API Endpoints

### Authentication
- `POST /api/auth/signup` - User registration
- `POST /api/auth/login` - User login
- `POST /api/auth/logout` - User logout

### User Operations
- `GET /api/parking-areas` - Get available parking areas
- `POST /api/bookings` - Create new booking
- `GET /api/bookings/user/{userId}` - Get user bookings
- `DELETE /api/bookings/{bookingId}` - Cancel booking

### Owner Operations
- `POST /api/parking-areas` - Create parking area
- `POST /api/parking-slots` - Add parking slot
- `GET /api/parking-slots/owner/{ownerId}` - Get owner slots
- `GET /api/bookings/owner/{ownerId}` - Get owner bookings

### Admin Operations
- `GET /api/admin/users` - Get all users
- `GET /api/admin/bookings` - Get all bookings
- `GET /api/admin/stats` - Get system statistics

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

⭐ **Star this repo** if you find it helpful!