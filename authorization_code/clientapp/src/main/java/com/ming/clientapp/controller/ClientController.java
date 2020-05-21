package com.ming.clientapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
public class ClientController {

  @Autowired RestTemplate restTemplate;

  @GetMapping("/index.html")
  public String index(String code, Model model) {
    if (null != code) {
      MultiValueMap<String, String> map = new LinkedMultiValueMap();
      map.add("code", code);
      map.add("client_id", "pwc");
      map.add("client_secret", "123456");
      map.add("redirect_uri", "http://localhost:8082/index.html");
      map.add("grant_type", "authorization_code");
      Map<String, String> res =
          restTemplate.postForObject("http://localhost:8080/oauth/token", map, Map.class);
      System.out.println("res" + res);

      HttpHeaders headers = new HttpHeaders();
      headers.add("Authorization", "Bearer" + res.get("access_token"));
      HttpEntity<?> httpEntity = new HttpEntity<>(headers);
      ResponseEntity<String> responseEntity =
          restTemplate.exchange(
              "http://localhost:8081/hello", HttpMethod.GET, httpEntity, String.class);
      model.addAttribute("msg", responseEntity.getBody());
    }
    return "index";
  }
}
