package com.dimalka.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.dimalka.projectmanager.commons.model.projectservice.Project;
import com.dimalka.projectmanager.commons.model.taskservice.Task;
import com.dimalka.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @LoadBalanced
    @Bean
    RestTemplate getRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    ;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(int id) {
        Optional<Project> result = projectRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            System.out.println("No project with id:" + id);
            return null;
        }
    }

    @Override
    public List<Project> getAllProjectsByStatus(String status) {
        return projectRepository.findByStatus(status);
    }


    @Override
    public void deleteProjectById(int id) {
        projectRepository.deleteById(id);

    }

    @Override
    public int updateProjectById(String projectName, String projectStatus, int projectId) {
        return projectRepository.updateProjectById(projectName, projectStatus, projectId);
    }

    public List<Project> getFilteredProjects(String status, String projectClient, String deadLine) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Project> getAllProjectsByClient(String projectClient) {
        return projectRepository.findByProjectClient(projectClient);
    }

    public List<Task> getAllTaskOfProject(Integer projectId) {
        return getAllTask(projectId);
    }

    private List<Task> getAllTask(Integer projectId) {
        ResponseEntity<Task[]> tasks = restTemplate.getForEntity("http://task/task?projectId=" + projectId, Task[].class);
        List<Task> taskList = List.of(tasks.getBody());
        return taskList;
    }

}
