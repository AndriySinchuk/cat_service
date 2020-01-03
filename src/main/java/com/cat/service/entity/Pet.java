package com.cat.service.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
@Table(name = "pets")
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Pet implements Serializable {

    private static final Integer serialVersionUID = 1;

    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "pet_name", unique = true)
    private String petName;

    @Column(name = "breed_id")
    private Long breedId;

    @Column(name = "customer_id")
    private Long customer_Id;

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "date")
    @ApiModelProperty(hidden = true)
    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date createdAt = new Date();

    @Column(name = "diagnosis")
    private String diagnosis;

    @Override
    public String toString() {
        return "Pet [id = " + id + ", " +
                "pet_name = " + petName + ", " +
                "breed_id = " + breedId + ", " +
                "ownerId = " + customer_Id + ", " +
                "doctorId = " + doctorId + " ," +
                "date = " + createdAt + "," +
                "diagnosis = " + diagnosis + "]";
    }

}
