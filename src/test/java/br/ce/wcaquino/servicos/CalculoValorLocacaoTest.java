package br.ce.wcaquino.servicos;

import br.ce.wcaquino.dao.LocacaoDAO;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static br.ce.wcaquino.builders.FilmeBuilder.umFilme;
import static br.ce.wcaquino.builders.UsuarioBuilder.umUsuario;
import static org.hamcrest.CoreMatchers.is;

@RunWith(Parameterized.class)
public class CalculoValorLocacaoTest {

    @InjectMocks
    private LocacaoService locacaoService;

    @Mock
    LocacaoDAO dao;
    @Mock
    SPCService spc;

    @Parameterized.Parameter
    public List<Filme> filmes;
    @Parameterized.Parameter(value = 1)
    public Double valorLocacao;
    @Parameterized.Parameter(value = 2)
    public String cenario;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private static Filme filme1 = umFilme().agora();
    private static Filme filme2 = umFilme().agora();
    private static Filme filme3 = umFilme().agora();
    private static Filme filme4 = umFilme().agora();
    private static Filme filme5 = umFilme().agora();
    private static Filme filme6 = umFilme().agora();
    private static Filme filme7 = umFilme().agora();

    @Parameterized.Parameters(name = "Teste {index} = {1} - {2}")
    public static Collection<Object[]> getParametros() {
        return Arrays.asList(new Object[][]{
                {Arrays.asList(filme1, filme2), 8.0, "2 filmes: Sem Descontos"},
                {Arrays.asList(filme1, filme2, filme3), 11.0, "3 filmes: 25%"},
                {Arrays.asList(filme1, filme2, filme3, filme4), 13.0, "4 filmes: 50%"},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5), 14.0, "5 filmes: 75%"},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6), 14.0, "6 filmes: 100%"},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6, filme7), 18.0, "7 filmes: 100%"}
        });
    }

    @Test
    public void deveCalcularValorLocacaoConsiderandoDescontos() throws FilmeSemEstoqueException, LocadoraException {
        //cenario
        Usuario usuario = umUsuario().agora();

        //acao
        Locacao locacao = locacaoService.alugarFilme(usuario, filmes);

        //verificacao
        Assert.assertThat(locacao.getValor(), is(valorLocacao));

        System.out.println("!");
    }

}
