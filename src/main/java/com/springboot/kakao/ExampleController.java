package com.springboot.kakao;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my")
public class ExampleController {

    @GetMapping
    public String getMyAuthenticationFromSession(@AuthenticationPrincipal OAuth2User oAuth2User){
        return oAuth2User.toString();
    }
}
