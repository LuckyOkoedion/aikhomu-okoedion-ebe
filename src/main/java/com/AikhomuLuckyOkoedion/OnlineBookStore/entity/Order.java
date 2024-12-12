package com.AikhomuLuckyOkoedion.OnlineBookStore.entity;


import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "`Order`")
public class Order {

    public enum PaymentMethod {
        WEB, USSD, TRANSFER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Lob
    private String items; // JSON representation of the cart contents

    @Version
    private Long version;

    private boolean success;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Order{" +
            "id=" + id +
            ", userId='" + userId + '\'' +
            ", items='" + items + '\'' +
            ", paymentMethod=" + paymentMethod +
            ", success=" + success +
            '}';
    }


}

