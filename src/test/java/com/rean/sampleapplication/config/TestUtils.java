package com.rean.sampleapplication.config;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class TestUtils {

  public static RequestBuilder getPostRequestBuilder(String url, String requestAsJson) {
    return MockMvcRequestBuilders
        .post(url)
        .accept(MediaType.APPLICATION_JSON)
        .content(requestAsJson)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("utf-8");
  }

  public static RequestBuilder getGetRequestBuilder(String url) {
    return MockMvcRequestBuilders
        .get(url)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("utf-8");
  }
  
  public static RequestBuilder getPutRequestBuilder(String url, String requestAsJson) {
    return MockMvcRequestBuilders
        .put(url)
        .accept(MediaType.APPLICATION_JSON)
        .content(requestAsJson)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("utf-8");
  }
  
  public static RequestBuilder getDeleteRequestBuilder(String url) {
	    return MockMvcRequestBuilders
	        .delete(url)
	        //.param("id", id)
	        .accept(MediaType.APPLICATION_JSON)
	        .contentType(MediaType.APPLICATION_JSON)
	        .characterEncoding("utf-8");
	  }
}