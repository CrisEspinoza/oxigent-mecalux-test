package oxigent.mecalux.mecalux.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import oxigent.mecalux.mecalux.Enum.FamilyType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

@Data()
@Entity(name = "warehouses")
public class Warehouses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", columnDefinition = "CHAR(36)", nullable = false)
    private String uuid;

    @Column(name = "client", columnDefinition = "CHAR(50)", nullable = false)
    private String client;

    @Enumerated(EnumType.STRING)
    @Column(name = "family",columnDefinition = "CHAR(3)", nullable = false)
    private FamilyType family;

    @Column(name = "size", nullable = false)
    @Min(1)
    @Max(99)
    private Integer size;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    @Column(name = "update_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    @OneToMany(mappedBy = "warehouse", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Racks> racks;

    @PrePersist
    protected void onCreate() {
        startDate = new Date();
        updateDate = new Date();
        endDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateDate = new Date();
    }
}
