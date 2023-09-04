package com.tonyydl.springbootzeroittdd.data.dto;

import com.tonyydl.springbootzeroittdd.data.po.MemberPo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Long id;
    private String name;
    private Integer age;
    private Float height;

    public MemberPo toPo() {
        return MemberPo.
                builder()
                .id(id)
                .firstName(name.split(",")[0].trim().toString())
                .lastName(name.split(",")[1].trim().toString())
                .age(age)
                .height(height)
                .build();
    }
}
