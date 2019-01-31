package com.zfog.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Component
public class ThrowexceptionFilter extends ZuulFilter {
    private Logger logger = LoggerFactory.getLogger(ThrowexceptionFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        dosomthing();
        return null;
    }

    private void dosomthing() {
        throw new RuntimeException("Exist some error...");
    }
}
