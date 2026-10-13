package com.jobhub.controller;


import com.jobhub.dto.JobRequest;
import com.jobhub.entity.Job;
import com.jobhub.exception.JobNotFoundException;
import com.jobhub.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }



    @PostMapping
    public Job addJob(@Valid @RequestBody JobRequest request) {
        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setLocation(request.getLocation());
        job.setSalary(request.getSalary());
        job.setJobType(request.getJobType());

        return jobService.createJob(job);
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Long id) {
        return jobService.getJobById(id).orElseThrow(()->new JobNotFoundException("job not found with id:"+id ));
    }

    @PutMapping("/{id}")
    public Job updateJob(@PathVariable Long id , @RequestBody Job updatejob) {
        return jobService.updateJob(id, updatejob);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
        return ResponseEntity.ok().build();
    }

}
