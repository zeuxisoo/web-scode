package im.ggd.scode.security.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import im.ggd.scode.dto.response.ErrorResponse;

public class ResetAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException authException
    ) throws IOException, ServletException {
        ErrorResponse errorResponse = new ErrorResponse(false, "Entry point authentication failed");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        response.getWriter()
                .write(
                    new ObjectMapper().writeValueAsString(errorResponse)
                );
    }

}
