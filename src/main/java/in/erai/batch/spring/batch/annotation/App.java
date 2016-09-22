package in.erai.batch.spring.batch.annotation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Hello world!
 *
 */

public class App 
{
    public static void main( String[] args )
    {
    	String[] springConfig  =
    		{	
    			"application-context.xml"
    			
    		};

    	ApplicationContext context =
    		new ClassPathXmlApplicationContext(springConfig);
    	JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
    	Job job = (Job) context.getBean("importUserJob");
    	JobParameter jobParameter = new JobParameter(System.currentTimeMillis());
    	Map<String, JobParameter> jobParametersMap = new HashMap<String, JobParameter>();
        jobParametersMap.put("time", jobParameter);
        //jobParametersMap.put("input.file", new JobParameter("file:src/test/resources/input/input.txt"));
        //jobParametersMap.put("output.file", new JobParameter("file:target/test-outputs/simple/output.txt"));
    	try {

    		JobExecution execution = jobLauncher.run(job,new JobParameters(jobParametersMap));
    		System.out.println("Exit Status : " + execution.getStatus());
    		System.out.println("Job Id : " + execution.getJobId());

    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    	System.out.println("Done");
    }
}
