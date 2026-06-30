package com.shashwat.classmanager.repository;

import com.shashwat.classmanager.model.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
}