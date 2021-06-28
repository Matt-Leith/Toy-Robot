## Caterpillar Coding Challenge: Toy Robot

### Technical Details
Java 11, SpringBoot 2.5.2, Gradle with Springfox Swagger.

### How to Edit
- Open the gradle file with IntelliJ, OR
- Run gradlew idea and open the generated .ipr file.

### How to Build
- gradlew build

### How to Run
- gradlew bootRun OR
- Open in IntelliJ and run the main application.

### Local Swagger address
- http://localhost:8888/swagger-ui/#/

### Implementation Details

- The createRobot POST endpoint provides the functionality to "Place" the robot.
- The moveRobot PATCH endpoint provides the functionality to "Move" the robot and alter the robot's heading.
- The getRobot GET endpoint provides the functionality to "Report" the robot's position.

### Further Additions and Improvements

- Persistence for created resources
- Delete robot functionality
- Separating create robot and place robot into distinct actions
- Create board (set bounds) functionality
- Integration tests

