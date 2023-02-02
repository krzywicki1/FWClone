package com.example.p2;

import com.ns.util.CC;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@SpringBootApplication
@Controller
@Slf4j
public class P2Application {

    public static void main(String[] args) {
        SpringApplication.run(P2Application.class, args);

        log.info("Application {} started.", CC.g("successfully"));
    }

    @RequestMapping("/403")
    public void force403(@RequestParam(required = false, defaultValue = "Access forbidden! (403)") String message) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access forbidden! (403)");
    }

    @RequestMapping("/logout")
    public void logout() { throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized access! (401)"); }

    @RequestMapping("/force/{status}")
    public void forceError(@PathVariable Integer status) throws IllegalArgumentException {
        throw new ResponseStatusException(HttpStatus.valueOf(status), "Forced status: " + status);
    }

    @RequestMapping({"/z/{view}", "/z/{path}/{view}"})
    public String zulMapper(@PathVariable String view, @PathVariable(required = false) String path)
    {
        path = path == null ? "" : path + "/";
        log.info("Mapping: '/z/{}{}' -> '/{}{}'", path, view, path, view + ".zul");
        return path  + view + ".zul";
    }

    @GetMapping("/unauthorized")
    protected void doUnauthorized(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized request.");
    }

    @GetMapping("/user/headers")
    protected void doGetHeaders(HttpServletRequest request, HttpServletResponse response) throws IOException {
        printHeaders(request, response, "User headers:");
    }

    @GetMapping("/admin/headers")
    protected void doGetAdminHeaders(HttpServletRequest request, HttpServletResponse response) throws IOException {
        printHeaders(request, response, "Admin Headers:");
    }

    private void printHeaders(HttpServletRequest request, HttpServletResponse response,
                              String heading) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(heading);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            out.println("<br/><b>" + headerName + "</b>: <em>" + request.getHeader(headerName) + "</em>");
        }

        out.println("<hr/>");

        String authHeader = request.getHeader("authorization");
        if (authHeader != null) { // "authorization: Basic dXNlcjpwYXNzd29yZA=="
            String encodedValue = authHeader.split(" ")[1];
            String decodedValue = new String(Base64.decodeBase64(encodedValue));
            out.println("Base64-encoded Authorization: <em>" + encodedValue + "</em><br/>");
            out.println("Base64-decoded Authorization: <em>" + decodedValue + "</em>");
        }
    }
}