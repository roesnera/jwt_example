package edu.generation.jfs_jwt_demo.controller;

import edu.generation.jfs_jwt_demo.controller.dto.LoginDto;
import edu.generation.jfs_jwt_demo.controller.dto.Token;
import edu.generation.jfs_jwt_demo.model.Course;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
// @Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CourseControllerTest {
    private static final String url = "http://localhost:8080";
    public static final String INEW_2438_OBJECTIVE =
            """
            A continuation of advanced Java programming techniques such as servlets and advanced graphical functions
            Java AWT/Swing, Java FX and Spring Boot
            Java Collections Framework and Generics
            Modern Java features
            Functional programming concepts in Java
            """;
    public static final String INEW_2438 = "INEW-2438";
    private static String accessToken;
    private static final String AUTHENTICATION_URL = url + "/auth";
    private static final String FETCH_COURSE_URL = url + "/api/courses";
    private static final String SAVE_COURSE_URL = url + "/api/course/insert";
    private static final String DELETE_COURSE_URL = url + "/api/course/delete";


    @Test @Order(1)
    void testToken() {
        TestRestTemplate restTemplate= new TestRestTemplate();
        ResponseEntity<Token> token = restTemplate.postForEntity(AUTHENTICATION_URL,
                new LoginDto("user@mail.com", "password"), Token.class);
        assertNotNull(token);
        assertEquals(Response.SC_OK, token.getStatusCodeValue());
        accessToken = String.valueOf(token.getBody());
        System.out.printf("Token=%s\n", accessToken);
    }

    @Test @Order(2)
    void testCourses() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpEntity<String> jwtEntity = new HttpEntity<>(getHttpHeaders());
        ResponseEntity<Course[]> response =
                restTemplate.exchange(FETCH_COURSE_URL, HttpMethod.GET, jwtEntity, Course[].class);
        assertNotNull(response);
        assertEquals(Response.SC_OK, response.getStatusCodeValue());
        System.out.println(Arrays.toString(response.getBody()));
    }

    @Test @Order(3)
    void testSave() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        Course newCourse = new Course(INEW_2438, "Advanced Java", INEW_2438_OBJECTIVE);
        System.out.println(newCourse);
        HttpEntity<Course> jwtEntity = new HttpEntity<>(newCourse, getHttpHeaders());
        ResponseEntity<Course> response =
                restTemplate.exchange(SAVE_COURSE_URL, HttpMethod.POST, jwtEntity, Course.class);
        assertNotNull(response);
        assertEquals(Response.SC_OK, response.getStatusCodeValue());
        System.out.println(response.getBody());
    }

    @Test
    @Order(4)
    void testSave2() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        Course newCourse = new Course(INEW_2438, "Advanced Java", INEW_2438_OBJECTIVE);
        System.out.println(newCourse);
        HttpEntity<Course> jwtEntity = new HttpEntity<>(newCourse, getHttpHeaders());
        ResponseEntity<Course> response =
                restTemplate.exchange(SAVE_COURSE_URL, HttpMethod.POST, jwtEntity, Course.class);
        assertNotNull(response);
        assertEquals(Response.SC_CONFLICT, response.getStatusCodeValue());
    }

    @Test @Order(5)
    void testDelete() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpEntity<String> jwtEntity = new HttpEntity<>(INEW_2438, getHttpHeaders());
        ResponseEntity<String> response =
                restTemplate.exchange(DELETE_COURSE_URL, HttpMethod.POST, jwtEntity, String.class);
        assertNotNull(response);
        assertEquals(Response.SC_OK, response.getStatusCodeValue());
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
