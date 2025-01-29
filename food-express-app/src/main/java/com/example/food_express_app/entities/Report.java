package com.example.food_express_app.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;

import com.example.food_express_app.entities.Order.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalRevenue;
    private int ordersServed;

    @OneToOne
    private MenuItem bestPreferredMenuItem;

    @ElementCollection
    @MapKeyColumn(name = "status")
    @Column(name = "count")
    private Map<Status, Long> orderStatusCount;
}