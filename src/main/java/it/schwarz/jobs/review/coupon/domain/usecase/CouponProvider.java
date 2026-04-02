package it.schwarz.jobs.review.coupon.domain.usecase;

import it.schwarz.jobs.review.coupon.domain.entity.Coupon;
import it.schwarz.jobs.review.coupon.domain.entity.CouponApplications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CouponProvider {
    Coupon createCoupon(Coupon coupon);

    Page<Coupon> findAll(Pageable pageable);

    Optional<Coupon> findById(String couponCode);

    void registerCouponApplication(String couponCode);

    Optional<CouponApplications> getCouponApplications(String couponCode);
}
