package example.sample.project.session;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SessionManager {
	
	public static final String SESSION_COOKIE_NAME = "tempSessionId";
	private Map<String,Object> sessionMap = new HashMap<String,Object>();
	
	public void create(Object object, HttpServletResponse resp) {
		String sessionId = UUID.randomUUID().toString();
		sessionMap.put(sessionId, object);
		
		Cookie cookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
		resp.addCookie(cookie);
	}
	
	public void remove(HttpServletRequest req) {
		
		Cookie cookie = findCookie(req, SESSION_COOKIE_NAME);
		if(cookie != null) {
			sessionMap.remove(cookie.getValue());
		}
//		if(req.getCookies() != null) {
//			for(Cookie cookie : req.getCookies()) {
//				if(cookie.getName().equals("SESSION_COOKIE_NAME")) {
//					//쿠키의 키값이랑 일치하면 일치하는 그 키값에 대한 밸류값을 지운다
//					sessionMap.remove(cookie.getValue());
//					return;
//				}
//			}
//		} 중복돼서 따로 뺌
	}
	public Object getSessionObject(HttpServletRequest req) {
		//req -> Cookie -> tempSessionId(name) -> value(UUID)
		//value(UUID)    sessionMap<value(UUID), Member)
		Cookie cookie = findCookie(req, SESSION_COOKIE_NAME);
		if(cookie != null) {
			return sessionMap.get(cookie.getValue());
		}
		
		return null;
	
//		if(req.getCookies() != null) {
//			for(Cookie cookie : req.getCookies()) {
//				if(cookie.getName().equals("SESSION_COOKIE_NAME")) {
//					return sessionMap.get(cookie.getValue());
//				}
//			}
//		}
//		return null;
	}
	
	public Cookie findCookie(HttpServletRequest req, String cookieName) {
		if(req.getCookies() != null) {
			for(Cookie cookie : req.getCookies()) {
				if(cookie.getName().equals(cookieName)) {
					return cookie;
				}
			}
		}
		return null;
	}
}
