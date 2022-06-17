package com.example.annotation.resubmit;

import java.lang.annotation.*;

/**
 * @author hkh
 * @version 1.0.0
 * @Description Resubmit
 * @createTime 2022年03月11日 09:42:00
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Resubmit {

    int delaySeconds() default 3;

}

