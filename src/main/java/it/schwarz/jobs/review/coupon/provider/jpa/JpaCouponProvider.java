package it.schwarz.jobs.review.coupon.provider.jpa;

import it.schwarz.jobs.review.coupon.domain.entity.AmountOfMoney;
import it.schwarz.jobs.review.coupon.domain.entity.Coupon;
import it.schwarz.jobs.review.coupon.domain.entity.CouponApplications;
import it.schwarz.jobs.review.coupon.domain.usecase.CouponProvider;
import it.schwarz.jobs.review.coupon.provider.DuplicateCouponException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.Optional;

public class JpaCouponProvider implements CouponProvider {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private final CouponJpaRepository couponJpaRepository;
    private final ApplicationJpaRepository applicationRepository;

    public JpaCouponProvider(CouponJpaRepository couponJpaRepository, ApplicationJpaRepository applicationRepository) {
        this.couponJpaRepository = couponJpaRepository;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Coupon createCoupon(Coupon coupon) {
        if (couponJpaRepository.existsById(coupon.getCode())) {
            throw new DuplicateCouponException("Coupon already exists: " + coupon.getCode());
        }
        var toPersist = domainToJpa(coupon);
        var persisted = couponJpaRepository.save(toPersist);
        logger.info("Coupon created: {}", coupon.getCode());
        return jpaToDomain(persisted);
    }

    @Override
    public Page<Coupon> findAll(Pageable pageable) {
        return couponJpaRepository.findAll(pageable).map(this::jpaToDomain);
    }

    @Override
    public void registerCouponApplication(String couponCode) {
        applicationRepository.save(new ApplicationJpaEntity(
                couponCode,
                Instant.now()));
    }

    @Override
    public Optional<Coupon> findById(String couponCode) {
        var found = couponJpaRepository.findById(couponCode);
        return found.map(this::jpaToDomain);
    }

    @Override
    public Optional<CouponApplications> getCouponApplications(String couponCode) {
        var found = couponJpaRepository.findById(couponCode);
        return found.map(couponJpaEntity -> new CouponApplications(
                couponJpaEntity.getCode(),
                couponJpaEntity.getApplications().stream()
                        .map(ApplicationJpaEntity::getTimestamp)
                        .toList()));
    }

    private CouponJpaEntity domainToJpa(Coupon coupon) {
        return new CouponJpaEntity(
                coupon.getCode(),
                coupon.getDiscount().toBigDecimal(),
                coupon.getDescription(),
                coupon.getMinBasketValue().toBigDecimal()
        );
    }


    private Coupon jpaToDomain(CouponJpaEntity couponJpaEntity) {

        return new Coupon(
                couponJpaEntity.getCode(),
                AmountOfMoney.of(couponJpaEntity.getDiscount()),
                AmountOfMoney.of(couponJpaEntity.getMinBasketValue()),
                couponJpaEntity.getDescription(),
                couponJpaEntity.getApplications() == null ? 0 : couponJpaEntity.getApplications().size()
        );
    }

}
