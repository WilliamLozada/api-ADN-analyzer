package co.mercadolibre.api.adn.analyzer.domain.mapper;

import co.mercadolibre.api.adn.analyzer.adapter.repository.DnaAnalyzerEntity;
import co.mercadolibre.api.adn.analyzer.domain.dto.DnaAnalyzer;
import org.springframework.stereotype.Component;

@Component
public class DnaAnalyzerMapper {

  public DnaAnalyzerEntity dtoToEntity(DnaAnalyzer dnaAnalyzer) {
    return DnaAnalyzerEntity.builder()
        .uuid(dnaAnalyzer.getUuid())
        .createdAt(dnaAnalyzer.getCreatedAt())
        .sequence(dnaAnalyzer.getSequence())
        .isMutant(dnaAnalyzer.getIsMutant())
        .build();
  }

  public DnaAnalyzer entityToDto(DnaAnalyzerEntity dnaAnalyzerEntity) {
    return DnaAnalyzer.builder()
        .uuid(dnaAnalyzerEntity.getUuid())
        .createdAt(dnaAnalyzerEntity.getCreatedAt())
        .sequence(dnaAnalyzerEntity.getSequence())
        .isMutant(dnaAnalyzerEntity.getIsMutant())
        .build();
  }
}
