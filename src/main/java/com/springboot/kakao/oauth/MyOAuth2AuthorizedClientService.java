package com.springboot.kakao.oauth;

import com.springboot.kakao.domain.Member;
import com.springboot.kakao.domain.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedHashMap;
import java.util.Objects;

@Service
public class MyOAuth2AuthorizedClientService implements OAuth2AuthorizedClientService {

    private final MemberRepository memberRepository;

    public MyOAuth2AuthorizedClientService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void saveAuthorizedClient(OAuth2AuthorizedClient oAuth2AuthorizedClient, Authentication authentication) {
        String providerType = oAuth2AuthorizedClient.getClientRegistration().getRegistrationId();
        OAuth2AccessToken accessToken = oAuth2AuthorizedClient.getAccessToken();

        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        String id = String.valueOf(oauth2User.getAttributes().get("id"));
        LinkedHashMap profiles = ((LinkedHashMap) ((LinkedHashMap) Objects.requireNonNull(oauth2User.getAttribute("kakao_account"))).get("profile"));
        String profile_image_url = (String) profiles.get("profile_image_url");
        String name = (String) profiles.get("nickname");
        Member member = new Member(id, name, providerType, accessToken.getTokenValue());
        System.out.println(profile_image_url);
        memberRepository.save(member);
    }

    @Override
    public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String s, String s1) {
        throw new NotImplementedException();
    }

    @Override
    public void removeAuthorizedClient(String s, String s1) {
        throw new NotImplementedException();
    }

}
