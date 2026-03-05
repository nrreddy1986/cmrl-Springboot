package com.shellinfo.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "line", uniqueConstraints = {
        @UniqueConstraint(columnNames = "lineId")
})
@Data
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer lineId;
    private String lineName;
    private String lineColor;
    private String lineColorCode;

}
