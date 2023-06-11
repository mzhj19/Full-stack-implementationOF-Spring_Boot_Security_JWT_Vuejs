package com.ZahidHasanJamil.SBproject.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ZahidHasanJamil.SBproject.model.response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/demo")
@RequiredArgsConstructor
public class DemoController {
  
  @GetMapping
  public ResponseEntity<String> sayHello() {
    return ResponseEntity.ok("Hello, World!");
  }
  @GetMapping("/test")
  public String test()  {
    return "test response";
  }
  @GetMapping("/mytest")
  public String myTest()  {
    return "200(OK) response from mytest endpoint";
  }

  @GetMapping("/fakeapi/1")
  public  response getOneResponse() {
    RestTemplate restTemplate = new RestTemplate();

    // Make the API call
    ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/todos/1", String.class);

    // Retrieve the response body
    String responseBody = responseEntity.getBody();
    response res = new response();

    // Print the response

    ObjectMapper objectMapper = new ObjectMapper();

    try {
      // Convert JSON to a plain object
      res = objectMapper.readValue(responseBody, response.class);

      // Print the converted object
      System.out.println(res);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return res;

  }

  @GetMapping("/fakeapi")
  public ResponseEntity<?> getResponse() {
    RestTemplate restTemplate = new RestTemplate();

    // Make the API call
    ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/todos", String.class);

    // Retrieve the response body
    String responseBody = responseEntity.getBody();
    List<response> responseList = new ArrayList<>();

    ObjectMapper objectMapper = new ObjectMapper();

    try {
      // Convert JSON to a list of objects
      responseList = objectMapper.readValue(responseBody, new TypeReference<List<response>>() {});

      // Check if the responseList is empty
      if (responseList.isEmpty()) {
        // Return an error response indicating that the responseList is empty
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data found.");
      }

      // Return a successful response with the responseList
      return ResponseEntity.ok(responseList);
    } catch (Exception e) {
      e.printStackTrace();
      // Handle any exception that occurred during deserialization
      // You can throw a custom exception or return an error response if needed
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
    }
  }


}
