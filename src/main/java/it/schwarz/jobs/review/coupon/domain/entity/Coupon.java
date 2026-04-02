package it.schwarz.jobs.review.coupon.domain.entity;

import java.time.Instant;

public class Coupon {

    private final String code;
    private final AmountOfMoney discount;
    private final AmountOfMoney minBasketValue;
    private final String description;
    private final long applicationCount;
    private final Instant validFrom;
    private final Instant validUntil;

    public Coupon(String code, AmountOfMoney discount, AmountOfMoney minBasketValue, String description) {
        this(code, discount, minBasketValue, description, 0, null, null);
    }

    public Coupon(String code, AmountOfMoney discount, AmountOfMoney minBasketValue, String description, long applicationCount) {
        this(code, discount, minBasketValue, description, applicationCount, null, null);
    }

    public Coupon(String code, AmountOfMoney discount, AmountOfMoney minBasketValue, String description, long applicationCount, Instant validFrom, Instant validUntil) {
        this.code = code;
        this.discount = discount;
        this.minBasketValue = minBasketValue;
        this.description = description;
        this.applicationCount = applicationCount;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
    }

    public String getCode() {
        return code;
    }

    public AmountOfMoney getDiscount() {
        return discount;
    }

    public AmountOfMoney getMinBasketValue() {
        return minBasketValue;
    }

    public String getDescription() {
        return description;
    }

    public long getApplicationCount() {
        return applicationCount;
    }

    public Instant getValidFrom() { return validFrom; }

    public Instant getValidUntil() { return validUntil; }

}
