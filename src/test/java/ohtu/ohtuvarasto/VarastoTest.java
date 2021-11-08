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
    Varasto virheellinenVarasto;
    Varasto virheellinenVarastoSaldolla;
    Varasto varastoSaldolla;
    Varasto alkuSaldoAlleNolla;
    Varasto saldoPienempiKuinTilavuus;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varastoSaldolla = new Varasto(10, 20);
        virheellinenVarasto = new Varasto(0);
        virheellinenVarastoSaldolla = new Varasto(0, 20);
        alkuSaldoAlleNolla = new Varasto(10, -20);
        saldoPienempiKuinTilavuus = new Varasto(20, 10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaSaldollisellaVarastollaOikeaTilavuus() {
        assertEquals(10, varastoSaldolla.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void saldollisellaVarastollaOikeaTilavuusNollalla() {
        assertEquals(0.0, virheellinenVarastoSaldolla.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void alkusaldoAlleNolla() {
        assertEquals(0.0, alkuSaldoAlleNolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void pienempiAlkusaldoKuinTilavuus() {
        assertEquals(10, saldoPienempiKuinTilavuus.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriVirheellinenTilavuus() {
        assertEquals(0.0, virheellinenVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
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
    public void varastoonLisaysAlleNolla() {
        varasto.lisaaVarastoon(-1);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoTaynna() {
        varasto.lisaaVarastoon(1000);

        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaNegatiivinenMaara() {
        varasto.otaVarastosta(-1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaEnemmanKuinSaldo() {
        varasto.otaVarastosta(10000);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void saldoToString() {
        assertEquals("saldo = " + varasto.getSaldo() + ", vielä tilaa " + varasto.paljonkoMahtuu(), varasto.toString());
    }

}
