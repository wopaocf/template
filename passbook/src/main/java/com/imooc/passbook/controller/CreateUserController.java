package com.imooc.passbook.controller;

import com.imooc.passbook.log.LogConstants;
import com.imooc.passbook.log.LogGenerator;
import com.imooc.passbook.service.IUserPassService;
import com.imooc.passbook.service.IUserService;
import com.imooc.passbook.vo.Response;
import com.imooc.passbook.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>创建用户服务</h1>
 */
@Slf4j
@RestController
@RequestMapping("/passbook")
public class CreateUserController {
    /**创建用户服务**/
    @Autowired
    private IUserService userService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * <h2>创建用户</h2>
     * @param user {@link User}
     * @return   {@link Response}
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/createuser")
    Response createUser(@RequestBody User user) throws Exception{

        LogGenerator.genLog(
                httpServletRequest,
                -1L,
                LogConstants.ActionName.CREATE_USER,
                user
        );
      return  userService.createUser(user);
    }
}
