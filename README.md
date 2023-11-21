# Project Startup Guide 🚀🚀🚀

Welcome to the project setup guide. Follow these steps to get the project up and running.

## Cloning the Project

Start by cloning the project repository:

```bash
git clone git@github.com:NewPhoneWhoDis/thekey.git
```

## Running the application in a containerized environment with Docker

To run the application in a containerized environment using Docker, follow these steps:

1. **Navigate to the Backend Directory:**

   ```bash
   cd backend
   ```

2. **Use the Maven Wrapper:**
   If you do not have Maven installed locally, use the `mvnw` wrapper. On Unix-based systems, you might have to give the wrapper execution rights first. Run the following command to do so, or contact your system administrator for assistance:

   ```bash
   chmod +x mvnw
   ```

3. **Build the Backend Application:**

   - To build and run tests:
     ```bash
     ./mvnw package
     ```
   - To skip tests:
     ```bash
     ./mvnw package -DskipTests
     ```

4. **Return to the Project Root Directory:**

   ```bash
   cd ..
   ```

5. **Build Docker Images:**

   ```bash
   docker-compose build
   ```

6. **Start Docker Containers:**

   ```bash
   docker-compose up
   ```

7. **Access the Application:**
   - Frontend: Open [http://localhost:3000](http://localhost:3000) in your browser.
   - Backend: Available at [http://localhost:8080](http://localhost:8080).

## Setting Up a Local Development Environment

Depending on your preference, you can either build a JAR file and execute it via the command line, or run the app immediately without building a JAR.

### Building and Running a JAR File:

1. **Navigate to the Backend Directory:**

   ```bash
   cd backend
   ```

2. **Build the Backend Application:**

   ```bash
   ./mvnw package
   ```

3. **Navigate to the Target Directory and Run the JAR File:**
   ```bash
   cd target
   java -jar key-0.0.1-SNAPSHOT.jar
   ```

### Running the App Without Building a JAR:

1. **Navigate to the Backend Directory:**

   ```bash
   cd backend
   ```

2. **Run the Backend Application:**
   ```bash
   ./mvnw spring-boot:run
   ```

_Note: If using IntelliJ IDEA, simply open the project and click the green arrow to start._

### Setting Up the Frontend:

1. **Navigate to the Client Directory:**

   ```bash
   cd client
   ```

2. **Install Dependencies:**

   ```bash
   npm install --legacy-peer-deps
   ```

3. **Start the Development Server:**
   ```bash
   npm run dev
   ```
