# Weather Forecaster

The Weather Forecaster Application provides users with the ability to search for weather forecasts by city. The application is built using Angular for the frontend and Spring Boot for the backend, utilizing [OpenWeather APIs](https://openweathermap.org/api) to fetch weather data.

For more details around functionalities, requirements, installation and usage please consider this [official documentation](https://github.com/PrabalSrivastava/WeatherForecaster/blob/main/README.md) and [Swagger UI](http://localhost:8080/swagger-ui/index.html).

## Design & Architecture
The application follows a microservices architecture with a clear separation between the frontend and backend components. The frontend is responsible for user interaction and display, while the backend handles data processing and API requests.

### Design Patterns
- **Strategy Pattern**: Used for implementing various recommendation strategies based on different weather conditions.
- **Chaining of Responsibility Pattern**: Employed for processing recommendations, allowing multiple handlers to process the request in a chain. Easy to add more condition by adding a recommendation class.
- **Adapter Pattern**: Utilized to convert data models from the weather API into application-specific models, ensuring compatibility and flexibility.

### Architectural Principles

1. **SOLID Principles**:
    - **S**ingle Responsibility: Each class in the application has a single responsibility, promoting easier maintenance and testing.
    - **O**pen/Closed: The system is designed to be open for extension but closed for modification, allowing new features without altering existing code.
    - **L**iskov Substitution: Subtypes can be substituted for their base types without affecting the correctness of the program.
    - **I**nterface Segregation: Interfaces are specific to clients, preventing them from depending on methods they do not use.
    - **D**ependency Inversion: High-level modules do not depend on low-level modules but on abstractions, facilitating easier changes.

2. **12-Factor App Principles**:
    - **Codebase**: A single codebase tracked in version control, with multiple deploys. With each build, the deployment is being done to docker.
    - **Dependencies**: Explicitly declared and isolated dependencies which comes along with the fundamental principles of Spring Boot and Angular.
    - **Config**: Configuration is stored in environment variables, enabling easy deployment across environments.
    - **Backing Services**: Treated as attached resources, allowing flexibility in changing services.
    - **Build, Release, Run**: Separation of the build, release, and run stages to ensure predictable deployments using Jenkins.
    - **Processes**: The application runs as one or more stateless processes.
    - **Port Binding**: The backend exposes a service via port binding, while the frontend can be served on any static server.
    - **Concurrency**: Scaling the application by running multiple instances as needed.
    - **Disposability**: Fast startup and graceful shutdown processes ensure reliable deployments.
    - **Dev/Prod Parity**: Keep development and production as similar as possible.
    - **Logs**: Treat logs as event streams, enabling log aggregation and analysis.
    - **Admin Processes**: Run administrative or management tasks as one-off processes.

### Performance Optimization
- Caching is implemented to reduce the number of API calls to the weather service, improving response times and reducing load.
- Lazy loading is used in the frontend to enhance performance by loading only necessary components.

### Security Aspects
- Sensitive information, such as API keys, is stored in environment variables and is not hardcoded, preventing exposure.
- Implemented HTTPS for secure data transmission.
- Input validation and sanitization are performed to prevent common security vulnerabilities, such as SQL injection and XSS attacks.

### Production Readiness
- The application is packaged with build tools (Maven for the backend, Angular CLI for the frontend) for consistent builds.
- Configurations for production environments are separated from development settings.
- Automated testing is incorporated to ensure code quality and functionality before deployment on Docker using Jenkins.

### Testing Strategies
- **TDD (Test-Driven Development)**: Tests are written before code, ensuring that all functionality is covered by unit tests.
- **BDD (Behavior-Driven Development)**: User stories and behaviors are defined and tested, ensuring that the application meets user requirements.
- Quality assurance practices, such as code reviews and static code analysis, are implemented to maintain code quality.

### Handling Sensitive Information
- API keys and other sensitive configurations are stored securely using environment variables and are accessed through secure configuration methods in both the frontend and backend.
- Consider implementing an encryption mechanism for additional security, especially for critical information.

## Implementation Approach

1. **Frontend**:
    - Built using Angular, incorporating Bootstrap for styling.
    - Forms are used to collect user input, and the results are displayed using components.
    - HTTPClient is used for making API calls to the backend.

2. **Backend**:
    - Developed using Spring Boot, exposing RESTful APIs to provide weather data.
    - Error handling is implemented to manage invalid city names or other issues.
    - Data models are designed to handle JSON responses from the weather API.

### Sequence Diagram
A sequence diagram illustrating the interaction between the user, frontend, and backend can be created using draw.io. Here’s a sample layout for your diagram:
- **User** initiates a weather search.
- **Frontend** sends a request to the **Backend**.
- **Backend** retrieves weather data and sends it back to the **Frontend**.
- **Frontend** displays the data to the **User**.

Detailed [Sequence Diagram](https://drive.google.com/file/d/1S_hCBInHP0wYHSh0VeomUaD56mak2mO9/view?usp=sharing) and [Class Diagram](https://drive.google.com/file/d/1ntXGU0e-SHI3LPMHNXweeOmc4qBoRn07/view?usp=sharing) are here.

## Sub Modules Organization

- **src**: Backend contribution for the application
- **frontend**: Frontend contribution for the application
- **Jenkinsfile**: CI/CD files and configurations responsible to generate and push docker image.
- **Dockerfile**: Docker file for image creation logic
- **pom.xml**: For project overview

## Development Setup

### Build

Weather Forecaster is Java based Spring Boot application that uses Maven and Angular. 

```shell script
git clone git@github.com:PrabalSrivastava/WeatherForecaster.git
cd WeatherForecaster
```
Add file _`src/main/resources/application-dev.properties`_:

```shell script
server.port=8080
logging.level.org.springframework=DEBUG
server.error.whitelabel.enabled=false
server.error.path=/error

#date handling
date.format.input=yyyy-MM-dd HH:mm:ss
date.format.output=MMMM d, yyyy

#open weather api
openweather.api.key=<your-api-key-here>
openweather.api.baseUrl=https://api.openweathermap.org/data/2.5/forecast
```

Now, this can be built by simply performing:

```shell script
mvn clean package
```

This command will build all the modules along with running both frontend and backend tests and generate the correspondent artifacts: _`.jar`_ file for the contributions. After that, it automatically push the _`.jar`_ file to docker after creating an image.

```shell script
docker run -e PROFILE=dev -p 8080:8080 prabalsrivastava/weatherforecaster:0.0.1-SNAPSHOT
```
This will run the application (frontend & backend) on  http://localhost:8080/

### Frontend Contributions

It is possible to isolate this part of the build by running the following command:

```shell script
cd frontend
npm install
npm run serve
```

This will run frontend on  http://localhost:4200/.

## Test

In a similar way to what was written above about the building process, it is possible to run tests against each one of the modules.

### Backend Contribution

#### Unit Tests

```shell script
mvn test
```
Running this command will run both frontend and backend tests.

### Frontend Contribution

#### Unit Tests

```shell script
npm run test
```
Running this command will run only frontend tests.


## Production Workflow

To run the application, use this command:

```shell script
docker pull prabalsrivastava/weatherforecaster:0.0.1-SNAPSHOT
docker run -e OPEN_WEATHER_API_KEY=<your-api-key-here> -e PROFILE=prod -p 8080:8080 prabalsrivastava/weatherforecaster:latest
```

This will run the application (frontend & backend) on  http://localhost:8080/

## CI/CD

Continuous Integration & Continuous Deployment(and Delivery) are an important part of the development process.

Weather Forecaster integrates [Jenkins pipelines](http://localhost:9090/job/WeatherForecaster/) for each reference branch, and also for each opened PR using [Jenkinsfile](https://github.com/PrabalSrivastava/WeatherForecaster/blob/main/Jenkinsfile).

The following features are available:

- Each PR build will compile the PR branch.
- Run Unit Tests and Frontend Tests separately.
- Publish Docker image upon successful build.

# About Weather Forecaster

This is a sample project in order to demonstrate the skills such as API Integration, System Design & Architecture, Design Pattern, Java, Spring Boot, Angular, Docker, Jenkins and Github.