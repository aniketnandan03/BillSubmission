package com.aniket.controller;

import java.io.IOException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aniket.dao.HealthCheck;
import com.aniket.transferObject.User;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/default")
public class WelcomeController {
	@Autowired
	private User user;
	
	@Autowired
	HealthCheck hlthChck;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> index(Map<String, Object> model) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		JSONObject outputJson = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		try {
			user = hlthChck.getUser();
			JSONObject userJson = new JSONObject(mapper.writeValueAsString(user));
			outputJson.put("status", "success");
			outputJson.put("user", userJson);
		} catch (JSONException e) {
			System.out.println("Error while creating output JSON from index method in welcomeController");
			e.printStackTrace();
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(outputJson.toString(), headers, HttpStatus.OK);
	}

}
