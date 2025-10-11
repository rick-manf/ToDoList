package com.project.toDoList.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;



@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column(name = "completed", nullable = false)
    private boolean completed = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;




//    @Override
//    public String toString() {
//        return "User{" +
//                "title='" + title + '\'' +
//                ", description='" + description + '\'' +
//                ", dueDate='" + dueDate + '\'' +
//                ", completed=" + completed +
//                '}';
//    }
}
