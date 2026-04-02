package it.schwarz.jobs.review.coupon.testobjects;

import it.schwarz.jobs.review.coupon.domain.entity.AmountOfMoney;
import it.schwarz.jobs.review.coupon.domain.entity.Coupon;

import java.time.Instant;

public class TestCoupons {

    public Coupon COUPON_12_20() {
        return new Coupon("CODE_12_20", AmountOfMoney.of("12.00"), AmountOfMoney.of("20.00"), "12 for 20");
    }

    public Coupon NOT_EXISTING_COUPON() {
        return new Coupon("NON_EXISTING", AmountOfMoney.of("0.00"), AmountOfMoney.of("0.00"), "NotExisting");
    }

    public Coupon COUPON_EXPIRED() {
        return new Coupon("CODE_EXPIRED", AmountOfMoney.of("12.00"), AmountOfMoney.of("20.00"), "expired coupon",
                0, Instant.now().minusSeconds(3600), Instant.now().minusSeconds(1800));
    }

    public Coupon COUPON_NOT_YET_VALID() {
        return new Coupon("CODE_NOT_YET_VALID", AmountOfMoney.of("12.00"), AmountOfMoney.of("20.00"), "future coupon",
                0, Instant.now().plusSeconds(3600), Instant.now().plusSeconds(7200));
    }

}
