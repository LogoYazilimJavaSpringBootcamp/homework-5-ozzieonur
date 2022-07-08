package com.example.moovienetwork.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {

    private String toSend;

    private String title;

    private String message;
}
