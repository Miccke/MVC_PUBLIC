package com.zxpublic.controller;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signout")
public class SignOutController {
	
	@RequestMapping(value="/getPage")
    public String writeSubmitHtml(Reader reader, Writer writer, HttpSession session) throws IOException {
		session.invalidate();
		return "index";      
    }
}
