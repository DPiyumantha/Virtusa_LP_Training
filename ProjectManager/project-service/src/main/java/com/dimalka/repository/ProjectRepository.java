package com.dimalka.repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.dimalka.projectmanager.commons.model.projectservice.Project;


public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findByStatus(String status);

    List<Project> findByProjectClient(String projectClient);

    @Modifying
    @Transactional
    @Query("update Project p set p.projectName=?1 , p.status=?2 where projectId=?3")
    public int updateProjectById(String projectName, String projectStatus, int projectId);

//	
//	@Query("select all from  Project where ")
//	public int getFilteredProjects(String status, String projectClient, Date deadLine);

}
