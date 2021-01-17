package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriNollaaVirheellisenTilavuuden() {
        Varasto negatiivinenVarasto = new Varasto(-10);
        assertEquals(0, negatiivinenVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kaksiParametrinenKonstruktoriLuoTyhjanVaraston() {
        Varasto kaksiParametrinenVarasto = new Varasto(10, 0);
        assertEquals(0, kaksiParametrinenVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void kaksiParametrinenKonstruktoriNollaaVirheellisenTilavuuden() {
        Varasto kaksiParametrinenNegatiivinenVarasto = new Varasto(-10, 0);
        assertEquals(0, kaksiParametrinenNegatiivinenVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kaksiParametrinenKonstruktoriNollaaNegatiivisenAlkusaldon() {
        Varasto kaksiParametrinenVarasto = new Varasto(10, -10);
        assertEquals(0, kaksiParametrinenVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void kaksiParametrinenKonstruktoriAntaaOikeanAlkuSaldonKunAlkuSaldoVahemmanKuinTilavuus() {
        Varasto kaksiParametrinenVarasto = new Varasto(10, 9);
        assertEquals(9, kaksiParametrinenVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kaksiParametrinenKonstruktoriAntaaOikeanAlkuSaldonKunAlkuSaldoEnemmanKuinTilavuus() {
        Varasto kaksiParametrinenVarasto = new Varasto(10, 90);
        assertEquals(10, kaksiParametrinenVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(10);
        varasto.lisaaVarastoon(-5);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void tayteenVarastoonLisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(10);
        varasto.lisaaVarastoon(10);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttaminenEiMuutaSaldoa() {
        varasto.lisaaVarastoon(5);
        double saatuMaara = varasto.otaVarastosta(-2);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenPalauttaaOikeanMaaranKunOttoEnemmanKuinSaldo() {
        varasto.lisaaVarastoon(5);
        double saatuMaara = varasto.otaVarastosta(10);
        assertEquals(saatuMaara, 5, vertailuTarkkuus);
    }
    
    @Test
    public void toStringPalauttaaOikeanMerkkijonoesityksen() {
        varasto.lisaaVarastoon(1);
        assertEquals("saldo = 1.0, vielä tilaa 9.0", varasto.toString());
        
    }

}
