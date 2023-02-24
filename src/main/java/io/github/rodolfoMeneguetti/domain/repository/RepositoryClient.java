package io.github.rodolfoMeneguetti.domain.repository;

import io.github.rodolfoMeneguetti.Model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RepositoryClient {

    // Scripts SQL
    private static String INSERT = "INSERT INTO CLIENTE (NOME) VALUES (?) ";

    private static String SELECTALLCLIENTES = "SELECT * FROM CLIENTE";

    private static String UPDATE = "UPDATE CLIENTE SET NOME = ? WHERE ID = ?";

    private static String DELETE = "DELETE FROM CLIENTE WHERE ID = ? ";

    @Autowired
    private JdbcTemplate jdbcTamplate;

    private static RowMapper<Cliente> getRowMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }

    public Cliente salvar(Cliente cliente) {
        jdbcTamplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    public Cliente atualizar(Cliente cliente){
        jdbcTamplate.update(UPDATE, new Object[]{cliente.getNome(), cliente.getId()});
        return cliente;
    }

    public void deletar(Cliente cliente){
        deletar(cliente.getId());
    }

    public void deletar(Integer id){
        jdbcTamplate.update(DELETE, new Object[]{id});
    }

    public List<Cliente> buscarPorNome (String nome){
        return jdbcTamplate.query(SELECTALLCLIENTES.concat(" WHERE NOME LIKE ?"),
                new Object[]{'%' + nome + '%'},
                getRowMapper());
    }


    public List<Cliente> listarClientes() {
       return jdbcTamplate.query(SELECTALLCLIENTES, getRowMapper());
    }

}


