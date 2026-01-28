package com.refine.common;

import com.refine.util.RefineUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/error")
public class RfErrorController implements ErrorController {
    public static final Logger logger = LoggerFactory.getLogger(RfErrorController.class);

    private final ErrorAttributes errorAttributes;

    @Autowired
    public RfErrorController(ErrorAttributes errorAttributes) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping
    public ModelAndView error(HttpServletRequest aRequest){
        Map<String, Object> body = getErrorAttributes(aRequest, getTraceParameter(aRequest));
        logger.error(RefineUtil.getObjectToGson(body));
        Object status = aRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String view = "/main";
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.BAD_REQUEST.value()) { //400
                view =  "common/error/400";
            } else if(statusCode == HttpStatus.FORBIDDEN.value()) { //403
                view =  "common/error/403";
            } else if(statusCode == HttpStatus.NOT_FOUND.value()) { //404
                view =  "common/error";
            }  else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) { //500
                view =  "common/error/500";
            }
        }

        ModelAndView mav = new ModelAndView(view);
        return mav;
    }

    @Override
    public String getErrorPath() {
        return "/main";
    }

    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        if (parameter == null) {
            return false;
        }
        return !"false".equals(parameter.toLowerCase());
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest aRequest, boolean includeStackTrace) {
        ServletWebRequest requestAttributes = new ServletWebRequest(aRequest);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }
}
