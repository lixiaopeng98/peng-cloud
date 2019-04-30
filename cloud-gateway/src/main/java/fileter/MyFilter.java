package fileter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 自定义Filter 获取请求头参数  get put 的参数
 * @author Lxp
 */
public class MyFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public String filterType() {
//        Standard types in Zuul are "pre" for pre-routing filtering,
//        "route" for routing to an origin, "post" for post-routing filters, "error" for error handling.
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        logger.info("进入 MyFilter ");
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();

        String s = "MyFilter";

        //循环打印请求头信息
        Enumeration<String> headerNames = request.getHeaderNames();
        if (!ObjectUtils.isEmpty( headerNames)){
            while (headerNames.hasMoreElements()){
                String headerName = headerNames.nextElement();
                logger.info("{} -- request header : headername {}, value {}",s,headerName,request.getHeader(headerName));
            }
        }else {
            logger.info("{} -- request header : name {}, value {}",s,null,null);
        }

        Enumeration<String> parameterNames = request.getParameterNames();
        if (!ObjectUtils.isEmpty( parameterNames)){
            while (parameterNames.hasMoreElements()){
                String parameterElement = parameterNames.nextElement();
                logger.info("{} -- request Parameter : name {}, value {}",s,parameterElement,request.getParameter(parameterElement));
            }
        }else {
            logger.info("{} -- request header : name {}, value {}",s,null,null);
        }


        Enumeration<String> attributeNames = request.getAttributeNames();
        if (!ObjectUtils.isEmpty( attributeNames)){
            while (attributeNames.hasMoreElements()){
                String attributeElement = attributeNames.nextElement();
                logger.info("{} -- request attribute : name {}, value {}",s,attributeElement,request.getParameter(attributeElement));
            }
        }else {
            logger.info("{} -- request attribute : name {}, value {}",s,null,null);
        }

        return null;
    }
}
