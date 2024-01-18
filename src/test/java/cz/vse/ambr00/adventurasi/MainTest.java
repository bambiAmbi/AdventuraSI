package cz.vse.ambr00.adventurasi;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void spravnePocitaniPoskozeniUNepratele() {
        assertEquals(12, Poskozeni.Poskozeni(10));
    }

    @Test
    void nespravnePocitaniPoskozeniUNepratele() {
        assertNotEquals(10, Poskozeni.Poskozeni(10));
    }

    @Test
    void spravnePocitaniPoskozeniUHrace() {
        assertEquals(80, Poskozeni.Poskozeni(10, 10));
    }

    @Test
    void nespravnePocitaniPoskozeniUHrace() {
        assertNotEquals(100, Poskozeni.Poskozeni(10, 10));
    }


    @Test
    void zkouskaSmrti() {
        assertEquals(Hra.Stav.MRTVY, Smrt.Smrt(0));
    }

    @Test
    void nezkouskaSmrti() {
        assertNotEquals(Hra.Stav.MRTVY, Smrt.Smrt(10));
    }

    @Test
    void zkouskaZdaJePostavaNazivu() {
        assertEquals(Hra.Stav.ZIVY, Smrt.Smrt(5));
    }

    @Test
    void nezkouskaZdaJePostavaNazivu() {
        assertNotEquals(Hra.Stav.ZIVY, Smrt.Smrt(0));
    }


    @Test
    void zkouskaZasahuZivotyNadPolovinu() {
        assertEquals(true, MoznostZasahu.moznostZasahu(15, 20));
    }

    @Test
    void zkouskaZasahuZivotyPodPolovinu() {
        assertEquals(true, MoznostZasahu.moznostZasahu(5, 20));
    }

    @Test
    void zkouskaObranyHrace() {
        assertEquals(20, Hra.obranaHrace(10, 10));
    }

    @Test
    void nezkouskaObranyHrace() {
        assertNotEquals(100, Hra.obranaHrace(10, 10));
    }

    @Test
    void zkouskaElixiruZivotaLimit() {
        assertEquals(15, PouzitiElixiru.pouzitiElixiruZdravi(12, 15));
    }

    @Test
    void nezkouskaElixiruZivotaLimit() {
        assertNotEquals(17, PouzitiElixiru.pouzitiElixiruZdravi(12, 15));
    }

    @Test
    void zkouskaElixiruZivotaBezLimitu() {
        assertEquals(14, PouzitiElixiru.pouzitiElixiruZdravi(9, 15));
    }

    @Test
    void nezkouskaElixiruZivotaBezLimitu() {
        assertNotEquals(15, PouzitiElixiru.pouzitiElixiruZdravi(5, 15));
    }

    @Test
    void zkouskaElixiruSilyLimit() {
        assertEquals(20, PouzitiElixiru.pouzitiElixiruSily(18, 20));
    }

    @Test
    void nezkouskaElixiruSilyLimit() {
        assertNotEquals(22, PouzitiElixiru.pouzitiElixiruSily(19, 20));
    }

    @Test
    void zkouskaElixiruSilyBezLimitu() {
        assertEquals(13, PouzitiElixiru.pouzitiElixiruSily(10, 20));
    }

    @Test
    void nezkouskaElixiruSilyBezLimitu() {
        assertNotEquals(15, PouzitiElixiru.pouzitiElixiruSily(10, 20));
    }

}