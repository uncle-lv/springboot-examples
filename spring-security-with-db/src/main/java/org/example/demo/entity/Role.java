package org.example.demo.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Role {
    private Long id;
    private String name;
}
