package com.petter77.financetracker.dto.response;
import lombok.Data;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
