package it.schwarz.jobs.review.coupon.domain.usecase;

public class CouponNotYetValidException extends BusinessException {
    public CouponNotYetValidException(String message) {
        super(message);
    }
}
