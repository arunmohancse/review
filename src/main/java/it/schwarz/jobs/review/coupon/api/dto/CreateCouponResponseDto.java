package it.schwarz.jobs.review.coupon.api.dto;

import it.schwarz.jobs.review.coupon.domain.entity.Coupon;

public record CreateCouponResponseDto(CouponDto coupon) {

    public static CreateCouponResponseDto of(Coupon coupon) {
        return new CreateCouponResponseDto(CouponDto.of(coupon));
    }
}
