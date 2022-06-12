package co.mercadolibre.api.adn.analyzer.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DnaAnalyzerRepository extends JpaRepository<DnaAnalyzerEntity, String> {

  @Transactional
  @Query(value ="SELECT COUNT(*) AS CountMutant FROM dna_analyzer DA WHERE DA.is_mutant = 'YES'",
      nativeQuery =true)
  NativeCountMutant countRegisterForMutant();

  interface NativeCountMutant {

    Long getCountMutant();

  }

  @Transactional
  @Query(value ="SELECT COUNT(*) AS CountNotMutant FROM dna_analyzer DA WHERE DA.is_mutant = 'NO'",
      nativeQuery =true)
  NativeCountNotMutant countRegisterForNotMutant();

  interface NativeCountNotMutant {

    Long getCountNotMutant();

  }

}
