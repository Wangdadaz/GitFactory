package com.wang.hm_takeout.dao.globalException;//wangDD

import com.wang.hm_takeout.dao.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//2022-05-2022/5/10-19:57
//捕获异常的注解
@ControllerAdvice(annotations = {Controller.class, PostMapping.class, RequestMapping.class})
@ResponseBody
@Slf4j
public class EmpException {

    @ExceptionHandler(Exception.class)
    public R<String> exception(Exception e){

        log.info(e.getLocalizedMessage());
        return R.error("出现异常");
    }

}
