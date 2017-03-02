package com.webchatOil.util;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.webchatOil.util.RequestTypeAnnotation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class RequestTypeInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -4204585527913002611L;
    protected final Log _log = LogFactory.getLog(RequestTypeInterceptor.class);

    public String intercept(ActionInvocation invocation) throws Exception {

        Action action = (Action) invocation.getAction();
        try {
            Method method = action.getClass().getMethod(invocation.getProxy().getMethod(), new Class[] {});
            Annotation[] annotations = method.getAnnotations();
            String methodName = ServletActionContext.getRequest().getMethod();
            for (Annotation annotation : annotations) {
                if (annotation instanceof RequestTypeAnnotation) {
                    RequestTypeAnnotation reqTypeAnnotation = (RequestTypeAnnotation) annotation;
                    if (!reqTypeAnnotation.value().name().equalsIgnoreCase(methodName)) {
                        // 当前台用户请求类型不是方法注解中对应类型时提示此信息
                        return "input";
                    }
                }
            }
        } catch (Exception e) {
            _log.error(e);
            return "error";
        }
        return invocation.invoke();
    }

}
