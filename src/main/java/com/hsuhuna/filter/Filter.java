package com.hsuhuna.filter;

import com.hsuhuna.utils.RedisUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class Filter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(Filter.class);

    /**
     * 返回一个字符串，代表过滤器的类型
     * pre表示被路由之前
     * @return
     */
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
    public Object run(){
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info(request.getRequestURI().toString());

        String token = request.getParameter("token");// 获取请求的参数

        boolean flag = RedisUtil.hasKey(token);

        if (!StringUtils.isEmpty(token) && flag) {
            ctx.setSendZuulResponse(true); //对请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            return null;
        } else {
            ctx.setSendZuulResponse(false); //不对其进行路由
            ctx.setResponseStatusCode(400);
            ctx.setResponseBody("token is empty");
            ctx.set("isSuccess", false);
            return null;

        }
    }
}
