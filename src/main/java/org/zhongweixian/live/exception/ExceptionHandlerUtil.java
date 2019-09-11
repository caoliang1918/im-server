package org.zhongweixian.live.exception;

import com.fasterxml.jackson.core.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.zhongweixian.live.web.CommonResponse;

import java.sql.SQLException;
import java.text.ParseException;

/**
 *
 */
@ControllerAdvice
@Transactional(rollbackFor = RuntimeException.class)
public class ExceptionHandlerUtil {
    private Logger logger = LoggerFactory.getLogger(ExceptionHandlerUtil.class);

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public CommonResponse<String> handleSystem(BusinessException ex) {
        logger.error("错误代码:{}  提示信息:{}", ex.getErrorCode().getCode(), ex.getMessage());
        return new CommonResponse<String>(ex.getErrorCode().getCode(), ex.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public CommonResponse<String> handleParameter(MissingServletRequestParameterException ex) {
        logger.error("MissingServletRequestParameterException:{}", ex);
        return new CommonResponse<String>(ErrorCode.SYS_PARAMETERS_EMPTY_ERROR.getCode(), ErrorCode.SYS_PARAMETERS_EMPTY_ERROR.getMessage(ex.getParameterName()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public CommonResponse<String> noHandlerFound(NoHandlerFoundException ex) {
        logger.error("No handler found for:{}", ex);
        return new CommonResponse<String>(ErrorCode.SYS_NO_HANDLER_FOUND_ERROR.getCode(), ErrorCode.SYS_NO_HANDLER_FOUND_ERROR.getMessage(ex.getRequestURL()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public CommonResponse<String> handleArgValid(MethodArgumentNotValidException ex) {
        logger.error("MethodArgumentNotValidException:{}", ex);
        StringBuilder message = new StringBuilder("");
        for (int i = 0; i < ex.getBindingResult().getFieldErrorCount(); i++) {
            if (i != 0) {
                message.append(",");
            }
            message.append(ex.getBindingResult().getFieldErrors().get(i).getDefaultMessage());
        }
        return new CommonResponse<String>(ErrorCode.BEAN_EMPTY_PROPERTY_ERROR.getCode(), ErrorCode.BEAN_EMPTY_PROPERTY_ERROR.getMessage(message));
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseBody
    public CommonResponse<String> handleArgValid(ServletRequestBindingException ex) {
        logger.error("ServletRequestBindingException:{}", ex);
        return new CommonResponse<String>(ErrorCode.BEAN_EMPTY_PROPERTY_ERROR.getCode(), ErrorCode.BEAN_EMPTY_PROPERTY_ERROR.getMessage(ex.getMessage()));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public CommonResponse<String> handleRepeatSubmit(DuplicateKeyException ex) {
        logger.error("DuplicateKeyException:{}", ex);
        return new CommonResponse<String>(ErrorCode.SYS_REPEAT_SUBMIT_EXCEPTION.getCode(), ErrorCode.SYS_REPEAT_SUBMIT_EXCEPTION.getMessage(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public CommonResponse<String> handleArgMisd(MethodArgumentTypeMismatchException ex) {
        logger.error("MissingServletRequestParameterException:{}", ex);
        return new CommonResponse<String>(ErrorCode.SYS_ARGUMENT_TYPE__ERROR.getCode(), ErrorCode.SYS_ARGUMENT_TYPE__ERROR.getMessage(ex.getName()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public CommonResponse<String> handleMethodNotSupport(HttpRequestMethodNotSupportedException ex) {
        logger.error("HttpRequestMethodNotSupportedException:{}", ex);
        return new CommonResponse<String>(ErrorCode.SYS_METHOD_NOT_SUPPORT.getCode(), ErrorCode.SYS_METHOD_NOT_SUPPORT.getMessage(ex.getMethod()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public CommonResponse<String> handleArgNotReadable(HttpMessageNotReadableException ex) {
        logger.error("HttpMessageNotReadableException:{}", ex);
        return new CommonResponse<String>(ErrorCode.SYS_PARAMETER_ERROR.getCode(), ErrorCode.SYS_PARAMETER_ERROR.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public CommonResponse<String> handleNull(NullPointerException ex) {
        logger.error("NullPointerException:{}", getStackTrace(ex));
        return new CommonResponse<String>(ErrorCode.SYS_NULLPOINTER_ERROR.getCode(), ErrorCode.SYS_NULLPOINTER_ERROR.getMessage());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public CommonResponse<String> handleMediaTypeNotSuported(HttpMediaTypeNotSupportedException ex) {
        logger.error("HttpMediaTypeNotSupportedException:{}", ex);
        return new CommonResponse<String>(ErrorCode.SYS_MEDIATYPE_NOT_SUPPORT.getCode(), ErrorCode.SYS_MEDIATYPE_NOT_SUPPORT.getMessage(ex.getContentType().getType()));
    }

    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    public CommonResponse<String> handleClassCast(ClassCastException ex) {
        logger.error("ClassCastException:{}", ex);
        return new CommonResponse<String>(ErrorCode.SYS_CLASSCAST_ERROR.getCode(), ErrorCode.SYS_CLASSCAST_ERROR.getMessage());
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    public CommonResponse<String> handleNumberFormat(NumberFormatException ex) {
        logger.error("NumberFormatException:{}", ex);
        return new CommonResponse<String>(ErrorCode.SYS_NUMBER_FORMAT_ERROR.getCode(), ErrorCode.SYS_NUMBER_FORMAT_ERROR.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResponse<String> handleExcption(Exception ex) {
        logger.error(ErrorCode.SYS_SERVER_ERROR.getMessage() + "", ex);
        return new CommonResponse<String>(ErrorCode.SYS_SERVER_ERROR.getCode(), ErrorCode.SYS_SERVER_ERROR.getMessage());
    }

    @ExceptionHandler(JsonParseException.class)
    @ResponseBody
    public CommonResponse<String> jsonParseException(Exception e) {
        logger.error("HttpMessageNotReadableException:{}", e);
        return new CommonResponse<String>(ErrorCode.SYS_JSON_ERROR.getCode(), ErrorCode.SYS_JSON_ERROR.getMessage());
    }

    @ExceptionHandler(ParseException.class)
    @ResponseBody
    public CommonResponse<String> parseException(Exception e) {
        logger.error("ParseException:{}", e);
        return new CommonResponse<String>(ErrorCode.SYS_CLASSCAST_ERROR.getCode(), ErrorCode.SYS_CLASSCAST_ERROR.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public CommonResponse<String> sqlException(Exception e) {
        logger.error("HttpMessageNotReadableException:{}", getStackTrace(e));
        return new CommonResponse<String>(ErrorCode.SYS_SQL_EXCPETION.getCode(), ErrorCode.SYS_SQL_EXCPETION.getMessage());
    }


    /**
     * 输出异常信息
     *
     * @param e
     * @return
     */
    @SuppressWarnings("unused")
    private String getStackTrace(Exception e) {
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] trace = e.getStackTrace();
        for (StackTraceElement traceElement : trace) {
            sb.append("\tat ").append(traceElement).append("\n");
        }
        sb.append(e.toString());
        return sb.toString();
    }
}
