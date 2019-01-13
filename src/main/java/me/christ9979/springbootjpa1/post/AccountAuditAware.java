package me.christ9979.springbootjpa1.post;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @CreatedBy, @LastModifiedBy를 사용하려면 @EnableJpaAuditing(auditorAwareRef = "Bean name")으로
 * 유저를 명시하는데 사용하는 빈을 등록해주어야 한다.
 * 이 때 사용되는 빈이 AuditorAware을 구현한 빈이다.
 * 이 클래스에서는 감시당할 유저를 추출하는 로직을 작성해야 한다.
 */
@Service
public class AccountAuditAware implements AuditorAware<Account> {

    @Override
    public Optional<Account> getCurrentAuditor() {
        System.out.println("looking for current user");
        return Optional.empty();
    }
}
