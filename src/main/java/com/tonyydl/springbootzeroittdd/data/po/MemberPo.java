package com.tonyydl.springbootzeroittdd.data.po;

import com.tonyydl.springbootzeroittdd.data.dto.MemberDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "member")
public class MemberPo implements Serializable {

    @Serial
    private static final long serialVersionUID = 6086029823229922949L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    private Float height;

    public MemberDto toDto() {
        return MemberDto
                .builder()
                .id(id)
                .name(firstName + ", " + lastName)
                .age(age)
                .height(height)
                .build();
    }
}
