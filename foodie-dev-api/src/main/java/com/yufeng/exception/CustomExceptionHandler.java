package com.yufeng.exception;

import com.yufeng.utils.IMOOCJSONResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 描述:
 *      自定义异常助手类
 * @author yufeng
 * @create 2020-12-07
 */

@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * 上传文件超过500k, 捕获异常: MaxUploadSizeExceededException
     * @param ex
     * @return
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public IMOOCJSONResult handlerMaxUploadFile(MaxUploadSizeExceededException ex) {
        return IMOOCJSONResult.errorMsg("文件上传大小不能超过500K，请压缩图片或者降低图片质量再上传! ");
    }



}
