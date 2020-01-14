package com.lindl.user.aop;

import com.lindl.user.mapper.ResourceMapper;
import com.lindl.user.mapper.RoleMapper;
import com.lindl.user.mapper.UserMapper;
import com.lindl.user.po.Role;
import com.lindl.user.po.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.thymeleaf.util.ListUtils;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/1/10 15:23
 */
@Aspect
@Slf4j
@Component
public class PermissionAccessAspect {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private ResourceMapper resourceMapper;


    @Pointcut(value = "@annotation(com.lindl.user.aop.UserAccess)")
    public void pointcut(){};

    @Around(value = "pointcut()")
    public Object arround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Signature signature = proceedingJoinPoint.getSignature();
        String methodName = signature.getName();
        String className = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        log.info("class name : {}, method name: {}", className, methodName);

        //角色校验
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(UserAccess.class)) {
            //获取用户权限
            UserAccess userAccess = method.getAnnotation(UserAccess.class);
            String role = userAccess.role();
            log.info("当前用户角色: {}", role);
            if ("".equals(role)) {
                throw new Exception("该用户权限不足");
            }

            List<String> roles = Arrays.asList(role.split(","));
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String authorization = request.getHeader("Authorization");
            if (StringUtils.isEmpty(authorization)) {
                throw new Exception("缺少校验字段");
            }
            User user = userMapper.selectById(Long.parseLong(authorization));
            if (Objects.isNull(user)) {
                throw new Exception("该用户不存在");
            }

            List<Role> roleList = roleMapper.findByUserId(Long.parseLong(authorization));
            List<String> rolesByUserId = roleList.stream().map(Role::getName).collect(Collectors.toList());
            List<String> collect = (List<String>)Stream.of(roleList, rolesByUserId).flatMap(Collection::stream).distinct().collect(Collectors.toList());
            if (!ListUtils.isEmpty(collect)) {
                Object proceed = proceedingJoinPoint.proceed();
                return proceed;
            } else {
                throw new Exception("该用户权限不足");
            }

        }
        return null;
    }
}
