package it.schwarz.jobs.review.coupon.provider;

public class DuplicateCouponException extends RuntimeException {

  public DuplicateCouponException(String message) {
    super(message);
  }
}
