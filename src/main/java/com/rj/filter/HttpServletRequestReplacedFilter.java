package com.rj.filter;

import com.alibaba.fastjson.JSON;
import com.rj.common.HttpHelper;
import com.rj.result.Result;
import com.rj.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ruanjin
 * @since 2019/5/24 11:15
 */
@Slf4j
public class HttpServletRequestReplacedFilter implements Filter {

    private static final String METHOD_POST = "POST";

    private static final String ERROR_MSG_1 = "请求中含有特殊字符";

    private static final String ERROR_MSG_2 = "请求方法不支持";

    private static final String METHOD_GET = "GET";

    private static final String MEDIA_MULTIPART_FORM_DATA = "multipart/form-data";

    private static final String REGEX = "(?s).*<script>(?s).*</script>(?s).*";

    private static final String SPACE = " ";

    private static final Pattern FILTER_STATIC_RESOURCE = Pattern.compile(".*\\.(bmp|css|js|gif|ico|jp?g|png|swf|woff)");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            Matcher matcher = FILTER_STATIC_RESOURCE.matcher(httpServletRequest.getRequestURI());

            if (matcher.matches()) {
                chain.doFilter(request, response);
            } else {
                String method = httpServletRequest.getMethod();
                String url = httpServletRequest.getRequestURL()
                        .append(StringUtils.isBlank(httpServletRequest.getQueryString()) ? "" : "?" + httpServletRequest.getQueryString())
                        .toString();
                url = URLDecoder.decode(url, "utf-8");
                String contentType = request.getContentType();
                String accept = httpServletRequest.getHeader("Accept");

                if (method.equals(METHOD_GET)) {

                    log.info("\n" + METHOD_GET + " " + url + "\n" + "Content-Type: " + contentType + "\n" + "Accept: " + accept);
                    if (url.trim().replace(SPACE, "").toLowerCase().matches(REGEX)) {
                        errorRequest(response, ERROR_MSG_1);
                    } else {
                        chain.doFilter(request, response);
                    }

                } else if (method.equals(METHOD_POST)) {

                    if (contentType != null && contentType.contains(MEDIA_MULTIPART_FORM_DATA)) {
                        log.info("\n" + METHOD_POST + " " + url + " " + "文件上传");
                        chain.doFilter(request, response);
                    } else {
                        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
                        if (url.trim().replace(SPACE, "").toLowerCase().matches(REGEX)) {
                            errorRequest(response, ERROR_MSG_1);
                            return;
                        }

                        String body = HttpHelper.getBodyString(requestWrapper);
                        log.info("\n" + METHOD_POST + " " + url + "\n" + "Content-Type: " + contentType + "\n" + "Accept: " + accept);

                        if (StringUtils.isNotBlank(body)) {
                            log.info("\n" + "body: " + body);
                            if (body.trim().replace(SPACE, "").toLowerCase().matches(REGEX)) {
                                errorRequest(response, ERROR_MSG_1);
                                return;
                            }
                        }
                        chain.doFilter(requestWrapper, response);
                    }

                } else {
                    errorRequest(response, method + ERROR_MSG_2);
                }
            }

        } else {
            chain.doFilter(request, response);
        }
    }

    private void errorRequest(ServletResponse response, String errorMsg) throws IOException {
        log.error("请求中含有特殊字符");
        Result result = new Result();
        result.setCode(ResultCode.OPERATION_FAILURE.getCode());
        result.setSuccess(false);
        result.setMessage("非法请求");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(result));
    }


    @Override
    public void destroy() {

    }
}
