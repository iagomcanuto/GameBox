package control;

import dao.ConexaoHibernate;
import dao.GenericDAO;
import java.util.List;
import model.Jogo;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class GerenciadorDominio {

    private GenericDAO genDAO;

    public GerenciadorDominio() throws HibernateException {
        try (Session sessao = ConexaoHibernate.getSessionFactory().openSession()) {
            // Forca a inicializacao do Hibernate e a verificacao do schema no inicio do sistema.
        }

        genDAO = new GenericDAO();
    }

    public <T> List<T> listar(Class<T> classe) throws HibernateException {
        return genDAO.listar(classe);
    }

    public List<Jogo> listarJogosDoUsuario(int idUsuario) throws HibernateException {
        return genDAO.listarJogosDoUsuario(idUsuario);
    }

    public void inserirJogoDoUsuario(Jogo jogo, int idUsuario) throws HibernateException {
        genDAO.inserirJogoDoUsuario(jogo, idUsuario);
    }

    public <T> T get(Class<T> classe, int id) throws HibernateException {
        return genDAO.get(classe, id);
    }

    public void inserir(Object obj) throws HibernateException {
        genDAO.inserir(obj);
    }

    public void alterar(Object obj) throws HibernateException {
        genDAO.alterar(obj);
    }

    public void excluir(Object obj) throws HibernateException {
        genDAO.excluir(obj);
    }
}
