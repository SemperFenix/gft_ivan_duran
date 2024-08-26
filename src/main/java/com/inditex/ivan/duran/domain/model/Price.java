package com.inditex.ivan.duran.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "PRICES")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;
    @Column(name = "start_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH.mm.ss")
    private LocalDateTime startDate;
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;
    @Column(name = "price_rate", nullable = false)
    private int priceRate;
    @Column(name = "product_id", nullable = false)
    private int productId;
    @Column(name = "priority", nullable = false)
    private int priority;
    @Column(name = "price", nullable = false)
    private Double priceToApply;
    @Column(name = "currency", length = 3, nullable = false)
    private String currency;

    @Override
    public String toString() {
        return "Price {\n" +
                "\tid=" + id +
                ",\n \tbrandId=" + brand.getCodigo() +
                ",\n \tstartDate=" + startDate +
                ",\n \tendDate=" + endDate +
                ",\n \tpriceList=" + priceRate +
                ",\n \tproductId=" + productId +
                ",\n \tpriority=" + priority +
                ",\n \tpriceToApply=" + priceToApply +
                ",\n \tcurr='" + currency + "'\n" +
                '}';
    }
}
