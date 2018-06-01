package com.lucaskatayama.winston.servicebeer.beer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Beer {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    private String uid;
    private String name;
    private String photo;
    private String style;
    private String description;


    public Beer(String name, String photo, String style, String decription) {
        this.name = name;
        this.photo = photo;
        this.style = style;
        this.description = decription;
    }
}
