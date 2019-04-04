package com.imooc.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * <h1>统一响应</h1>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    /**错误码：正确返回 0*/
    private Integer errorCode=0;
    /**错误信息，正确返回空字符串*/
    private String errorMsg="";
    /**返回值对象**/
    private Object data;

    /**
     * <h2>正确的响应构造函数</h2>
     * @param data
     */
    public  Response(Object data){
        this.data=data;
    }

    /**空响应*/
    public static Response success(){
        return new Response();
    }

    /**
     * <h2>错误响应</h2>
     * @param errorMsg
     * @return
     */
    public static Response failure(String errorMsg){
        return  new Response(-1,errorMsg,null);
    }

}
