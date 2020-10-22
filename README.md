# PT_Diagnoser
PT역량강화 자가진단을 진행하기위한 서비스 작성

## Environment
1. Tomcat			8.0.36
2. Spring-Boot    1.3.6.RELEASE
3. Tibero              5



## How to set Environment

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

  

  

* spring-boot

  * 1.3.6.RELEASE
  * In pom.xml



## How to Execute

* In local environment
  * This environment run spring-boot using embeded tomcat modules

```powershell
> ./mvnw spring-boot:run
```



* In employ environment
  * This environment make WAR files for tomcat in ${projectDir}/target directory

```powershell
> mvn clean -f "${projectDir}/pom.xml"
> mvn install -f "${projectDir}/pom.xml"
```

