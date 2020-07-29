package com.sailbright.airclean.config;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.sailbright.airclean.job.Q3Job;
import com.sailbright.airclean.job.Q4Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 全局作业配置中心
 */
@Configuration
public class JobConfig {

    @Autowired
    private ZookeeperRegistryCenter regCenter;

    @Autowired
    private Q3Job q3Job;

    @Autowired
    private Q4Job q4Job;

    @Resource
    private JobEventConfiguration jobEventConfiguration;

    @Bean(initMethod = "init")
    public JobScheduler q3JobScheduler() {
        JobCoreConfiguration coreConfig = JobCoreConfiguration.newBuilder("Q3", "0/5 * * * * ?", 3).build();
        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(coreConfig, Q3Job.class.getCanonicalName());
        return new SpringJobScheduler(q3Job, regCenter, LiteJobConfiguration.newBuilder(simpleJobConfig).build(), jobEventConfiguration);
    }

//    @Bean(initMethod = "init")
    public JobScheduler q4JobScheduler() {
        JobCoreConfiguration coreConfig = JobCoreConfiguration.newBuilder("Q4", "0/5 * * * * ?", 3).build();
        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(coreConfig, Q4Job.class.getCanonicalName());
        return new SpringJobScheduler(q4Job, regCenter, LiteJobConfiguration.newBuilder(simpleJobConfig).build(), jobEventConfiguration);
    }

}