package com.jobhub.controller;


import com.jobhub.entity.Job;
import com.jobhub.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }



    @PostMapping
    public Job addJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }
}
