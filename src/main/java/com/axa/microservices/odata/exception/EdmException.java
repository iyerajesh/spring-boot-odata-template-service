/**
 * 
 */
package com.axa.microservices.odata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Rajesh Iyer
 *
 */
@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR,reason = "Error Processing Edm")
public class EdmException extends RuntimeException {

}
