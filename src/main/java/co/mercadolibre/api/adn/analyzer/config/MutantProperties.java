package co.mercadolibre.api.adn.analyzer.config;

import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mutant", ignoreUnknownFields = false)
@Setter
@Getter
public class MutantProperties {

  private static final int SEQUENCE_SIZE = 4;
  private static final int MATCHING_SEQUENCES = 2;
  private static final Pattern DNA_CHARACTER_PATTERN = Pattern.compile("[ATCG]+", Pattern.CASE_INSENSITIVE);

  private int sizeSequenceToMutant = SEQUENCE_SIZE;
  private int numberMatchingToMutant = MATCHING_SEQUENCES;
  private Pattern dnaCharacterPattern = DNA_CHARACTER_PATTERN;

}
