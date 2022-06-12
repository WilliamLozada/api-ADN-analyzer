package co.mercadolibre.api.adn.analyzer.services;

import static co.mercadolibre.api.adn.analyzer.Contants.dna3x3;
import static co.mercadolibre.api.adn.analyzer.Contants.dna5x5;
import static co.mercadolibre.api.adn.analyzer.Contants.dna5x5CharactersInvalid;
import static co.mercadolibre.api.adn.analyzer.Contants.dna5x6;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import co.mercadolibre.api.adn.analyzer.config.MutantProperties;
import co.mercadolibre.api.adn.analyzer.config.exception.DNAInvalidException;
import co.mercadolibre.api.adn.analyzer.config.exception.DNALengthException;
import co.mercadolibre.api.adn.analyzer.domain.Constant;
import co.mercadolibre.api.adn.analyzer.domain.dto.DnaAnalyzer;
import co.mercadolibre.api.adn.analyzer.domain.response.ResponseStats;
import co.mercadolibre.api.adn.analyzer.port.in.DnaAnalyzerUseCase.IsMutantCommand;
import co.mercadolibre.api.adn.analyzer.port.out.DnaAnalyzerPort;
import java.util.regex.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class AdnAnalyzerServiceTest {

  @Mock
  private MutantProperties mutantProperties;
  @Mock
  private ValidatorSequenceService validatorSequenceService;
  @Mock
  private DnaAnalyzerPort dnaAnalyzerPort;
  @InjectMocks
  AdnAnalyzerService adnAnalyzerService;

  @BeforeEach
  void setUp() {
    openMocks(this);
  }

  @Test
  void shouldGenerateErrorForMatrixSizeLessThanDefaultSize() {
    IsMutantCommand isMutantCommand = IsMutantCommand.builder().dna(dna3x3).build();
    when(mutantProperties.getSizeSequenceToMutant()).thenReturn(4);

    DNALengthException dnaLengthException = assertThrows(DNALengthException.class,
        () -> adnAnalyzerService.isMutant(isMutantCommand));

    assertNotNull(dnaLengthException);
    assertEquals(
        String.format(Constant.ERROR_SIZE_SEQUENCE, mutantProperties.getSizeSequenceToMutant()),
        dnaLengthException.getMessage());

    verify(mutantProperties, times(3)).getSizeSequenceToMutant();
    verify(validatorSequenceService, times(0)).horizotal(any());
    verify(validatorSequenceService, times(0)).vertical(any());
    verify(validatorSequenceService, times(0)).diagonalDown(any());
    verify(validatorSequenceService, times(0)).diagonalUpward(any());
    verify(dnaAnalyzerPort, times(0)).save(any(DnaAnalyzer.class));
  }

  @Test
  void shouldGenerateErrorForCharactersDifferentFromDefaultOnes() {
    IsMutantCommand isMutantCommand = IsMutantCommand.builder().dna(dna5x5CharactersInvalid)
        .build();
    when(mutantProperties.getSizeSequenceToMutant()).thenReturn(4);
    when(mutantProperties.getDnaCharacterPattern()).thenReturn(
        Pattern.compile("[ATCG]+", Pattern.CASE_INSENSITIVE));

    DNAInvalidException dnaInvalidException = assertThrows(DNAInvalidException.class,
        () -> adnAnalyzerService.isMutant(isMutantCommand));

    assertNotNull(dnaInvalidException);
    assertEquals(Constant.ERROR_CHARACTERS, dnaInvalidException.getMessage());

    verify(mutantProperties, times(1)).getSizeSequenceToMutant();
    verify(validatorSequenceService, times(0)).horizotal(any());
    verify(validatorSequenceService, times(0)).vertical(any());
    verify(validatorSequenceService, times(0)).diagonalDown(any());
    verify(validatorSequenceService, times(0)).diagonalUpward(any());
    verify(dnaAnalyzerPort, times(0)).save(any(DnaAnalyzer.class));
  }

  @Test
  void shouldGenerateErrorForNonSquareMatrixNxN() {
    IsMutantCommand isMutantCommand = IsMutantCommand.builder().dna(dna5x6)
        .build();
    when(mutantProperties.getSizeSequenceToMutant()).thenReturn(4);
    when(mutantProperties.getDnaCharacterPattern()).thenReturn(
        Pattern.compile("[ATCG]+", Pattern.CASE_INSENSITIVE));

    DNALengthException dnaLengthException = assertThrows(DNALengthException.class,
        () -> adnAnalyzerService.isMutant(isMutantCommand));

    assertNotNull(dnaLengthException);
    assertEquals(
        String.format(Constant.ERROR_HOMOGENEOUS_MATRIX, dna5x6[0].length(), dna5x6.length),
        dnaLengthException.getMessage());

    verify(mutantProperties, times(1)).getSizeSequenceToMutant();
    verify(validatorSequenceService, times(0)).horizotal(any());
    verify(validatorSequenceService, times(0)).vertical(any());
    verify(validatorSequenceService, times(0)).diagonalDown(any());
    verify(validatorSequenceService, times(0)).diagonalUpward(any());
    verify(dnaAnalyzerPort, times(0)).save(any(DnaAnalyzer.class));
  }

  @Test
  void shouldReturnIsMutantInTrue(){
    IsMutantCommand isMutantCommand = IsMutantCommand.builder().dna(dna5x5)
        .build();
    when(mutantProperties.getSizeSequenceToMutant()).thenReturn(4);
    when(mutantProperties.getDnaCharacterPattern()).thenReturn(
        Pattern.compile("[ATCG]+", Pattern.CASE_INSENSITIVE));
    when(mutantProperties.getNumberMatchingToMutant()).thenReturn(2);
    when(validatorSequenceService.horizotal(any())).thenReturn(1);
    when(validatorSequenceService.vertical(any())).thenReturn(0);
    when(validatorSequenceService.diagonalDown(any())).thenReturn(0);
    when(validatorSequenceService.diagonalUpward(any())).thenReturn(1);

    boolean isMutant = adnAnalyzerService.isMutant(isMutantCommand);

    assertTrue(isMutant);

    verify(mutantProperties, times(1)).getSizeSequenceToMutant();
    verify(validatorSequenceService, times(1)).horizotal(any());
    verify(validatorSequenceService, times(1)).vertical(any());
    verify(validatorSequenceService, times(1)).diagonalDown(any());
    verify(validatorSequenceService, times(1)).diagonalUpward(any());
    verify(dnaAnalyzerPort, times(1)).save(any(DnaAnalyzer.class));
  }

  @Test
  void shouldReturnIsMutantInFalse(){
    IsMutantCommand isMutantCommand = IsMutantCommand.builder().dna(dna5x5)
        .build();
    when(mutantProperties.getSizeSequenceToMutant()).thenReturn(4);
    when(mutantProperties.getDnaCharacterPattern()).thenReturn(
        Pattern.compile("[ATCG]+", Pattern.CASE_INSENSITIVE));
    when(mutantProperties.getNumberMatchingToMutant()).thenReturn(2);
    when(validatorSequenceService.horizotal(any())).thenReturn(0);
    when(validatorSequenceService.vertical(any())).thenReturn(0);
    when(validatorSequenceService.diagonalDown(any())).thenReturn(0);
    when(validatorSequenceService.diagonalUpward(any())).thenReturn(1);

    boolean isMutant = adnAnalyzerService.isMutant(isMutantCommand);

    assertFalse(isMutant);

    verify(mutantProperties, times(1)).getSizeSequenceToMutant();
    verify(validatorSequenceService, times(1)).horizotal(any());
    verify(validatorSequenceService, times(1)).vertical(any());
    verify(validatorSequenceService, times(1)).diagonalDown(any());
    verify(validatorSequenceService, times(1)).diagonalUpward(any());
    verify(dnaAnalyzerPort, times(1)).save(any(DnaAnalyzer.class));
  }

  @Test
  void shouldReturnIsMutantInTrueHorizontal(){
    IsMutantCommand isMutantCommand = IsMutantCommand.builder().dna(dna5x5)
        .build();
    when(mutantProperties.getSizeSequenceToMutant()).thenReturn(4);
    when(mutantProperties.getDnaCharacterPattern()).thenReturn(
        Pattern.compile("[ATCG]+", Pattern.CASE_INSENSITIVE));
    when(mutantProperties.getNumberMatchingToMutant()).thenReturn(2);
    when(validatorSequenceService.horizotal(any())).thenReturn(2);
    when(validatorSequenceService.vertical(any())).thenReturn(0);
    when(validatorSequenceService.diagonalDown(any())).thenReturn(0);
    when(validatorSequenceService.diagonalUpward(any())).thenReturn(0);

    boolean isMutant = adnAnalyzerService.isMutant(isMutantCommand);

    assertTrue(isMutant);

    verify(mutantProperties, times(1)).getSizeSequenceToMutant();
    verify(validatorSequenceService, times(1)).horizotal(any());
    verify(validatorSequenceService, times(0)).vertical(any());
    verify(validatorSequenceService, times(0)).diagonalDown(any());
    verify(validatorSequenceService, times(0)).diagonalUpward(any());
    verify(dnaAnalyzerPort, times(1)).save(any(DnaAnalyzer.class));
  }

  @Test
  void shouldReturnIsMutantInTrueDiagonalDown(){
    IsMutantCommand isMutantCommand = IsMutantCommand.builder().dna(dna5x5)
        .build();
    when(mutantProperties.getSizeSequenceToMutant()).thenReturn(4);
    when(mutantProperties.getDnaCharacterPattern()).thenReturn(
        Pattern.compile("[ATCG]+", Pattern.CASE_INSENSITIVE));
    when(mutantProperties.getNumberMatchingToMutant()).thenReturn(2);
    when(validatorSequenceService.horizotal(any())).thenReturn(1);
    when(validatorSequenceService.vertical(any())).thenReturn(0);
    when(validatorSequenceService.diagonalDown(any())).thenReturn(1);
    when(validatorSequenceService.diagonalUpward(any())).thenReturn(0);

    boolean isMutant = adnAnalyzerService.isMutant(isMutantCommand);

    assertTrue(isMutant);

    verify(mutantProperties, times(1)).getSizeSequenceToMutant();
    verify(validatorSequenceService, times(1)).horizotal(any());
    verify(validatorSequenceService, times(1)).vertical(any());
    verify(validatorSequenceService, times(1)).diagonalDown(any());
    verify(validatorSequenceService, times(0)).diagonalUpward(any());
    verify(dnaAnalyzerPort, times(1)).save(any(DnaAnalyzer.class));
  }

  @Test
  void shouldReturnIsMutantInTrueVertical(){
    IsMutantCommand isMutantCommand = IsMutantCommand.builder().dna(dna5x5)
        .build();
    when(mutantProperties.getSizeSequenceToMutant()).thenReturn(4);
    when(mutantProperties.getDnaCharacterPattern()).thenReturn(
        Pattern.compile("[ATCG]+", Pattern.CASE_INSENSITIVE));
    when(mutantProperties.getNumberMatchingToMutant()).thenReturn(2);
    when(validatorSequenceService.horizotal(any())).thenReturn(0);
    when(validatorSequenceService.vertical(any())).thenReturn(2);
    when(validatorSequenceService.diagonalDown(any())).thenReturn(0);
    when(validatorSequenceService.diagonalUpward(any())).thenReturn(0);

    boolean isMutant = adnAnalyzerService.isMutant(isMutantCommand);

    assertTrue(isMutant);

    verify(mutantProperties, times(1)).getSizeSequenceToMutant();
    verify(validatorSequenceService, times(1)).horizotal(any());
    verify(validatorSequenceService, times(1)).vertical(any());
    verify(validatorSequenceService, times(0)).diagonalDown(any());
    verify(validatorSequenceService, times(0)).diagonalUpward(any());
    verify(dnaAnalyzerPort, times(1)).save(any(DnaAnalyzer.class));
  }

  @Test
  void shouldReturnStats() {
    when(dnaAnalyzerPort.getCountHumanDna()).thenReturn(0L);
    when(dnaAnalyzerPort.getCountMutantDna()).thenReturn(40L);

    ResponseStats responseStats = adnAnalyzerService.getStats();

    assertNotNull(responseStats);
    assertEquals(40L, responseStats.getCountMutantDna());
    assertEquals(0L, responseStats.getCountHumanDna());
    assertEquals(40.0, responseStats.getRatio());

    verify(dnaAnalyzerPort, times(1)).getCountHumanDna();
    verify(dnaAnalyzerPort, times(1)).getCountMutantDna();
  }

  @Test
  void shouldReturnStatsWithRecordsHumans() {
    when(dnaAnalyzerPort.getCountHumanDna()).thenReturn(100L);
    when(dnaAnalyzerPort.getCountMutantDna()).thenReturn(40L);

    ResponseStats responseStats = adnAnalyzerService.getStats();

    assertNotNull(responseStats);
    assertEquals(40L, responseStats.getCountMutantDna());
    assertEquals(100L, responseStats.getCountHumanDna());
    assertEquals(0.4, responseStats.getRatio());

    verify(dnaAnalyzerPort, times(1)).getCountHumanDna();
    verify(dnaAnalyzerPort, times(1)).getCountMutantDna();
  }
}