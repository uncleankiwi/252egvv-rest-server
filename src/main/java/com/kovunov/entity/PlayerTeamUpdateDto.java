package com.kovunov.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerTeamUpdateDto {
    private Long id;
    private Team team;
}
