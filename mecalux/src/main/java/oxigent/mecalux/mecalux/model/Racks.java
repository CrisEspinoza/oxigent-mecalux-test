package oxigent.mecalux.mecalux.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import oxigent.mecalux.mecalux.Enum.RackType;

import java.util.Date;

@Data()
@Entity(name = "racks")
public class Racks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", columnDefinition = "CHAR(36)", nullable = false)
    private String uuid;

    @Enumerated(EnumType.STRING)
    @Column(name = "type",columnDefinition = "CHAR(1)", nullable = false)
    private RackType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouses")
    @JsonIgnore
    private Warehouses warehouse;

}
