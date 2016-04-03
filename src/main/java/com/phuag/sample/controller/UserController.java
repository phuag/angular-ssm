package com.phuag.sample.controller;

import com.phuag.sample.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = Constants.URI_API)
public class UserController {
    
    /**
     * Get the authenticated user info.
     * 
     * @param principal
     * @return 
     */
    @RequestMapping("/me")
    public ResponseEntity<Map<String, Object>> user(Principal principal) {
        
        Map<String, Object> map = new HashMap<>();
        map.put("username", principal.getName());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
   
}
