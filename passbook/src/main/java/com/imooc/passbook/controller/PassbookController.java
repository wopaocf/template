package com.imooc.passbook.controller;

import com.imooc.passbook.log.LogConstants;
import com.imooc.passbook.log.LogGenerator;
import com.imooc.passbook.service.IFeedbackService;
import com.imooc.passbook.service.IGainPassTemplateService;
import com.imooc.passbook.service.IInventoryService;
import com.imooc.passbook.service.IUserPassService;
import com.imooc.passbook.vo.Feedback;
import com.imooc.passbook.vo.GainPassTemplateRequest;
import com.imooc.passbook.vo.Pass;
import com.imooc.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>Passbook Rest Controller</h1>
 */
@Slf4j
@RestController
@RequestMapping("/passbook")
public class PassbookController {
    /**用户优惠券服务**/
    @Autowired
    private IUserPassService userPassService;
    /**优惠券库存服务***/
    @Autowired
    private IInventoryService inventoryService;
    /**领取优惠券服务**/
    @Autowired
    private IGainPassTemplateService gainPassTemplateService;
    /**反馈服务*/
    @Autowired
    private IFeedbackService feedbackService;
    /**HttpServletRequest***/
    private HttpServletRequest httpServletRequest;


    /**
     * <h2>获取用户个人的优惠券信息</h2>
     * @param userId
     * @return
     * @throws Exception
     */
    @ResponseBody
    @GetMapping("/userpassinfo")
  Response userPassInfo(Long userId) throws  Exception{
      LogGenerator.genLog(
              httpServletRequest,
              userId, LogConstants.ActionName.USER_PASS_INFO,
              null
      );

      return userPassService.getUserPassInfo(userId);

  }

    /**
     * <h2>获取用户已经使用了的优惠券信息</h2>
     * @param userId     用户id
     * @return  {@link Response}
     * @throws Exception
     */
    @ResponseBody
    @GetMapping("userUsedPassInfo")
    Response userUsedPassInfo(Long userId) throws Exception{
        LogGenerator.genLog(
            httpServletRequest,
                userId,LogConstants.ActionName.USER_USED_PASS_INFO,
                null
        );

        return  userPassService.getUserUsedPassInfo(userId);
    }

    /**
     * <h2>用户使用优惠券</h2>
     * @param pass {@link Pass}
     * @return   {@link Response}
     */
      @ResponseBody
      @PostMapping("/userusepass")
      Response userUsePass(Pass pass){


           LogGenerator.genLog(
                   httpServletRequest,
                   pass.getUserId(),
                   LogConstants.ActionName.USER_USED_PASS,
                   pass
           );
           return userPassService.userUsePass(pass);
      }


    /**
     * <h2>获取库存信息</h2>
     * @param userId 用户id
     * @return   {@link Response}
     * @throws Exception
     */
      @ResponseBody
      @GetMapping("/inventoryinfo")
      Response  inventoryInfo(Long userId) throws Exception{

          LogGenerator.genLog(
                  httpServletRequest,
                  userId,
                  LogConstants.ActionName.INVENTORY_INFO,
                  null
          );
          return inventoryService.getInventoryInfo(userId);
      }

    /**
     * <h2>用户领取优惠券</h2>
     * @param request  {@link GainPassTemplateRequest}
     * @return    {@link Response}
     * @throws Exception
     */
        @ResponseBody
        @PostMapping("/gainPassTemplate")
        Response gainPassTemplate(@RequestBody GainPassTemplateRequest request)
                throws Exception{
             LogGenerator.genLog(
                     httpServletRequest,
                     request.getUserId(),
                     LogConstants.ActionName.GAIN_PASS_TEMPLATE,
                     request
             );
                 return gainPassTemplateService.gainPassTemplate(request);

      }

    /**
     * <h2>用户创建评论</h2>
     * @param feedback {@link Feedback}
     * @return {@link Response}
     */
      @ResponseBody
      @PostMapping("/createfeedback")
      Response createFeedback(@RequestBody Feedback feedback){

          LogGenerator.genLog(
                  httpServletRequest,
                  feedback.getUserId(),
                  LogConstants.ActionName.CREATE_FEEDBACK,
                  feedback
          );
          return  feedbackService.createFeedback(feedback);
      }

    /**
     * <h2>用户获取评论</h2>
     * @param userId   用户id
     * @return    {@link Response}
     */
      @ResponseBody
      @GetMapping("/getfeedback")
      Response  getFeedback(Long userId){
          LogGenerator.genLog(
                  httpServletRequest,
                  userId,
                  LogConstants.ActionName.GET_FEEDBACK,
                  null
          );
          return feedbackService.getFeedback(userId);
      }

    /**
     * <h2>异常演示接口</h2>
     * @return  {@link Response}
     * @throws Exception
     */
    @ResponseBody
    @GetMapping("/exception")
    Response exception() throws Exception{

            throw  new Exception("Welcome to IMooc!");
       }

}
