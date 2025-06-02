# Secret Friend Backend API

This is a backend API for a Secret Friend application, built with **Spring Boot**. It provides endpoints for user management, group organization, participant wish lists, and gift-related functionalities, all with secure authentication and database persistence.

---

### ✨ Features

The application offers a robust set of features, including:

* 👤 **User Management:** Secure user registration with password hashing, and full CRUD operations (Create, Read, Update, Delete) for user accounts.
* 🖼️ **Avatar Management:** Users can upload, update, and delete their profile avatars.
* 👥 **Group Management:** Create, delete, and update Secret Friend groups.
* 🤝 **Participant Management:** A separate service handles adding and listing participants within a group.
* 🎁 **Wish List Management:** Users can add and manage gifts on their wish lists within a specific group.
* 💌 **Letter Service:** Allows participants to register and retrieve letters (messages) associated with a specific group participation.
* 🔐 **Authenticated Endpoints:** The API is designed with authentication in mind, using **Spring Security** and `PasswordEncoder` to secure user-related and group-related actions.

---

### 📋 Prerequisites

Before you begin, ensure you have the following installed:

* ☕ **Java Development Kit (JDK) 17+**
* 📦 **Apache Maven 3.6+**
* 🐘 **PostgreSQL** (Database Server)

---

### ⚙️ Environment Variables & Configuration

This application uses environment variables for configuration, which is a best practice for security and flexibility. The values are read from the system environment at startup.

* `spring.datasource.url=${DB_URL}`
* `spring.datasource.username=${DB_USERNAME}`
* `spring.datasource.password=${DB_PASSWORD}`
* `server.port=${DB_PORT}`
* `api.security.token.secret=${JWT_SECRET}`
* `file.upload-dir=${PATH_FILES}`

-----

### 🚀 API Endpoints

Here are some of the main API endpoints provided by the services:

  * **User Service**

      * `POST /api/users/create`: Register a new user.
      * `GET /api/users/all`: Retrieve all users.
      * `GET /api/users/{id}`: Retrieve a user by their ID.
      * `PUT /api/users/update/{id}`: Update user information.
      * `DELETE /api/users/delete/{id}`: Delete a user.
      * `POST /api/users/avatar/{id}`: Upload or update a user's avatar.
      * `DELETE /api/users/avatar/{id}`: Delete a user's avatar.

  * **Group Service**

      * `POST /api/groups/register`: Register a new group.
      * `GET /api/groups/{id}`: Retrieve a group by its ID.
      * `GET /api/groups/all`: Retrieve all groups.
      * `PUT /api/groups/update/{id}`: Update group information.
      * `DELETE /api/groups/delete/{id}`: Delete a group.

  * **Participant Service**

      * `POST /api/participants/join`: Join a user to a group.
      * `GET /api/participants/{groupId}/all`: Retrieve all participants for a specific group.
      * `GET /api/participants/{userId}/groups`: Retrieve all groups a user is participating in.

  * **Wish List Service**

      * `POST /api/wishlists/create`: Create a new wish list entry (add a gift to a list).
      * `GET /api/wishlists/{id}`: Retrieve a wish list entry by ID.

  * **Letter Service**

      * `POST /api/letters/register`: Register a new letter.
      * `GET /api/letters/{userGroupId}`: Retrieve a letter by the user-group participant ID.

-----


### ⬇️ Installation & Run

1.  **Clone the repository:**

    ```bash
    git clone [https://github.com/DaviCalo/secret_friend_backend.git](https://github.com/DaviCalo/secret_friend_backend.git)
    cd secret_friend_backend
    ```

2.  **Build the project:**

    ```bash
    mvn clean package
    ```

3.  **Run the application:**

    ```bash
    java -jar target/secret-friend-backend-0.0.1-SNAPSHOT.jar
    ```
