package co.mercadolibre.api.adn.analyzer.adapter.repository;

import co.mercadolibre.api.adn.analyzer.adapter.repository.DnaAnalyzerRepository.NativeCountMutant;
import co.mercadolibre.api.adn.analyzer.adapter.repository.DnaAnalyzerRepository.NativeCountNotMutant;
import co.mercadolibre.api.adn.analyzer.domain.dto.DnaAnalyzer;
import co.mercadolibre.api.adn.analyzer.domain.mapper.DnaAnalyzerMapper;
import co.mercadolibre.api.adn.analyzer.port.out.DnaAnalyzerPort;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DnaAnalyzerAdapter implements DnaAnalyzerPort {

  private final DnaAnalyzerMapper dnaAnalyzerMapper;
  private final DnaAnalyzerRepository dnaAnalyzerRepository;

  @Override
  public void save(DnaAnalyzer dnaAnalyzer) {
    dnaAnalyzerRepository.save(dnaAnalyzerMapper.dtoToEntity(dnaAnalyzer));
  }

  @Override
  public long getCountMutantDna() {
    Optional<NativeCountMutant> nativeCountMutant =
        Optional.of(dnaAnalyzerRepository.countRegisterForMutant());
    return nativeCountMutant.get().getCountMutant();
  }

  @Override
  public long getCountHumanDna() {
    Optional<NativeCountNotMutant> nativeCountNotMutant =
        Optional.of(dnaAnalyzerRepository.countRegisterForNotMutant());
    return nativeCountNotMutant.get().getCountNotMutant();
  }
}
