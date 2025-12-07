package com.yiancode.fastauthdemo.controller;

import com.yiancode.fastauthdemo.config.FastAuthConfig;
import com.yiancode.fastauth.config.AuthConfig;
import com.yiancode.fastauth.model.AuthCallback;
import com.yiancode.fastauth.model.AuthResponse;
import com.yiancode.fastauth.model.AuthUser;
import com.yiancode.fastauth.request.AuthRequest;
import com.yiancode.fastauth.request.AuthWeChatMpRequest;
import com.yiancode.fastauth.utils.AuthStateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 微信公众号授权控制器
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final FastAuthConfig fastAuthConfig;

    public AuthController(FastAuthConfig fastAuthConfig) {
        this.fastAuthConfig = fastAuthConfig;
    }

    /**
     * 首页 - 登录入口
     */
    @GetMapping("/")
    public String index() {
        return "redirect:/";
    }

    /**
     * 发起微信授权
     */
    @GetMapping("/login")
    public void login(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = buildAuthRequest();
        String state = AuthStateUtils.createState();
        String authorizeUrl = authRequest.authorize(state);

        log.info("生成授权URL: {}", authorizeUrl);
        response.sendRedirect(authorizeUrl);
    }

    /**
     * 微信回调处理
     */
    @GetMapping("/callback")
    public String callback(AuthCallback callback, HttpSession session, Model model) {
        log.info("收到微信回调, code: {}, state: {}", callback.getCode(), callback.getState());

        AuthRequest authRequest = buildAuthRequest();
        AuthResponse<AuthUser> authResponse = authRequest.login(callback);

        log.info("登录结果: {}", authResponse);

        if (authResponse.ok()) {
            AuthUser user = authResponse.getData();
            session.setAttribute("user", user);
            return "redirect:/user";
        } else {
            model.addAttribute("error", authResponse.getMsg());
            return "error";
        }
    }

    /**
     * 获取当前登录用户信息（API）
     */
    @GetMapping("/user/info")
    @ResponseBody
    public AuthUser userInfo(HttpSession session) {
        return (AuthUser) session.getAttribute("user");
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    /**
     * 构建 AuthRequest
     */
    private AuthRequest buildAuthRequest() {
        return new AuthWeChatMpRequest(AuthConfig.builder()
                .clientId(fastAuthConfig.getClientId())
                .clientSecret(fastAuthConfig.getClientSecret())
                .redirectUri(fastAuthConfig.getRedirectUri())
                .build());
    }
}
