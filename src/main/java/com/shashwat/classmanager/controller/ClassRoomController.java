package com.shashwat.classmanager.controller;

import com.shashwat.classmanager.model.ClassRoom;
import com.shashwat.classmanager.service.ClassRoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classrooms")
public class ClassRoomController {

    private final ClassRoomService classRoomService;

    public ClassRoomController(ClassRoomService classRoomService) {
        this.classRoomService = classRoomService;
    }

    @GetMapping
    public List<ClassRoom> getAllClassRooms() {
        return classRoomService.getAllClassRooms();
    }

    // Notice: instead of @RequestBody with a full JSON object, we're using
    // @RequestParam here — these come from the URL itself, like:
    // POST /classrooms?className=Math101&facultyId=2
    // This is a deliberate choice for this endpoint since we just need two
    // simple values, not a full object structure.
    @PostMapping
    public ClassRoom createClassRoom(@RequestParam String className,
                                     @RequestParam Long facultyId) {
        return classRoomService.createClassRoom(className, facultyId);
    }

    // Path variables — values embedded directly in the URL path itself.
    // e.g. POST /classrooms/1/enroll/3  → classRoomId=1, studentId=3
    @PostMapping("/{classRoomId}/enroll/{studentId}")
    public ClassRoom enrollStudent(@PathVariable Long classRoomId,
                                   @PathVariable Long studentId) {
        return classRoomService.enrollStudent(classRoomId, studentId);
    }
}