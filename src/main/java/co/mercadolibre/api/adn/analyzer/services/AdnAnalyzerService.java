package co.mercadolibre.api.adn.analyzer.services;

import co.mercadolibre.api.adn.analyzer.config.MutantProperties;
import co.mercadolibre.api.adn.analyzer.config.exception.DNAInvalidExceptionBuilder;
import co.mercadolibre.api.adn.analyzer.config.exception.DNALengthExceptionBuilder;
import co.mercadolibre.api.adn.analyzer.domain.dto.Constant;
import co.mercadolibre.api.adn.analyzer.port.in.AdnAnalyzerUseCase;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdnAnalyzerService implements AdnAnalyzerUseCase {

  private static final Logger LOGGER = LoggerFactory.getLogger(AdnAnalyzerService.class);

  private final MutantProperties mutantProperties;
  private final ValidatorSequenceService validatorSequenceService;

  @Override
  public boolean isMutant(IsMutantCommand isMutantCommand) {

    int sequencesFound = NumberUtils.INTEGER_ZERO;
    if (isMutantCommand.getDna().length < mutantProperties.getSizeSequenceToMutant()) {
      LOGGER.error(Constant.ERROR_SIZE_SEQUENCE, mutantProperties.getSizeSequenceToMutant());
    }
    final char[][] matrixDNA = createMatrixDNA(isMutantCommand);
    sequencesFound += validatorSequenceService.horizotal(matrixDNA);
    if (sequencesFound >= mutantProperties.getNumberMatchingToMutant()) {
      return true;
    }
    sequencesFound += validatorSequenceService.vertical(matrixDNA);
    if (sequencesFound >= mutantProperties.getNumberMatchingToMutant()) {
      return true;
    }
    sequencesFound += validatorSequenceService.diagonalDown(matrixDNA);
    if (sequencesFound >= mutantProperties.getNumberMatchingToMutant()) {
      return true;
    }
    sequencesFound += validatorSequenceService.diagonalUpward(matrixDNA);
    return sequencesFound >= mutantProperties.getNumberMatchingToMutant();
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
