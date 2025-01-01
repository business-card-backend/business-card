package solverz.business_card.domain.common.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static org.springdoc.core.service.AbstractRequestService.getHeaders;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Pointcut("execution(* solverz.business_card.domain..controller..*(..))")
    public void controllerExecute() {
    }

    @Around("solverz.business_card.domain.common.logging.LoggingAspect.controllerExecute()")
    public Object requestLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
        long startAt = System.currentTimeMillis();
        Object returnValue = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());

        //proceedingJoinPoint의 proceed를 활용해 result를 받아온다.
        long endAt = System.currentTimeMillis();
        log.info("================================================NEW===============================================");
        log.info("====> Request: {} {} ({}ms)\n *Header = {}", request.getMethod(), request.getRequestURL(), endAt - startAt, getHeaders(request));
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            //	post 요청일 경우 body 로깅
            log.info("====> Body: {}", objectMapper.readTree(cachingRequest.getContentAsByteArray()));
        }
        if (returnValue != null) {
            log.info("====> Response: {}", returnValue);
        }
        log.info("================================================END===============================================");
        return returnValue;
    }

    private Map<String, Object> getHeaders(HttpServletRequest request) {
        Map<String, Object> headerMap = new HashMap<>();

        Enumeration<String> headerArray = request.getHeaderNames();
        while (headerArray.hasMoreElements()) {
            String headerName = headerArray.nextElement();
            headerMap.put(headerName, request.getHeader(headerName));
        }
        return headerMap;
    }
}

