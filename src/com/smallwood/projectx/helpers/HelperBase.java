package com.smallwood.projectx.helpers;

import com.smallwood.projectx.ButtonMethod;
import com.smallwood.projectx.Professional;
import com.smallwood.projectx.ProfessionalCenter;
import com.smallwood.projectx.RegistryCenter;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bigwood928 on 4/14/14.
 */
public class HelperBase implements IHelper {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpServlet servlet;
    private Method methodDefault = null;
    protected Professional loggedInAccount = null;
    protected Map<String, String> errorMap = new HashMap<String, String>();




    public HelperBase(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) {
        this.request = request;
        this.response = response;
        this.servlet = servlet;
        this.loggedInAccount = new Professional();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
    }

    @Override
    public void doGet() {

    }

    protected String executeButtonMethod() throws ServletException, IOException {
        String result = "";
        methodDefault = null;
        Class clazz = this.getClass();
        Class enclosingClass  = clazz.getEnclosingClass();
        while (enclosingClass != null){
            clazz = this.getClass();
            enclosingClass = clazz.getEnclosingClass();
        }

        try{
            result = executeButtonMethod(clazz, true);
        } catch (Exception ex) {
            //writeError(request, responsef)
            return "";
        }
        return result;
    }

    protected String executeButtonMethod(Class clazz, boolean searchForDefault) throws IllegalAccessException, InvocationTargetException {
        String result = "";
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method : methods) {
            ButtonMethod annotation = method.getAnnotation(ButtonMethod.class);
            if(annotation != null) {
                if(searchForDefault && annotation.isDefault()) {
                    methodDefault = method;
                }
                if(request.getParameter(annotation.buttonName()) != null) {
                    result = invokeButtonMethod(method);
                    break;
                }
            }
        }
        if(result.equals("")) {
            Class superClass = clazz.getSuperclass();
            if(superClass != null) {
                result = executeButtonMethod(superClass, methodDefault == null);
            }
            if(result.equals("")) {
                if(methodDefault != null) {
                    result = invokeButtonMethod(methodDefault);
                }
            }
        }
        return result;
    }

    protected String invokeButtonMethod(Method buttonMethod) throws IllegalAccessException, InvocationTargetException {
        String resultInvoke = "Could not invoke method";
        try{
            resultInvoke = (String) buttonMethod.invoke(this, (Object[]) null);
        } catch (IllegalAccessException e) {
            throw e;
        } catch (InvocationTargetException ite) {
            throw ite;
        }
        return resultInvoke;
    }

    public void fillBeanFromRequest(Object data) {
        try {
            String address;
            BeanUtils.populate(data, request.getParameterMap());
            ProfessionalCenter center = RegistryCenter.get(ProfessionalCenter.class);
            if(center.verifyProfessional(data)) {

            }
        } catch (IllegalAccessException e) {
            System.out.println(Utilities.exceptionToString(e));
        } catch (InvocationTargetException e) {
            System.out.println(Utilities.exceptionToString(e));
        } catch (Exception e) {
            System.out.println(Utilities.exceptionToString(e));
        }
    }

    public void setErrors(Object data) {

    }
}
