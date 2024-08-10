USER-APP 🚀
---
user-app is a robust and scalable application built using Spring Boot, Spring Security, and Spring Data JPA. 
This project adheres to the latest coding standards and best practices to ensure clean, maintainable, and efficient code.

Technologies Used 😍
---
1) Java 17
2) Spring Boot 3.0.0
3) Spring Security 6
4) Spring Data JPA 
5) Maven for dependency management and build automation
6) MySQL Database for development

Getting Started 😎
-
Prerequisites

1) Java 17
2) Maven or Gradle (depending on your build tool preference)
3) IDE such as IntelliJ IDEA, Eclipse, or VSCode

Installation 🙂
-
1. Clone the repository 
   ```
    git clone https://github.com/Mohammad-Husen-Mulla/user-app.git
   ```
   ```
    cd project-directory
   ```
2. Build the project
   ```
     mvn clean install
   ```
3. Create databse
   ```
   Database name: registration
   ```    
4. Run the application
   ```
     mvn spring-boot:run
   ```
The application will start on http://localhost:8080 by default.   

Configuration 🥲
-
The application configuration can be modified in the application.properties file located in src/main/resources/. 

You can configure:

- Database connection settings
- Security settings
- Application-specific properties

## Endpoints 🌐

### 1. Register User

- **URL:** `/api/v1/user/register`
- **Method:** `POST`
- **Description:** Registers a new user in the system.
- **Security:** This endpoint is publicly accessible. No authentication is required.
- **Request Body:** `ApplicationUserRequestDto`
  - **Example JSON:**
    ```json
    {
      "name": "John Doe",
      "email": "johndoe@example.com",
      "password": "Password1!",
      "role": "USER"
    }
    ```
  - **Fields:**
    - `name` (String) - Required: The name of the user. Should be between 2-30 characters and may include letters and spaces.
    - `email` (String) - Required: The email address of the user. Must be a valid email format.
    - `password` (String) - Required: The password for the user account. Should be between 5-20 characters.
    - `role` (Roles) - Required: The role assigned to the user. It must be a valid role as defined in the `Roles` enum.
  - **Validation:**
    - `name` must be between 2 and 30 characters, and can only contain letters and spaces.
    - `email` must be a valid email format.
    - `password` must be between 5 and 20 characters.
    - `role` cannot be null.
- **Response:**
  - **Status Code:** `201 Created`
  - **Body:** `"User registered successfully!"`
  - **Location Header:** The URI of the newly created user resource.
  - **Example Response:**
    ```json
    {
      "status": "Created",
      "message": "User registered successfully!",
      "location": "/api/v1/user/get/1"
    }
    ```

### 2. Get User

- **URL:** `/api/v1/user/get/{id}`
- **Method:** `GET`
- **Description:** Retrieves the details of a user by their ID.
- **Security:** This endpoint requires authentication. Only authenticated users can access this endpoint.
- **Path Variable:** `id` (Long) - The ID of the user to retrieve.
- **Response:**
  - **Status Code:** `200 OK`
  - **Body:** `ApplicationUserResponseDto`
  - **Example JSON:**
    ```json
    {
      "name": "John Doe",
      "email": "johndoe@example.com",
      "role": "USER"
    }
    ```
  - **Example Response:**
    ```json
    {
      "name": "John Doe",
      "email": "johndoe@example.com",
      "role": "USER"
    }
    ```

### 3. Update User

- **URL:** `/api/v1/user/update/{id}`
- **Method:** `PUT`
- **Description:** Updates the details of an existing user by their ID.
- **Security:** This endpoint requires authentication. Only users with the role `USER` can update their own profile.
- **Path Variable:** `id` (Long) - The ID of the user to update.
- **Request Body:** `ApplicationUserUpdateDto`
  - **Example JSON:**
    ```json
    {
      "name": "John Smith",
      "email": "johnsmith@example.com"
    }
    ```
  - **Fields:**
    - `name` (String) - The new name for the user.
    - `email` (String) - The new email address for the user.
- **Response:**
  - **Status Code:** `200 OK`
  - **Body:** `"User details updated successfully!"`
  - **Example Response:**
    ```json
    {
      "status": "OK",
      "message": "User details updated successfully!"
    }
    ```

### 4. Delete User

- **URL:** `/api/v1/user/delete/{id}`
- **Method:** `DELETE`
- **Description:** Deletes a user by their ID.
- **Security:** This endpoint requires authentication. Both users and admins can delete user profiles.
- **Path Variable:** `id` (Long) - The ID of the user to delete.
- **Response:**
  - **Status Code:** `200 OK`
  - **Body:** `"Deleted successfully"`
  - **Example Response:**
    ```json
    {
      "status": "OK",
      "message": "Deleted successfully"
    }
    ```

### 5. Update Password

- **URL:** `/api/v1/user/update/password`
- **Method:** `PUT`
- **Description:** Updates the password of a user.
- **Security:** This endpoint requires authentication. Only users with the role `USER` can change their own password.
- **Request Body:** `PasswordUpdateDto`
  - **Example JSON:**
    ```json
    {
      "oldPassword": "oldpassword123",
      "newPassword": "NewPass1!",
      "email": "johndoe@example.com"
    }
    ```
  - **Fields:**
    - `oldPassword` (String) - Required: The current password of the user.
    - `newPassword` (String) - Required: The new password for the user. Must include at least one lowercase letter, one uppercase letter, one special character, and one number.
    - `email` (String) - Required: The email address associated with the user account. Must be a valid email format.
  - **Validation:**
    - `oldPassword` cannot be empty.
    - `newPassword` must match the specified pattern: at least one lowercase letter, one uppercase letter, one special character, and one number.
    - `email` must be a valid email format.
- **Response:**
  - **Status Code:** `200 OK`
  - **Body:** `"Password updated successfully"`
  - **Example Response:**
    ```json
    {
      "status": "OK",
      "message": "Password updated successfully"
    }
    ```

## Security Configuration 🔒

- **CSRF:** Disabled
- **CORS:** Disabled
- **Authorization:**
  - **Public Access:**
    - `/api/v1/user/register` - No authentication required.
  - **Authenticated Access:**
    - `/api/v1/user/get/{id}` - Requires authentication for all users.
    - `/api/v1/user/update/{id}` - Requires authentication with the role `USER` (only users can update their own profile).
    - `/api/v1/user/delete/{id}` - Requires authentication with the role `ADMIN` or `USER` (both admins and users can delete).
    - `/api/v1/user/update/password` - Requires authentication with the role `USER` (only users can change their password).

## Error Handling 🪲

Ensure appropriate error handling is in place for scenarios such as:

- Invalid input data
- User not found
- Unauthorized access

Respond with appropriate HTTP status codes and error messages to guide users on what went wrong.

## Notes 📝

- Ensure that the request bodies conform to the expected DTO structures and validations.
- Secure endpoints with proper authentication and authorization mechanisms where necessary.
- For further enhancements, consider adding validation annotations and handling potential security issues like password encryption.
