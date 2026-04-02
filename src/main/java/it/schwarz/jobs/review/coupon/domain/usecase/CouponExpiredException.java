package it.schwarz.jobs.review.coupon.domain.usecase;

public class CouponExpiredException extends BusinessException {

    public CouponExpiredException(String message) {
        super(message);
    }
}