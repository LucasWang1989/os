package nz.ac.sit.os.infrastructure.channel.exception;

/**
 * @program: os
 * @description: Exception for payload validation of payment channels
 * @author: Lucas Wang
 * @email: lucas.wang.1024@gmail.com
 * @date: 01/03/2026 22:59
 **/
public class PaymentChannelPayloadException extends PaymentChannelException {
    public PaymentChannelPayloadException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentChannelPayloadException(String message) {
        super(message, null);
    }
}