package me.christ9979.springbootjpa1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/**
 * @EnableJpaAuditing을 이용하여 Auditing 기능을 사용하겠다고 설정한다.
 * Auditing 기능은 누가 수정했고, 누가 만들었는지 감시하는 기능이다.
 *
 * auditorAwareRef에
 */
@EnableJpaAuditing(auditorAwareRef = "accountAuditAware")
public class SpringBootJpa1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpa1Application.class, args);
	}
}

