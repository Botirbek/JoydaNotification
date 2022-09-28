package com.example.joydanotification.v1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataDTO<T> implements Serializable {
    protected T data;

    protected AppErrorDTO error;

    protected boolean success;

    private Long totalCount;

    public DataDTO(T data) {
        this.data = data;
        this.success = true;
    }

    public DataDTO(AppErrorDTO error) {
        this.error = error;
        this.success = false;
    }

    public DataDTO(T data, Long totalCount) {
        this.data = data;
        this.success = true;
        this.totalCount = totalCount;
    }
}
