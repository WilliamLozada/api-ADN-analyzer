package co.mercadolibre.api.adn.analyzer.services;

import co.mercadolibre.api.adn.analyzer.config.MutantProperties;
import co.mercadolibre.api.adn.analyzer.config.exception.DNAInvalidExceptionBuilder;
import co.mercadolibre.api.adn.analyzer.config.exception.DNALengthExceptionBuilder;
import co.mercadolibre.api.adn.analyzer.domain.Constant;
import co.mercadolibre.api.adn.analyzer.domain.dto.DnaAnalyzer;
import co.mercadolibre.api.adn.analyzer.domain.enums.IsMutant;
import co.mercadolibre.api.adn.analyzer.domain.response.ResponseStats;
import co.mercadolibre.api.adn.analyzer.port.in.DnaAnalyzerUseCase;
import co.mercadolibre.api.adn.analyzer.port.out.DnaAnalyzerPort;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdnAnalyzerService implements DnaAnalyzerUseCase {

  private final MutantProperties mutantProperties;
  private final ValidatorSequenceService validatorSequenceService;
  private final DnaAnalyzerPort dnaAnalyzerPort;

  @Override
  public boolean isMutant(IsMutantCommand isMutantCommand) {

    int sequencesFound = NumberUtils.INTEGER_ZERO;
    if (isMutantCommand.getDna().length < mutantProperties.getSizeSequenceToMutant()) {
      throw DNALengthExceptionBuilder.builder()
          .location(this.getClass().getCanonicalName()).exceptionCode("400")
          .message(String.format(Constant.ERROR_SIZE_SEQUENCE,
              mutantProperties.getSizeSequenceToMutant()))
          .build();
    }
    final char[][] matrixDNA = createMatrixDNA(isMutantCommand);
    sequencesFound += validatorSequenceService.horizotal(matrixDNA);
    if (sequencesFound >= mutantProperties.getNumberMatchingToMutant()) {
      dnaAnalyzerPort.save(DnaAnalyzer.builder().uuid(UUID.randomUUID().toString())
          .sequence(Arrays.toString(isMutantCommand.getDna())).isMutant(IsMutant.YES).build());
      return true;
    }
    sequencesFound += validatorSequenceService.vertical(matrixDNA);
    if (sequencesFound >= mutantProperties.getNumberMatchingToMutant()) {
      dnaAnalyzerPort.save(DnaAnalyzer.builder().uuid(UUID.randomUUID().toString())
          .sequence(Arrays.toString(isMutantCommand.getDna())).isMutant(IsMutant.YES).build());
      return true;
    }
    sequencesFound += validatorSequenceService.diagonalDown(matrixDNA);
    if (sequencesFound >= mutantProperties.getNumberMatchingToMutant()) {
      dnaAnalyzerPort.save(DnaAnalyzer.builder().uuid(UUID.randomUUID().toString())
          .sequence(Arrays.toString(isMutantCommand.getDna())).isMutant(IsMutant.YES).build());
      return true;
    }
    sequencesFound += validatorSequenceService.diagonalUpward(matrixDNA);
    if (sequencesFound >= mutantProperties.getNumberMatchingToMutant()) {
      dnaAnalyzerPort.save(DnaAnalyzer.builder().uuid(UUID.randomUUID().toString())
          .sequence(Arrays.toString(isMutantCommand.getDna())).isMutant(IsMutant.YES).build());
      return true;
    }
    dnaAnalyzerPort.save(DnaAnalyzer.builder().uuid(UUID.randomUUID().toString())
        .sequence(Arrays.toString(isMutantCommand.getDna())).isMutant(IsMutant.NO).build());
    return false;
  }

  @Override
  public ResponseStats getStats() {
    long countMutantDna = dnaAnalyzerPort.getCountMutantDna();
    long countHumanDna = dnaAnalyzerPort.getCountHumanDna();

    return ResponseStats.builder()
        .countMutantDna(countMutantDna)
        .countHumanDna(countHumanDna)
        .ratio((((double) countMutantDna) / (countHumanDna == 0L ? 1L : countHumanDna)))
        .build();
  }

  private char[][] createMatrixDNA(IsMutantCommand isMutantCommand) {
    final int matrixLen = isMutantCommand.getDna().length;
    char[][] matrixDNA = new char[matrixLen][matrixLen];
    final IntStream rankLen = IntStream.range(NumberUtils.INTEGER_ZERO, matrixLen);
    rankLen.forEach(i -> {
      final String dnaRow = isMutantCommand.getDna()[i];
      validateMatrixHomogeneity(dnaRow, matrixLen);
      matrixDNA[i] = dnaRow.toCharArray();
    });
    return matrixDNA;
  }

  private void validateMatrixHomogeneity(final String dnaRow, final int matrixLen) {
    if (dnaRow.length() != matrixLen) {
      throw DNALengthExceptionBuilder.builder()
          .location(this.getClass().getCanonicalName()).exceptionCode("400")
          .message(String.format(Constant.ERROR_HOMOGENEOUS_MATRIX, dnaRow.length(), matrixLen))
          .build();
    } else if (!mutantProperties.getDnaCharacterPattern().matcher(dnaRow).matches()) {
      throw DNAInvalidExceptionBuilder.builder()
          .location(this.getClass().getCanonicalName()).exceptionCode("400")
          .message(Constant.ERROR_CHARACTERS)
          .build();
    }
  }
}
