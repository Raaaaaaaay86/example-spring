package com.example.plan.config;

import org.json.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle (HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			AccessDeniedException e) throws IOException, ServletException {
		var responseJSON = new JSONObject();
		responseJSON.put("timestamp", new Date().getTime());
		responseJSON.put("status", "403");
		responseJSON.put("message", "權限不足");
		httpServletResponse.setHeader("content-type", "application/json");
		httpServletResponse.setCharacterEncoding("UTF-8");
		httpServletResponse.getWriter().println(responseJSON.toString());
	}
}
