package com.jobhub.service;

import com.jobhub.entity.Job;
import com.jobhub.exception.JobNotFoundException;
import com.jobhub.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job createJob(Job job){
        return jobRepository.save(job);
    }

public List<Job> getAllJobs(){
        return jobRepository.findAll();
}


public Optional<Job> getJobById(Long id){
        return jobRepository.findById(id);
}


    public Job updateJob(Long id, Job updatedJob) {

        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() ->
                        new JobNotFoundException("Job not found with id: " + id));

        existingJob.setTitle(updatedJob.getTitle());
        existingJob.setDescription(updatedJob.getDescription());
        existingJob.setLocation(updatedJob.getLocation());
        existingJob.setSalary(updatedJob.getSalary());
        existingJob.setJobType(updatedJob.getJobType());

        return jobRepository.save(existingJob);
    }


    public void deleteJob(Long id) {
        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() ->
                        new JobNotFoundException("Job not found with id: " + id));

        jobRepository.delete(existingJob);
    }
}
