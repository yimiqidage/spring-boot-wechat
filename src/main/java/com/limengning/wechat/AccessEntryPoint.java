package com.limengning.wechat;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

public class AccessEntryPoint extends HttpFilter {
    private final EntryService entryService;
    private final String entryUrl;
    private final String getMethod = "GET";

    public AccessEntryPoint(EntryService entryService, WechatConfig config) {
        this.entryService = entryService;
        this.entryUrl = config.getEntryUrl();
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String uri = req.getRequestURI();
        System.out.println(uri);
        if (uri.equals(entryUrl) && req.getMethod().equals(getMethod)) {
            String signature = req.getParameter("signature");
            String timestamp = req.getParameter("timestamp");
            String nonce = req.getParameter("nonce");
            String echostr = req.getParameter("echostr");
            if (StringUtils.hasText(signature) && StringUtils.hasText(timestamp) && StringUtils.hasText(nonce)
                    && StringUtils.hasText(echostr)) {
                if (entryService.checkSignature(signature, timestamp, nonce)) {
                    res.getOutputStream().print(echostr);
                }
            } else {
                res.setStatus(400);
            }
        }
        chain.doFilter(req, res);
    }

}
