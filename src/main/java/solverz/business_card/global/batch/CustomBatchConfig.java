package solverz.business_card.global.batch;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import solverz.business_card.domain.member.entity.Member;
import solverz.business_card.domain.member.repository.MemberRepository;
import solverz.business_card.domain.member.service.MemberService;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
@EnableScheduling
//@EnableTransactionManagement
public class CustomBatchConfig {
    private final MemberRepository memberRepository;
    private final DataSource dataSource;

    public CustomBatchConfig(DataSource dataSource, MemberRepository memberRepository) {
        this.dataSource = dataSource;
        this.memberRepository = memberRepository;
    }

    @Bean
    @Primary
    @Qualifier("transactionManager")
    public JpaTransactionManager JpaTransactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public JobRepository customJobRepository(JpaTransactionManager jpaTransactionManager) throws Exception {
        JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTransactionManager(jpaTransactionManager);
        factoryBean.afterPropertiesSet(); // 설정 후 초기화
        return factoryBean.getObject();
    }

    @Bean
    public Job deleteExpiredMembersJob(JpaTransactionManager jpaTransactionManager, Step deleteExpiredMembersStep) throws Exception {
        return new JobBuilder("deleteExpiredMembersJob", customJobRepository(jpaTransactionManager))
                .start(deleteExpiredMembersStep)
                .build();
    }

    @Bean
    public Step deleteExpiredMembersStep(JpaTransactionManager jpaTransactionManager, MemberService memberService) throws Exception {
        return new StepBuilder("deleteExpiredMembersStep", customJobRepository(jpaTransactionManager))
                .tasklet((contribution, chunkContext) -> { //deleteExpiredMembersTasklet
                    LocalDateTime cutoffDate = LocalDateTime.now().minusDays(30);
                    //LocalDateTime cutoffDate = LocalDateTime.now(); // 테스트용

                    // 만료된 멤버 조회
                    List<Member> expiredMembers = memberService.findExpiredMembers(cutoffDate);

                    // 만료된 멤버 삭제
                    int deletedCount = memberService.deleteExpiredMembers(cutoffDate);
                    System.out.println("# " + deletedCount + " expired members deleted.");

                    if (!expiredMembers.isEmpty()) {
                        for (Member member : expiredMembers) {
                            // 삭제 대상 멤버 정보 출력
                            System.out.println(" - ID: " + member.getMemberToken() + ", Name: " + member.getNickname() + ", email: " +  member.getEmail() + ", emailType: " +  member.getLoginType());
                        }
                    }
                    return RepeatStatus.FINISHED;
                }, jpaTransactionManager)
                .allowStartIfComplete(true)  // 이미 완료된 Step도 재실행
                .build();
    }
}