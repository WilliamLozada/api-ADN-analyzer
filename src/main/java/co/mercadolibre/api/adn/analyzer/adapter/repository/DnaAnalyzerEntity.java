package co.mercadolibre.api.adn.analyzer.adapter.repository;

import co.mercadolibre.api.adn.analyzer.domain.enums.IsMutant;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "DNA_ANALYZER")
public class DnaAnalyzerEntity {

  @Id
  @Column(name = "UUID", updatable = false, nullable = false)
  private String uuid;

  @Basic
  @Column(name = "CREATED_AT")
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "SEQUENCE")
  private String sequence;

  @Column(name = "IS_MUTANT")
  @Enumerated(EnumType.STRING)
  private IsMutant isMutant;
}
