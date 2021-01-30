package com.challenge.simpleApi.domains.tasks.models;


import com.challenge.simpleApi.domains.users.models.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Tasks")
public class Tasks {


    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String status;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private Users user_id;

    private LocalDateTime due_date;

    private LocalDateTime finished_date;
}
