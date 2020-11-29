package com.example.sweater.domain.util;

import com.example.sweater.domain.User;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class MessageHelper {
    public static String getAuthorName(User user) {
        return user != null ? user.getUsername() : "<none>";
    }

    public static UriComponents getUrlAddition(String referer, RedirectAttributes redirectAttributes) {
        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();

        components.getQueryParams()
                .entrySet()
                .forEach(pair -> redirectAttributes.addAttribute(pair.getKey(), pair.getValue()));

        return components;
    }
}
