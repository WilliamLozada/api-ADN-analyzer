package co.mercadolibre.api.adn.analyzer.port.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

public interface AdnAnalyzerUseCase {

  boolean isMutant(IsMutantCommand isMutantCommand) ;

  @Builder(toBuilder = true)
  @Value
  @EqualsAndHashCode(callSuper = false)
  @AllArgsConstructor
  class IsMutantCommand {
    String [] dna;
  }

}
