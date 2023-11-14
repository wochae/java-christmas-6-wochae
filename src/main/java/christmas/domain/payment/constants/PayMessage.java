package christmas.domain.payment.constants;

public enum PayMessage {
    LOW_PRICE("<증정 메뉴>\n"
            + "없음\n"
            + " \n"
            + "<혜택 내역>\n"
            + "없음\n"
            + " \n"
            + "<총혜택 금액>\n"
            + "0원\n"
            + " \n"
            + "<할인 후 예상 결제 금액>\n"
            + "%,d원\n"
            + " \n"
            + "<12월 이벤트 배지>\n"
            + "없음"),
    GIFT_EVENT("\n<증정 메뉴>\n"),
    ONE_BOTTLE("샴페인 1개\n"),
    NONE("없음\n"),
    BENEFIT_RECIPE("\n<혜택 내역>\n"),
    X_MAX_DISCOUNT("크리스마스 디데이 할인: -%,d원\n"),
    WEEKDAY_DISCOUNT("평일 할인: -%,d원\n"),
    WEEKEND_DISCOUNT("주말 할인: -%,d원\n"),
    SPECIAL_DISCOUNT("특별 할인: -%,d원\n"),
    GIFT_EVENT_DISCOUNT("증정 이벤트: -%,d원\n"),
    AMOUNT_BENEFIT_PRICE("\n<총혜택 금액>\n"),
    EXPECTED_PRICE("\n<할인 후 예상 결제 금액>\n"),
    PRICE_MINUS_FORMAT("-%,d원\n"),
    PRICE_FORMAT("%,d원\n"),
    BADGE("\n<12월 이벤트 배지>\n"),


    STAR("별"),
    TREE("트리"),
    SANTA("산타");
    private final String message;
    PayMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
