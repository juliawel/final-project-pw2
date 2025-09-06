# E-commerce Backend

This is a Spring Boot backend for an e-commerce application. It provides a RESTful API for managing products, categories, orders, and users.

## Technologies Used

*   Java 21
*   Spring Boot
*   Spring Data JPA
*   Spring Security
*   PostgreSQL
*   Maven
*   JWT for authentication
*   OpenAPI (Swagger) for API documentation

## How to Run

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/your-username/final-project-pw2-main.git
    ```
2.  **Navigate to the project directory:**
    ```bash
    cd final-project-pw2-main/demo
    ```
3.  **Configure the database:**
    *   Open `src/main/resources/application.properties` and update the following properties with your PostgreSQL database credentials:
        ```properties
        spring.datasource.url=jdbc:postgresql://localhost:5432/ecommercepw
        spring.datasource.username=your-username
        spring.datasource.password=your-password
        ```
4.  **Configure the JWT Secret:**
    *   In `src/main/resources/application.properties`, you can also configure the JWT secret:
        ```properties
        api.security.token.secret=your-jwt-secret
        ```
5.  **Run the application:**
    ```bash
    ./mvnw spring-boot:run
    ```
6.  **Access the API documentation:**
    *   Open your browser and go to `http://localhost:8080/swagger-ui.html`

## API Endpoints

The API is documented using OpenAPI (Swagger). You can access the interactive API documentation at `http://localhost:8080/swagger-ui.html`.

Here are some example endpoints for managing products:

*   `GET /products`: Get all products
*   `GET /products/{id}`: Get a product by ID
*   `POST /products`: Create a new product
*   `PUT /products/{id}`: Update a product
*   `DELETE /products/{id}`: Delete a product

The API also includes endpoints for managing categories, orders, and users.