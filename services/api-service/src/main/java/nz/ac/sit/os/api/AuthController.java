package nz.ac.sit.os.api;

import nz.ac.sit.os.infrastructure.security.CustomUserDetailsService;
import nz.ac.sit.os.infrastructure.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: os
 * @description: Authentication api
 * @author: Lucas Wang
 * @email: lucas.wang.1024@gmail.com
 * @date: 23/03/2026 00:24
 **/
@Controller
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/auth/login")
    public String loginPage() {
        return "redirect:/admin/login.jsp";
    }

    @PostMapping("/auth/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpServletResponse response,
                        RedirectAttributes redirectAttributes) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            String token = jwtUtil.generateToken(userDetails);
            Cookie cookie = new Cookie("JWT_TOKEN", token);
            cookie.setHttpOnly(true);   // Prevent JS to read token
            cookie.setPath("/");
            cookie.setMaxAge(60 * 15);  // 15 minutes
            response.addCookie(cookie);

            return "redirect:/admin/fetch-order";

        } catch (Exception e) {
            redirectAttributes.addAttribute("error", "Incorrect user name or password.");
            return "redirect:/admin/login.jsp";
        }
    }
}