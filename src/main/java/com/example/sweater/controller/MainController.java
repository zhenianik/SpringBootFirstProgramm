package com.example.sweater.controller;

import com.example.sweater.domain.User;
import com.example.sweater.domain.dto.MessageDto;
import com.example.sweater.repos.MessageRepo;
import com.example.sweater.service.FileService;
import com.example.sweater.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    MessageService messageService;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "")
                               String filter, Model model,
                       @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                       @AuthenticationPrincipal User user
                       ) {

        Page<MessageDto> page = messageService.MessageList(pageable, filter, user);

        model.addAttribute("page", page);
        model.addAttribute("url", "/main");
        model.addAttribute("filter", filter);

        return "main";
    }

}