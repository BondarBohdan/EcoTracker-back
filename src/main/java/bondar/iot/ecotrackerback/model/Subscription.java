package bondar.iot.ecotrackerback.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String deviceId;
    
    @Column(nullable = false)
    private String endpoint;
    
    @Column(nullable = false)
    private String p256dh;
    
    @Column(nullable = false)
    private String auth;
}