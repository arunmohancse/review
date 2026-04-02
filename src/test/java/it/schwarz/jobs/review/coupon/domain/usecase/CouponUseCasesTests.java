package it.schwarz.jobs.review.coupon.domain.usecase;

import it.schwarz.jobs.review.coupon.domain.entity.AmountOfMoney;
import it.schwarz.jobs.review.coupon.domain.entity.Basket;
import it.schwarz.jobs.review.coupon.provider.inmem.InMemoryCouponProvider;
import it.schwarz.jobs.review.coupon.testobjects.TestObjects;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CouponUseCasesTests {

    @Test
    void testFindAllCoupons() {
        var couponUseCases = new CouponUseCases(new InMemoryCouponProvider());
        var allCoupons = couponUseCases.findAllCoupons(PageRequest.of(0, 10));
        assertThat(allCoupons).hasSize(3);
    }

    @Test
    void testCreateCoupon() {
        // Create
        var couponUseCases = new CouponUseCases(new InMemoryCouponProvider());
        var createdCoupon = couponUseCases.createCoupon(TestObjects.coupons().COUPON_12_20());
        assertThat(createdCoupon).isNotNull();

        // Duplicate
        Exception exception = assertThrows(CouponAlreadyExistsException.class, () -> couponUseCases.createCoupon(TestObjects.coupons().COUPON_12_20()));
        assertThat(exception.getMessage()).contains("Coupon already exists");
    }

    @Test
    void testApplyCoupon_expiredCoupon_throwsCouponExpiredException() {
        var couponUseCases = new CouponUseCases(new InMemoryCouponProvider());
        couponUseCases.createCoupon(TestObjects.coupons().COUPON_EXPIRED());
        var basket = new Basket(AmountOfMoney.of("50.00"));

        assertThrows(CouponExpiredException.class, () ->
                couponUseCases.applyCoupon(basket, "CODE_EXPIRED"));
    }

    @Test
    void testApplyCoupon_notYetValidCoupon_throwsCouponNotYetValidException() {
        var couponUseCases = new CouponUseCases(new InMemoryCouponProvider());
        couponUseCases.createCoupon(TestObjects.coupons().COUPON_NOT_YET_VALID());
        var basket = new Basket(AmountOfMoney.of("50.00"));

        assertThrows(CouponNotYetValidException.class, () ->
                couponUseCases.applyCoupon(basket, "CODE_NOT_YET_VALID"));
    }


}