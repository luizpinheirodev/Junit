package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Locacao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

public class CalculadoraMockTest {

    @Mock
    private Calculadora calcMock;

    @Spy
    private Calculadora calcSpy;

    //@Spy
    private EmailService email;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void devoMostrarDiferencaEntreMockESpy() {
        Mockito.when(calcMock.somar(1, 2)).thenReturn(8);
        Mockito.when(calcMock.somar(1, 2)).thenCallRealMethod();
        //Mockito.when(calcSpy.somar(1, 2)).thenReturn(8);
        Mockito.doReturn(5).when(calcMock).somar(1, 2);
        Mockito.doNothing().when(calcSpy).imprime();


        Mockito.when(calcSpy.somar(1, 2)).thenReturn(8);


        System.out.println("Mock " + calcMock.somar(1, 2));
        System.out.println("Mock Real " + calcMock.somar(1, 2));
        System.out.println("Spy " + calcSpy.somar(1, 2));

        System.out.println("Mock");
        calcMock.imprime();
        System.out.println("Spy");
        calcSpy.imprime();


    }


    @Test
    public void teste() {
        Calculadora calc = Mockito.mock(Calculadora.class);

        ArgumentCaptor<Integer> argCaptor = ArgumentCaptor.forClass(Integer.class);

        Mockito.when(calc.somar(argCaptor.capture(), Mockito.anyInt())).thenReturn(5);

        Assert.assertEquals(5, calc.somar(1, 1000000));
        //System.out.println(argCaptor.getValue());
    }
}
