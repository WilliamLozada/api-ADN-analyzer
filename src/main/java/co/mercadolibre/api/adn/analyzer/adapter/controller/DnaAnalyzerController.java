package co.mercadolibre.api.adn.analyzer.adapter.controller;

import co.mercadolibre.api.adn.analyzer.config.exception.DNAInvalidException;
import co.mercadolibre.api.adn.analyzer.config.exception.DNALengthException;
import co.mercadolibre.api.adn.analyzer.domain.request.Request;
import co.mercadolibre.api.adn.analyzer.domain.response.Response;
import co.mercadolibre.api.adn.analyzer.domain.response.ResponseStats;
import co.mercadolibre.api.adn.analyzer.port.in.DnaAnalyzerUseCase;
import co.mercadolibre.api.adn.analyzer.port.in.DnaAnalyzerUseCase.IsMutantCommand;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class DnaAnalyzerController {

  private final DnaAnalyzerUseCase adnAnalyzerUseCase;

  @PostMapping("/mutant/")
  public Mono<ResponseEntity<Response>> checkMutant(
      @Valid @RequestBody Request request) {

    try {
      IsMutantCommand isMutantCommand = IsMutantCommand.builder().dna(request.getDna()).build();

      if (adnAnalyzerUseCase.isMutant(isMutantCommand)) {
        return Mono.just(ResponseEntity.status(HttpStatus.OK)
            .body(Response.builder().message("Is a mutant").build()));
      } else {
        return Mono.just(ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(Response.builder().message("Is not a mutant").build()));
      }
    } catch (DNAInvalidException | DNALengthException exc) {
      return Mono.just((ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(Response.builder().message(exc.getMessage()).build())));
    }
  }

  @GetMapping("/stats")
  public Mono<ResponseEntity<ResponseStats>> statsMutant() {

    try {
      return Mono.just(ResponseEntity.ok().body(adnAnalyzerUseCase.getStats()));

    } catch (Exception exc) {
      return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(ResponseStats.builder().message(exc.getMessage()).build()));
    }
  }
}
