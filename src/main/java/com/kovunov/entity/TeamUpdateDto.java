package com.kovunov.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamUpdateDto {
    private Long id;
    private  League league;
    private String name;
    private Date timeOfNextGame;
}
