package co.mercadolibre.api.adn.analyzer.services;

import static co.mercadolibre.api.adn.analyzer.Contants.dna5x5Char;
import static co.mercadolibre.api.adn.analyzer.Contants.dna5x5Char2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import co.mercadolibre.api.adn.analyzer.config.MutantProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class ValidatorSequenceServiceTest {

  @Mock private MutantProperties mutantProperties;
  @InjectMocks private ValidatorSequenceService validatorSequenceService;

  @BeforeEach
  void setUp() {
    openMocks(this);
  }

  @Test
  void shouldReturn1InHorizontal() {
    when(mutantProperties.getSizeSequenceToMutant()).thenReturn(4);

    int sequencesFound = validatorSequenceService.horizotal(dna5x5Char);

    assertEquals(1, sequencesFound);

    verify(mutantProperties, times(24)).getSizeSequenceToMutant();

  }

  @Test
  void shouldReturn1InVertical() {
    when(mutantProperties.getSizeSequenceToMutant()).thenReturn(4);

    int sequencesFound = validatorSequenceService.vertical(dna5x5Char);

    assertEquals(1, sequencesFound);

    verify(mutantProperties, times(4)).getSizeSequenceToMutant();
  }

  @Test
  void shouldReturn1InDiagonalDown() {
    when(mutantProperties.getSizeSequenceToMutant()).thenReturn(4);

    int sequencesFound = validatorSequenceService.diagonalDown(dna5x5Char);

    assertEquals(1, sequencesFound);

    verify(mutantProperties, times(16)).getSizeSequenceToMutant();
  }

  @Test
  void shouldReturn1InDiagonalUpward() {
    when(mutantProperties.getSizeSequenceToMutant()).thenReturn(4);

    int sequencesFound = validatorSequenceService.diagonalUpward(dna5x5Char);

    assertEquals(1, sequencesFound);

    verify(mutantProperties, times(16)).getSizeSequenceToMutant();
  }

  @Test
  void shouldReturn0InHorizontal() {
    when(mutantProperties.getSizeSequenceToMutant()).thenReturn(4);

    int sequencesFound = validatorSequenceService.horizotal(dna5x5Char2);

    assertEquals(0, sequencesFound);

    verify(mutantProperties, times(24)).getSizeSequenceToMutant();
  }

  @Test
  void shouldReturn0InVertical() {
    when(mutantProperties.getSizeSequenceToMutant()).thenReturn(4);

    int sequencesFound = validatorSequenceService.vertical(dna5x5Char2);

    assertEquals(0, sequencesFound);

    verify(mutantProperties, times(4)).getSizeSequenceToMutant();
  }

  @Test
  void shouldReturn0InDiagonalDown() {
    when(mutantProperties.getSizeSequenceToMutant()).thenReturn(4);

    int sequencesFound = validatorSequenceService.diagonalDown(dna5x5Char2);

    assertEquals(0, sequencesFound);

    verify(mutantProperties, times(16)).getSizeSequenceToMutant();
  }

  @Test
  void shouldReturn0InDiagonalUpward() {
    when(mutantProperties.getSizeSequenceToMutant()).thenReturn(4);

    int sequencesFound = validatorSequenceService.diagonalUpward(dna5x5Char2);

    assertEquals(0, sequencesFound);

    verify(mutantProperties, times(16)).getSizeSequenceToMutant();
  }
}