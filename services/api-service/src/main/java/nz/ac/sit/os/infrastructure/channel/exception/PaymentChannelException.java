package nz.ac.sit.os.infrastructure.channel.exception;

/**
 * @program: os
 * @description: parent exception class for payment channel
 * @author: Lucas Wang
 * @email: lucas.wang.1024@gmail.com
 * @date: 02/03/2026 11:51
 **/
public class PaymentChannelException extends RuntimeException{
    public PaymentChannelException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentChannelException(String message) {
        super(message, null);
    }
}