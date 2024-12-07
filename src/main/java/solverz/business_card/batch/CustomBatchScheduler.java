package solverz.business_card.batch;
import org.springframework.batch.core.JobParameters;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.Job;
import org.springframework.stereotype.Component;

@Component
public class CustomBatchScheduler {
    private final JobLauncher jobLauncher;
    private final Job deleteExpiredMembersJob;

    public CustomBatchScheduler(JobLauncher jobLauncher, Job deleteExpiredMembersJob) {
        this.jobLauncher = jobLauncher;
        this.deleteExpiredMembersJob = deleteExpiredMembersJob;
    }

    @Scheduled(cron = "0 * * * * *") // 1분마다 실행 (테스트)
    //@Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
    public void runDeleteExpiredMembersJob() throws Exception {
        jobLauncher.run(deleteExpiredMembersJob, new JobParameters());
    }
}