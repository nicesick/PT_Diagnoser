package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class Interceptor extends HandlerInterceptorAdapter {

	//private static final Logger logger = Logger.getLogger(HandlerInterceptor.class);

	/*
	 * @Override public void postHandle(HttpServletRequest request,
	 * HttpServletResponse response, Object handler, ModelAndView modelAndView )
	 * throws Exception { System.out.println("postHandle interceptor start");
	 * HttpSession httpSession = request.getSession(); ModelMap modelMap =
	 * modelAndView.getModelMap(); Object member = modelMap.get("login");
	 * System.out.println(member); if(member != null ) {
	 * System.out.println("new Login success "); httpSession.setAttribute("USER",
	 * member); response.sendRedirect("/"); } }
	 */
	
	@SuppressWarnings("unused")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("PreHandle interceptor start "); 
		HttpSession session = request.getSession(); 
		
		System.out.println(session.getAttribute("user_id"));
		System.out.println(session.getAttribute("user_name"));
		System.out.println(session.getAttribute("user_enum"));
		System.out.println(session.getAttribute("user_email"));

		if(session.getAttribute("user_id") == null ) {
			response.sendRedirect("/login");
		}
		return true;
	}
}
