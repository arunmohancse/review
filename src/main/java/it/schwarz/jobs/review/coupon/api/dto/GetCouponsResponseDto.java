package it.schwarz.jobs.review.coupon.api.dto;

import it.schwarz.jobs.review.coupon.domain.entity.Coupon;
import org.springframework.data.domain.Page;

import java.util.List;

public record GetCouponsResponseDto(
        List<CouponDto> coupons,
        int page,
        int size,
        long totalElements,
        int totalPages
        ) {

    public static GetCouponsResponseDto of(Page<Coupon> couponPage) {
        return new GetCouponsResponseDto(
                couponPage.getContent().stream()
                        .map(coupon -> new CouponDto(
                                coupon.getCode(),
                                coupon.getDiscount().toBigDecimal(),
                                coupon.getMinBasketValue().toBigDecimal(),
                                coupon.getDescription(),
                                coupon.getApplicationCount()))
                        .toList(),
                couponPage.getNumber(),
                couponPage.getSize(),
                couponPage.getTotalElements(),
                couponPage.getTotalPages()
                );
    }
}
