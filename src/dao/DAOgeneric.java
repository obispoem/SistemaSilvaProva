/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author bispo
 */
public class DAOgeneric extends DAO_Abstract {

    @Override
    public void insert(Object object) {
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
    }

    @Override
    public void update(Object object) {
        session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Object object) {
        session.beginTransaction();
        session.flush();
        session.clear();
        session.delete(object);
        session.getTransaction().commit();
    }

    @Override
    public Object list(int id, Class clazz) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(clazz);
        criteria.add(Restrictions.eq("id", id));
        ArrayList lista = (ArrayList) criteria.list();
        session.getTransaction().commit();
        return lista.get(0);
    }

    @Override
    public ArrayList listAll(Class clazz) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(clazz);
        ArrayList lista = (ArrayList) criteria.list();
        session.getTransaction().commit();
        return lista;
    }
/*
    public static void main(String[] args) throws ParseException {
        Session session = HibernateUtil.getSessionFactory().openSession(); // Obtém a sessão do Hibernate
        DAOgeneric dao = new DAOgeneric(); // Instancia o DAO

    ////
        EbsUsuario usuario = (EbsUsuario) dao.list(5, EbsUsuario.class);

        System.out.println("ID: " + usuario.getEbsIdUsuario());
        System.out.println("Nome: " + usuario.getEbsNome());
        System.out.println("Apelido: " + usuario.getEbsApelido());
        System.out.println("CPF: " + usuario.getEbsCpf());
        System.out.println("Data de Nascimento: " + usuario.getEbsDataNasc());
        System.out.println("Nível: " + usuario.getEbsNivel());
        System.out.println("Ativo: " + usuario.getEbsAtivo());

    ///
         EbsUsuario u = new EbsUsuario();
        
         u.setEbsIdUsuario(5);
         u.setEbsNome("Bispo");
         u.setEbsApelido("bispete");
         u.setEbsCpf("04091204309");
         u.setEbsDataNasc(Util.strToDate("02/02/2002"));
         u.setEbsNivel(2);
         u.setEbsAtivo("s");
         u.setEbsSenha("bispo");
        
         dao.insert(u);
        
         ArrayList<EbsUsuario> usuarios = dao.listAll(EbsUsuario.class);
         for (EbsUsuario usuario : usuarios) {
         System.out.println("ID: " + usuario.getEbsIdUsuario());
         System.out.println("Nome: " + usuario.getEbsNome());
         System.out.println("Apelido: " + usuario.getEbsApelido());
         System.out.println("CPF: " + usuario.getEbsCpf());
         System.out.println("Data de Nascimento: " + usuario.getEbsDataNasc());
         System.out.println("Nível: " + usuario.getEbsNivel());
         System.out.println("Ativo: " + usuario.getEbsAtivo());
         }
    ////
         EbsCategoria c = new EbsCategoria();
         c.setEbsIdCategoria(5);
         c.setEbsNome("Fejao");
        
         dao.insert(c);
         ArrayList<EbsCategoria> categorias = dao.listAll(EbsCategoria.class);
         for (EbsCategoria categoria : categorias){
         System.out.println("ID: " + categoria.getEbsIdCategoria());
         System.out.println("Nome: " + categoria.getEbsNome());
         }
    
    ////
        session.close();
    }
*/

}
