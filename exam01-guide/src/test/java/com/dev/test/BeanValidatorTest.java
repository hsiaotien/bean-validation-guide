package com.dev.test;

import com.dev.pojo.Car;
import com.dev.util.BeanValidatorUtil;
import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

public class BeanValidatorTest {

    /**
     * 验证bean参数，并返回验证结果消息
     */
    @Test
    public void test01() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Car car = new Car();
        // 验证bean参数，并返回验证结果消息
        Set<ConstraintViolation<Car>> resultValidator = validator.validate(car);
        for (ConstraintViolation<Car> carConstraintViolation : resultValidator) {
            System.out.println("message:" + carConstraintViolation.getMessage());
        }
    }

    /**
     * 验证方法参数
     */
    @Test
    public void test02() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Method method = null;
        try {
            method = Car.class.getMethod("drive", int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Car car = new Car();
        Object[] parameterValues = {80};
        ExecutableValidator executableValidator = validator.forExecutables();
        // 验证方法参数
        Set<ConstraintViolation<Car>> constraintViolations = executableValidator.validateParameters(car, method, parameterValues);
        for (ConstraintViolation<Car> constraintViolation : constraintViolations) {
            System.out.println("message: " + constraintViolation.getMessage());
        }
    }

    /**
     * 将校验的结果包装成异常，验证不成功就抛出异常
     */
    @Test
    public void test03() {
        // 可以在每个方法的第一行调用BeanValidator.validate来验证参数
        try {
            BeanValidatorUtil.validate(new Car());
        } catch (Exception e) {
            Assert.assertEquals("车主不能为空",e.getMessage());
        }
    }


}
