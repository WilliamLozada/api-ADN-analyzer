package co.mercadolibre.api.adn.analyzer.domain.dto;

import co.mercadolibre.api.adn.analyzer.domain.enums.IsMutant;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class DnaAnalyzer {

  private String uuid;

  private LocalDateTime createdAt;

  private String sequence;

  private IsMutant isMutant;

}
