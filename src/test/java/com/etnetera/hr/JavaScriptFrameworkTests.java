package com.etnetera.hr;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import java.net.URI;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.data.HypeLevel;

/**
 * Class used for Spring Boot/MVC based tests.
 * 
 * @author Etnetera
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class JavaScriptFrameworkTests {

  @LocalServerPort
  private int port;

  private Long recordId;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void deleteAllFrameworks() throws Exception {
    this.restTemplate.delete("http://localhost:" + port + "/frameworks/all", String.class);
  }

  @Test
  public void insertNewFrameworkIntoDatabase() throws Exception {
    URI uri = new URI("http://localhost:" + port + "/framework");
    HttpHeaders headers = new HttpHeaders();

    JavaScriptFramework employee = new JavaScriptFramework("Angular", "8.2.3", null, HypeLevel.MAXIMUM);
    HttpEntity<JavaScriptFramework> request = new HttpEntity<>(employee, headers);
    ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

    JavaScriptFramework requestBody = new ObjectMapper().readValue(result.getBody(), JavaScriptFramework.class);
    this.recordId = requestBody.getId();
    assertThat(result.getStatusCodeValue()).isEqualTo(200);
  }

  @Test
  public void getListOfFrameworksFromDatabase() throws Exception {
    ResponseEntity<String> result = this.restTemplate.getForEntity("http://localhost:" + port + "/frameworks", String.class);
    assertThat(result.getStatusCodeValue()).isEqualTo(200);
    assertThat(result.getBody()).contains("Angular");
  }

  @Test
  public void shouldListOfFrameworksBasedOnNameQueryParameter() throws Exception {
    ResponseEntity<String> result = this.restTemplate.getForEntity("http://localhost:" + port + "/frameworks?name=Angular", String.class);
    assertThat(result.getStatusCodeValue()).isEqualTo(200);
    assertThat(result.getBody()).contains("Angular");
  }

  @Test
  public void shouldFailAsHypeLevelIsNotRecognized() throws Exception {
    ResponseEntity<String> result = this.restTemplate.getForEntity("http://localhost:" + port + "/frameworks?hypeLevel=MAXIMU", String.class);
    assertThat(result.getStatusCodeValue()).isEqualTo(400);
  }

  @Test
  public void shouldListFrameworkBasedOnNameAndHypeLevelQueryParameters() throws Exception {
    ResponseEntity<String> result = this.restTemplate.getForEntity("http://localhost:" + port + "/frameworks?hypeLevel=MAXIMUM&name=Ang", String.class);
    assertThat(result.getStatusCodeValue()).isEqualTo(200);
    assertThat(result.getBody()).contains("Angular");
  }

  @Test
  public void getFrameworkFromDatabaseById() throws Exception {
    ResponseEntity<String> res = this.restTemplate.getForEntity("http://localhost:" + port + "/frameworks", String.class);
    JavaScriptFramework[] response = new ObjectMapper().readValue(res.getBody(), JavaScriptFramework[].class); 
    ResponseEntity<String> result = this.restTemplate.getForEntity("http://localhost:" + port + "/framework/" + response[0].getId(), String.class);
    assertThat(result.getStatusCodeValue()).isEqualTo(200);
    assertThat(result.getBody()).contains("Angular");
  }
}
