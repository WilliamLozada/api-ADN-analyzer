package co.mercadolibre.api.adn.analyzer;

public class Contants {

  public static final String [] dna3x3 = {"ACT", "GCA", "ATG"};
  public static final String [] dna5x5CharactersInvalid = {"ATGCGG","CAGTGC","TTAGZT","AGGAGG","CCCCTA","TCACTG"};
  public static final String [] dna5x6 = {"ATGCGGC","CAGTGCA","TTAGGTG","AGGAGGA","CCCCTAC","TCACTGT"};
  public static final String [] dna5x5 = {"ATGCGG","CAGTGC","TTAGGT","AGGAGG","CCCCTA","TCACTG"};
  public static final char [][] dna5x5Char = {{'A','T','G','C','G','G'},{'C','A','G','T','G','C'},
      {'T','T','A','G','G','T'},{'A','G','G','A','G','G'},{'C','C','C','C','T','A'},{'T','C','A','C','T','G'}};
  public static final char [][] dna5x5Char2 = {{'T','T','G','C','G','T'},{'C','A','G','T','G','C'},
      {'T','T','A','G','G','T'},{'A','G','G','A','T','C'},{'A','C','C','C','T','A'},{'T','C','A','C','T','G'}};

}
