# PT_Diagnoser
PT역량강화 자가진단을 진행하기위한 서비스 작성



## Environment
1. Tomcat			    8.0.36
2. Spring-Boot        1.3.6.RELEASE
3. Tibero                  5



## How to set Environment

* spring-boot

  * 1.3.6.RELEASE
  * Edit spring-boot version to 1.3.6.RELEASE in pom.xml



* Tomcat 

  * 8.0.36
  * Download tomcat 8.0.36
  * Add Modules in pom.xml

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-tomcat</artifactId>
      <scope>provided</scope>
  </dependency>
  ```

  * Extend SpringBootServletInitializer And Override for employ

  ```java
  @SpringBootApplication
  public class DemoApplication extends SpringBootServletInitializer {
      @Override
      protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
          return builder.sources(DemoApplication.class);
      }
  
      public static void main(String[] args) {
          SpringApplication.run(DemoApplication.class, args);
      }
  }
  ```



* Tibero

  * 5
  * Download tibero5-jdbc.jar
  * Add library in tomcat ${tomcatPath}/lib/
  * If you want to test in local environment, Add this dependency in pom.xml

  ```xml
  <dependency>
	    <groupId>tibero5-jdbc</groupId>
	    <artifactId>tibero5-jdbc</artifactId>
	    <version>5.0.0</version>
	    <scope>system</scope>
	    <systemPath>${project.basedir}/tibero5-jdbc.jar</systemPath>
	</dependency>
  ```

  * Add DataSource Config in application.properties
  * You have to fill varibles starting with ${}

  ```properties
  spring.datasource.url=jdbc:tibero:thin:@${hostIp}:${hostPort}:tibero
  spring.datasource.driver-class-name=com.tmax.tibero.jdbc.TbDriver
  spring.datasource.username=${username}
  spring.datasource.password=${password}

  spring.jpa.database-platform=org.hibernate.dialect.Oracle9Dialect
  ```

  * build datasource using configuration java file

  ```java
  @Configuration
  public class DataSourceConfig {

      @Bean
      @ConfigurationProperties(prefix = "spring.datasource")
      //위에 application.properties에 앞부분
      public DataSource dataSource() {
          return DataSourceBuilder.create().build();
      }
  }
  ```



## How to Execute

* In local environment
  * This environment run spring-boot using embeded tomcat modules

  ```powershell
  > ./mvnw spring-boot:run
  ```



* In employ environment
  * This environment make WAR file for tomcat in ${projectDir}/target directory

  ```powershell
  > mvn clean -f "${projectDir}/pom.xml"
  > mvn install -f "${projectDir}/pom.xml"
  ```

