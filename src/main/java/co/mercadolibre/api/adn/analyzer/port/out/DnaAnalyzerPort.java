package co.mercadolibre.api.adn.analyzer.port.out;

import co.mercadolibre.api.adn.analyzer.domain.dto.DnaAnalyzer;

public interface DnaAnalyzerPort {

  void save(DnaAnalyzer dnaAnalyzer);

  long getCountMutantDna();

  long getCountHumanDna();

}
