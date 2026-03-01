package nz.ac.sit.os.infrastructure.channel.exception;

/**
 * @program: os
 * @description: Exception for payment channel communication
 * @author: Lucas Wang
 * @email: lucas.wang.1024@gmail.com
 * @date: 01/03/2026 22:59
 **/
public class PaymentChannelTransportException extends PaymentChannelException {
    public PaymentChannelTransportException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentChannelTransportException(String message) {
        super(message, null);
    }
}