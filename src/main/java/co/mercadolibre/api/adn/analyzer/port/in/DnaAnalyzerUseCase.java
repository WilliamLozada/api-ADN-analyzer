package co.mercadolibre.api.adn.analyzer.port.in;

import co.mercadolibre.api.adn.analyzer.domain.response.ResponseStats;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

public interface DnaAnalyzerUseCase {

  boolean isMutant(IsMutantCommand isMutantCommand);

  ResponseStats getStats();

  @Builder(toBuilder = true)
  @Value
  @EqualsAndHashCode(callSuper = false)
  @AllArgsConstructor
  class IsMutantCommand {
    String [] dna;
  }

}
