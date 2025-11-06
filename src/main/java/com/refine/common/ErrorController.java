package com.refine.common;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.refine.common.code.service.CodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {
	public static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

	@Autowired
	protected CodeService codeService;
		
	/**
	 * Error Message Page
	 */
	@GetMapping(value="/error/errorPage")
	public String errorMessage(Map<String, Object> modelMap, HttpSession session) throws SQLException{
		logger.debug(">>>>>>>>>>>>>>>> error");		
		return "common/error";
	}

	/**
	 * Error Message Popup
	 */
	@GetMapping(value="/error/errorPop")
	public String errorPop(Map<String, Object> modelMap, HttpSession session) throws SQLException{
		logger.debug(">>>>>>>>>>>>>>>> error");		
		return "common/errorPop";
	}
	
    @GetMapping(value="/error/forbidden")
    public String forbidden(Map<String, Object> modelMap, HttpServletRequest httpServletRequest, HttpSession session) throws SQLException{
        logger.debug(">>>>>>>>>>>>>>>> forbidden");
        logger.info("attemptUrl: " + session.getAttribute("attemptUrl"));
        logger.info("httpServletRequest.getRequestURL(): " + httpServletRequest.getRequestURL().toString());
        
        modelMap.put("responseText", httpServletRequest.getAttribute("responseText"));
        return "common/forbidden";
    }
    
    
    @PostMapping(value="/error/forbiddenP")
    public ModelAndView forbiddenPost(HttpServletRequest httpServletRequest) throws SQLException{

        ModelAndView model = new ModelAndView();
        model.addObject("responseText", httpServletRequest.getParameter("responseText"));
        model.setViewName("common/forbidden");
        
        return model;
    }


	@RequestMapping(value = "/error/auth")
	public String noauth(Map<String, Object> modelMap,
						 HttpServletRequest request) throws SQLException {

		if (!request.getRequestURL().toString().contains("localhost")) {
			Map<String, Object> map = codeService.getMenuInfo("PORTAL");
			String portalUrl = (String) map.get("url_addr");
			modelMap.put("portal", portalUrl);
		}

		return "common/noauth";
	}
	
}
