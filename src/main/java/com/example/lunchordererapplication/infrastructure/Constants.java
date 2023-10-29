package com.example.lunchordererapplication.infrastructure;

public class Constants {

    public static final String URL_DAILY_MENU = "/daily/menu";
    public static final String URL_ORDER = "/order";
    public static final String URL_ORDER_UPDATE = URL_ORDER + "/{orderId}";
    public static final String SUMMARIZED_ORDER_SUBJECT = "[DAILY ORDER]";
    public static final String SUMMARIZED_ORDER_GREETING =
            "Hello, we would like to order the following:\n\n";
    public static final String SUMMARIZED_ORDER_REGARDS = """

            Regards,
            Leapwise team""";
    public static final String SENDING_MAIL_LOG =
            "Sending daily mail of summarized orders";
    public static final String MAIL_SENT_SUCCESSFULLY_LOG =
            "Message sent successfully";


    private Constants() {
        throw new IllegalArgumentException("Constants class");
    }
}
