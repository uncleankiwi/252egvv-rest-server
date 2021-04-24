package com.kovunov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@NamedQuery(name = "Request.findAll", query = "SELECT r FROM Request r")
public class Request implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Lob
    @Column(length = 500)
    private String requestText;

    /**
     * Add JsonIgnore in a many to one part of your mapping
     */
    @ManyToOne
    @JoinColumn(name = "id_player")
    @JsonIgnore
    private Player player;

    public Request(String requestText) {
        this.requestText = requestText;
    }
}
