package org.example.expert.domain.log.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "log")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String requestType;

    private String requestContent;

    private LocalDateTime timestamp;

    public Log(String requestType, String requestContent){
        this.requestType = requestType;
        this.requestContent = requestContent;
        this.timestamp = LocalDateTime.now();
    }
}
