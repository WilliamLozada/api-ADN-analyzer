package co.mercadolibre.api.adn.analyzer.services;

import co.mercadolibre.api.adn.analyzer.config.MutantProperties;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidatorSequenceService {

  private final MutantProperties mutantProperties;

  public int horizotal(final char[][] matrixDNA) {
    int sequencesFound = NumberUtils.INTEGER_ZERO;
    for (int i = 0; i < matrixDNA.length; i++) {
      for (int j = 0; j <= matrixDNA.length - mutantProperties.getSizeSequenceToMutant(); j++) {
        String charValidating = Character.toString(matrixDNA[i][j]);
        if (charValidating.equals(Character.toString(matrixDNA[i][j + 1])) &&
            charValidating.equals(Character.toString(matrixDNA[i][j + 2])) &&
            charValidating.equals(Character.toString(matrixDNA[i][j + 3]))) {
          sequencesFound++;
          if ((j + mutantProperties.getSizeSequenceToMutant()) < matrixDNA.length) {
            j += mutantProperties.getSizeSequenceToMutant();
          } else {
            j = matrixDNA.length - mutantProperties.getSizeSequenceToMutant();
          }
        }
      }
    }
    return sequencesFound;
  }

  public int vertical(final char[][] matrixDNA) {
    int sequencesFound = NumberUtils.INTEGER_ZERO;
    for (int i = 0; i <= matrixDNA.length - mutantProperties.getSizeSequenceToMutant(); i++) {
      for (int j = 0; j < matrixDNA.length; j++) {
        String charValidating = Character.toString(matrixDNA[i][j]);
        if (charValidating.equals(Character.toString(matrixDNA[i + 1][j])) &&
            charValidating.equals(Character.toString(matrixDNA[i + 2][j])) &&
            charValidating.equals(Character.toString(matrixDNA[i + 3][j]))) {
          sequencesFound++;
          if ((i + mutantProperties.getSizeSequenceToMutant()) < matrixDNA.length) {
            i += mutantProperties.getSizeSequenceToMutant();
          } else {
            i = matrixDNA.length - mutantProperties.getSizeSequenceToMutant();
          }
        }
      }
    }
    return sequencesFound;
  }

  public int diagonalDown(final char[][] matrixDNA) {
    int sequencesFound = NumberUtils.INTEGER_ZERO;
    for (int i = 0; i <= matrixDNA.length - mutantProperties.getSizeSequenceToMutant(); i++) {
      for (int j = 0; j <= matrixDNA.length - mutantProperties.getSizeSequenceToMutant(); j++) {
        String charValidating = Character.toString(matrixDNA[i][j]);
        if (charValidating.equals(Character.toString(matrixDNA[i + 1][j + 1])) &&
            charValidating.equals(Character.toString(matrixDNA[i + 2][j + 2])) &&
            charValidating.equals(Character.toString(matrixDNA[i + 3][j + 3]))) {
          sequencesFound++;
        }
      }
    }
    return sequencesFound;
  }

  public int diagonalUpward(final char[][] matrixDNA) {
    int sequencesFound = NumberUtils.INTEGER_ZERO;
    for (int i = matrixDNA.length - 1;
        i > matrixDNA.length - mutantProperties.getSizeSequenceToMutant(); i--) {
      for (int j = 0; j <= matrixDNA.length - mutantProperties.getSizeSequenceToMutant(); j++) {
        String charValidating = Character.toString(matrixDNA[i][j]);
        if (charValidating.equals(Character.toString(matrixDNA[i-1][j + 1])) &&
            charValidating.equals(Character.toString(matrixDNA[i-2][j + 2])) &&
            charValidating.equals(Character.toString(matrixDNA[i-3][j + 3]))) {
          sequencesFound++;
        }
      }
    }
    return sequencesFound;
  }
}
